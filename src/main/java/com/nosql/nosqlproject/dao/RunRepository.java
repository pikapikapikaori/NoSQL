package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.entity.Run;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RunRepository extends Neo4jRepository<Run, String> {
    // Insert database operations here.
}