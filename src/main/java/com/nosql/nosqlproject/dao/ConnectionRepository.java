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

public interface ConnectionRepository extends Neo4jRepository<Connection, Integer> {
    // Insert database operations here.
}
