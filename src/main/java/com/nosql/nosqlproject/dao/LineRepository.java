package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.repository.*;
import com.nosql.nosqlproject.entity.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.query.;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface LineRepository extends Neo4jRepository<Line, String> {

    @Query("""
            match
                (l:Line)
            return l.name
            """)
    public ArrayList<String> get_all_line_names();

    @Query("""
            match
                (l:Line{name: {line_id}})
            return l
            """)
    public Line find_lineId_line(String line_id);

    @Query("""
            match
                (n:Line{name:{line_id}})
            case
                when n.route_up_or_round[0] = {stationname1} then
                    n.route_up_or_round
                else
                    n.route_down
            end as stations
            unwind stations as station
            match
                (s:Station{name:station})
            return n.onewayTime, n.name , n.direction , s
            """)
    public Demand4 find_lineId_stationName_path(String line_id, String station_name1, String station_name2);

    @Query("""
            optional match
            (l:Line{departure:{station1}}, destination:{station2})
            return l.name + l.direction
            """)
    public ArrayList<String> find_directRoute();

    @Query("""
            match
             (l:Run) where {station_id} in r.route_up_or_round or {station_id} in r.route_down
             """)
    public ArrayList<Demand8> find_station_time_line(String station_id, String base_time, String last_time);

    public ArrayList<Demand8> find_station_time_nearest3(String station_id, String base_time);

    @Query("""
            match
            (l:Line) where {station_id} in r.route_up_or_round or {station_id} in r.route_down
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

    @Transcational
    @Query("""
    match
    (n:Line{name:{line_id}})
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
    """)
    public void delete_line(@Param("lien_id")String line_id);

    @Transcational
    @Query("""
    """)
    public void change_line(String line_id, String station_id, String new_station_id);
}
