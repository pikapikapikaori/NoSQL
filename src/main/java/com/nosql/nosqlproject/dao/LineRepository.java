package com.nosql.nosqlproject.dao;

import java.util.ArrayList;

import com.nosql.nosqlproject.entity.Line;
import com.nosql.nosqlproject.repository.Demand4;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LineRepository extends Neo4jRepository<Line, String> {

    @Query("""
            match
                (l:Line)
            return l.name
            """)
    ArrayList<String> get_all_line_names();

    @Query("""
            match
                (l:Line {name: {line_id}})
            return l limit 1
            """)
    Line find_lineId_line(String line_id);

    @Query("""
            match
                (n:Line {name: {line_id}}) where n.route[0] = {station_name1} and n.route[-1] = {station_name2}
            unwind n.route as station
            match
                (s:Station{name: station})
            return n.onewayTime, n.name , n.direction , s limit 1
            """)
    Demand4 find_lineId_stationName_path(String line_id, String station_name1, String station_name2);

    @Query("""
            match
                (l:Line {departure: {station1}, destination: {station2}})
            return l.name + l.direction
            """)
    ArrayList<String> find_directRoute(String station1, String station2);

    /*
     * @Query(""" match (l:Run) where {station_id} in r.route """) public
     * ArrayList<Demand8> find_station_time_line(String station_id, String
     * base_time, String last_time);
     * 
     * public ArrayList<Demand8> find_station_time_nearest3(String station_id,
     * String base_time);
     */

    @Query("""
            match
                (l:Line) where {station_id} in l.route
            return l.name
            """)
    ArrayList<String> get_lines_in_a_station(String station_id);

    @Query("""
            match (l:Line) where l.name =~ "^[0-9]+"
            return count(l)
            """)
    Integer count_type_l();

    @Query("""
            match (k:Line) where k.name starts with "K"
            return count(k)
            """)
    Integer count_type_k();

    @Query("""
            match (g:Line) where g.name starts with "G"
            return count(g)
            """)
    Integer count_type_g();

    @Query("""
            match (n:Line) where n.name starts with "N"
            return count(n)
            """)
    Integer count_type_n();

    @Query("""
            match
                (r:Run{line_id:{line_name}})
            return r.time[0], r.time[-1]
            """)
    ArrayList<String> get_start_end_time_in_one_run(String line_name);

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
            return n limit 1
            """)
    Line delete_line(@Param("lien_id") String line_id);

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
            return l limit 1
            """)
    Line change_line(String line_id, String station_id, String new_station_id);
}
