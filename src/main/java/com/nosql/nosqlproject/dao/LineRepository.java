package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.entity.*;
import com.nosql.nosqlproject.repository.Demand4;
import com.nosql.nosqlproject.repository.Demand8;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LineRepository extends Neo4jRepository<Line, String> {

    public List<String> get_all_line_names();
    public Line find_lineId_line(String line_id);
    public List<Demand4> find_lineId_stationName_path(String line_id, String station_name1, String station_name2);
    public List<String> find_directRoute();
    public List<Demand8> find_station_time_line(String station_id, String base_time, String last_time);
    public List<Demand8> find_station_time_nearest3(String station_id, String base_time);
    public List<String> get_lines_in_a_station(String station_id);
    public List<Integer> count_type();
    public List<String> get_start_end_time_in_one_run(String line_name);
    public void delete_line(String line_id);
    public void change_line(String line_id, String station_id, String new_station_id);
}

