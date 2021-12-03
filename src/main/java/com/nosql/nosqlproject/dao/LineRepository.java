package com.nosql.nosqlproject.dao;

import java.util.ArrayList;

import com.nosql.nosqlproject.entity.Line;
import com.nosql.nosqlproject.repository.Demand4;
import com.nosql.nosqlproject.repository.Demand8;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface LineRepository extends Neo4jRepository<Line, String> {

    @Query("""
            match
                (l:Line)
            return l.name
            """)
    public ArrayList<String> get_all_line_names();

    @Query("""
            match
                (l:Line {name: {line_id}})
            return l
            """)
    public Line find_lineId_line(String line_id);

    @Query("""
            match
                (n:Line {name: {line_id}}) where n.route[0] = {stationname1}
            unwind n.route as station
            match
                (s:Station{name: station})
            return n.onewayTime, n.name , n.direction , s
            """)
    public Demand4 find_lineId_stationName_path(String line_id, String station_name1, String station_name2);

    @Query("""
            optional match
            (l:Line {departure: {station1}}, destination: {station2})
            return l.name + l.direction
            """)
    public ArrayList<String> find_directRoute();

    /*
    @Query("""
            match
             (l:Run) where {station_id} in r.route
             """)
    public ArrayList<Demand8> find_station_time_line(String station_id, String base_time, String last_time);

    public ArrayList<Demand8> find_station_time_nearest3(String station_id, String base_time);
     */

    @Query("""
            match
            (l:Line) where {station_id} in r.route
            return l.name
                """)
    public ArrayList<String> get_lines_in_a_station(String station_id);

    @Query("""
            match (l:Line) where l.name ~= "^%d"
            match (k:Line) where k.name start with "K"
            match (g:Line) where g.name start with "G"
            match (n:Line) where n.name start with "N"
            return count(l), count(k), count(g), count(n)
                """)
    public ArrayList<Integer> count_type();

    @Query("""
            match
                (r:Run{line_id:{line_name}})
            return r.time[0], r.time[-1]
                """)
    public ArrayList<String> get_start_end_time_in_one_run(String line_name);

    @Query("""
            match
                (n:Line {name:{line_id}})
            detach delete n
            match
                (r:Run{line_id:n.name})
            detach delete r
            match
                (a:Station) -[r]-> (b:Station) where n.name in r.lines and size(r.lines) = 1
            delete r
            match
                (a:Station) where not (a) -- ()
            delete a
            return n
                """)
    public Line delete_line(@Param("lien_id") String line_id);

    @Query("""
            match
                (l:Line {name: {line_id}}) where {station_id} in l.route
            with apoc.coll.indexOf(l.route, {station_id}) as i
            set l.route[i] = {new_station_id}
            with l.route[i-1] as prev, l.route[i+1] as next
            case prev
                when not null then
                    match (p:Station{id: prev}) -[r]-> (n:Station{id: {station_id}})
                    set r.lines = [x IN r.lines WHERE x <> {line_id}];
                    merge p -[rh]-> (m:Station{id: {new_station_id}})
                    on match
                        set rh.lines= rh.lines + {line_id}
                    on create
                        set rk.lines = [{line_id}]
            end
            case next
                when not null then
                    match (p:Station{id: {station_id}}) -[r]-> (n: Station{id: next})
                    set r.lines = [x IN r.lines WHERE x <> {line_id}];
                    merge (m: Station{id: {new_station_id}}) -[rk]-> n
                    on match
                        set rk.lines = rk.lines + {line_id}
                    on create
                        set rk.lines = [{line_id}]
            end
            return l
                """)
    public Line change_line(String line_id, String station_id, String new_station_id);
}
