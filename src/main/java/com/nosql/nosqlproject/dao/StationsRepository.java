package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.entity.Stations;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

public interface StationsRepository extends Neo4jRepository<Stations, Integer> {
    // Insert database operations here.
}