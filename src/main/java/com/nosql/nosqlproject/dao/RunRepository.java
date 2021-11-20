package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.entity.Station;
import com.nosql.nosqlproject.entity.Line;
import com.nosql.nosqlproject.entity.Connection;
import com.nosql.nosqlproject.entity.Run;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RunRepository extends Neo4jRepository<Run, Integer> {
    // Insert database operations here.
    @Query("match (s:Stations) return s")
    Station find_id_station(@Param("id") int id);
}
