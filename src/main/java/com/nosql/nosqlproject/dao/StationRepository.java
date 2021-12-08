package com.nosql.nosqlproject.dao;

import java.util.ArrayList;

import com.nosql.nosqlproject.entity.Station;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StationRepository extends Neo4jRepository<Station, String> {
    @Query("match (s:Station) return s.id")
    ArrayList<String> get_all_station_id();

    @Query("match (s:Station{id:{id}}) return s.name limit 1")
    String get_station_name_by_id(String id);

    @Query("""
                match (s:Station) where s.name starts with "地铁"
            return distinct s.name
            """)
    ArrayList<String> count_subway_station();

    @Query("""
            match (s:Station) where s.name ends with "(始发站)" or not () --> (s)
            return distinct s.name
                """)
    ArrayList<String> count_start_station();

    @Query("""
            match (s:Station) where s.name ends with "(终点站)" or not (s) --> ()
            return distinct s.name
            """)
    ArrayList<String> count_end_station();

    @Query("""
                match
                (l:Line{name:{name}, direction:{direction}})
            with l.route[{i}] as sid
            match (s:Station{id:sid})
            return s
                """)
    Station find_route_station_by_index(String name, String direction, Integer i);

    @Query("""
            match (l:Line {name: {line_id}, direction: {direction}})
            unwind l.route as k
            match (s:Station {id: k})
            return s""")
    ArrayList<Station> find_route_station(String line_id, String direction);

    @Query("""
            match (s:Station {name: {station_name}})
            return distinct s.id
             """)
    ArrayList<String> find_stationName_routeName_stationId(String station_name);

    @Query("""
            match (s:Station {id: {station_id}}) -[r]- ()
            match (l:Line) where l.name in r.lines and s.id in l.route
            return distinct l.name + l.direction
             """)
    ArrayList<String> find_stationName_routeName_lineId(String station_id);

    /*
     * public ArrayList<Station> station_lines(String station_id);
     * 
     * @Query("") public ArrayList<Station> find_stations_of_two_lines(String id1,
     * String direction, String id2, String direction2);
     */

    /* can optimalize */
    @Query("""
            match
            (a:Station) -[r]-> (b:Station)
            with a, b, length(r.lines) as cnt
            order by cnt desc
            return a.name limit 15
            """)
    ArrayList<String> most_connection_in();

    @Query("""
            match
            (a:Station) -[r]-> (b:Station)
            with a, b, length(r.lines) as cnt
            order by cnt desc
            return b.name limit 15
            """)
    ArrayList<String> most_connection_out();

    @Query("""
            match
            (a:Station) -[r]-> (b:Station)
            with a, b, length(r.lines) as cnt
            order by cnt desc
            return cnt limit 15
            """)
    ArrayList<Integer> most_connection_count();

    /* can optimalize */
    @Query("""
            match (a:Line)
            with a, length(a.route) as cnt
            order by cnt desc
            return a.name limit 15
            """)
    ArrayList<String> most_station_name();

    /* can optimalize */
    @Query("""
            match (a:Line)
            with a, length(a.route) as cnt
            order by cnt desc
            return a.direction limit 15
            """)
    ArrayList<String> most_station_direction();

    /* can optimalize */
    @Query("""
            match (a:Line)
            with a, length(a.route) as cnt
            order by cnt desc
            return cnt limit 15
            """)
    ArrayList<Integer> most_station_count();
}
