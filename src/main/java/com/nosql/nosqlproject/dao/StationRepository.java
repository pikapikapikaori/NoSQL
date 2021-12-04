package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.entity.*;
import com.nosql.nosqlproject.repository.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
@Transactional
public interface StationRepository extends Neo4jRepository<Station, String> {
    @Query("match (s:Station) return s.id limit 1")
    ArrayList<String> get_all_station_id();

    @Query("match (s:Station{id:{id}}) return s.name limit 1")
    String get_station_name_by_id(String id);

    @Query("""
            match (l:Line {name: {line_id}, direction: {direction}})
            unwind l.route as k
            match (s:Station {id: k})
            return s""")
    ArrayList<Station> find_route_station(String line_id, String direction);

    @Query("""
            match (s:Station {name: {station_name}}) -[r]- ()
            match (l:Line) where l.name in r.lines
            return s.id, collect(l.name), collect(l.direction)
             """)
    ArrayList<Demand3> find_stationName_routeName(String station_name);

    /*
    public ArrayList<Station> station_lines(String station_id);

    @Query("")
    public ArrayList<Station> find_stations_of_two_lines(String id1, String direction, String id2, String direction2);
     */

    @Query("""
            match
            (a:Station) -[r]-> (b:Station)
            return a.name , b.name , count(r.lines)
            """)
    ArrayList<Demand15> most_connection();

    /* optimal */
    @Query("""
            match (a:Line)
            return a.name, a.direction, count(a.route) order by count(a.route) limit 15
            """)
    ArrayList<Demand16> most_station_up();

    @Query("""
            match (b:Line)
            return b.name, b.direction, count(b.route) order by count(b.route) limit 15
            """)
    ArrayList<Demand16> most_station_down();
}
