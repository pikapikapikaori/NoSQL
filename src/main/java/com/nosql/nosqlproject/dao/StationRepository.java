package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.entity.Station;
import com.nosql.nosqlproject.entity.Line;
import com.nosql.nosqlproject.entity.Run;
import com.nosql.nosqlproject.repository.Demand15;
import com.nosql.nosqlproject.repository.Demand16;
import com.nosql.nosqlproject.repository.Demand3;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface StationRepository extends Neo4jRepository<Station, Integer> {
    // Insert database operations here.
    @Query("match (s:Stations) return s")
    Station find_id_station(@Param("id") int id);

    public List<String> get_all_station_id();
    public Station find_route_station(String line_id, String direction);
    public ArrayList<Demand3> find_stationName_routeName(String station_id);
    public ArrayList<Station> station_lines(String station_id);
    public ArrayList<Station> find_sameStation(String id1, String direction, String id2, String direction2);
    public List<Demand15> most_connection(String station_id1, String station_id2);
    public List<Demand16> most_station();
}
