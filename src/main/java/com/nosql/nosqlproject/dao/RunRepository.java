package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.repository.*;
import com.nosql.nosqlproject.entity.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RunRepository extends Neo4jRepository<Run, String> {
    // Insert database operations here.
}
