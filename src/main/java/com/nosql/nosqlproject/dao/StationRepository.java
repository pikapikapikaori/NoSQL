package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.entity.*;
import com.nosql.nosqlproject.repository.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface StationRepository extends Neo4jRepository<Station, Integer> {
    @Query("match (s:Station) return s.id")
    public ArrayList<String> get_all_station_id();

    @Query("match (s:Station{id:{id}}) return s.name")
    public String get_station_name_by_id(String id);

    @Query("""
            match (l:Line{name: {line_id}, direction: {direction}})
            unwind l.route AS k
            return (:Station{name: k}) """)
    public ArrayList<Station> find_route_station(String line_id, String direction);

    @Query("")
    public ArrayList<Demand3> find_stationName_routeName(String station_id);

    public ArrayList<Station> station_lines(String station_id);

    @Query("")
    public ArrayList<Station> find_stations_of_two_lines(String id1, String direction, String id2, String direction2);

    @Query("""
            match
            (a:Station) -[r]-> (b:Station)
            return a.name , b.name , count(r.lines)
            """)
    public ArrayList<Demand15> most_connection();

    /* optimal */
    @Query("""
                match (a:Line)
            return a.name, a.direction, count(a.route) order by count(a.route) limit 15

                            """)
    public ArrayList<Demand16> most_station_up();

    @Query("""
                match (b:Line)
            return a.name, a.direction, count(a.route) order by count(a.route) limit 15

                """)
    public ArrayList<Demand16> most_station_down();
}
