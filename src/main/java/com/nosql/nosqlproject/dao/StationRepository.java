package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.repository.*;
import com.nosql.nosqlproject.entity.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface StationRepository extends Neo4jRepository<Station, Integer> {
    @Query("MATCH (s:Station) RETURN s.id")
    public List<String> get_all_station_id();
    @Query("MATCH (l:Line{name: {line_id}}) WITH CASE {direction} WHEN '上行' THEN l.route_up_or_round WHEN '下行' THEN l.route_down ELSE null END AS list UNWIND list AS k RETURN (:Station{name: k}) ")
    public Station find_route_station(String line_id, String direction);
    public ArrayList<Demand3> find_stationName_routeName(String station_id);
    public ArrayList<Station> station_lines(String station_id);
    public ArrayList<Station> find_sameStation(String id1, String direction, String id2, String direction2);
    public List<Demand15> most_connection(String station_id1, String station_id2);
    public List<Demand16> most_station();
}
