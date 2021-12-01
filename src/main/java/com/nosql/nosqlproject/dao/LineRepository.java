package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.repository.*;
import com.nosql.nosqlproject.entity.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface LineRepository extends Neo4jRepository<Line, String> {

    @Query("MATCH (l:Line) RETURN l.name")
    public ArrayList<String> get_all_line_names();
    @Query("MATCH (l:Line{name: {line_id}}) RETURN l")
    public Line find_lineId_line(String line_id);
    @Query("Match (n:Line{name:{line_id}}) Case When n.route_up_or_round[0] = {stationname1} Then n.route_up_or_round ELSE n.route_down END as stations Unwind stations as station Match (s:Station{name:station}) Return n.name as lineName, n.onewayTime as runtime, n.direction as direction, s as station ")
    public Demand4 find_lineId_stationName_path(String line_id, String station_name1, String station_name2);
    public ArrayList<String> find_directRoute();
    public ArrayList<Demand8> find_station_time_line(String station_id, String base_time, String last_time);
    public ArrayList<Demand8> find_station_time_nearest3(String station_id, String base_time);
    public ArrayList<String> get_lines_in_a_station(String station_id);
    public ArrayList<Integer> count_type();
    public ArrayList<String> get_start_end_time_in_one_run(String line_name);
    public void delete_line(String line_id);
    public void change_line(String line_id, String station_id, String new_station_id);
}

