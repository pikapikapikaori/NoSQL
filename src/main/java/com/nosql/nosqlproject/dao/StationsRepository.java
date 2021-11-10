package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.entity.Stations;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StationsRepository extends Neo4jRepository<Stations, Integer> {
    // Insert database operations here.
    @Query("match (s:Stations) return s")
    Stations find_id_1_station(int id);
}