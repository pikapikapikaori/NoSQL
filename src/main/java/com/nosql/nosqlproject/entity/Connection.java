/*
package com.nosql.nosqlproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.TargetNode;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.List;

@Getter
@Setter
@RelationshipEntity(type = "Connects")
public class Connection {
    @Id
    @GeneratedValue
    private Long id;

    @EndNode
    private Station in;

    @StartNode
    private Station out;

    @Property
    private List<String> lines;
}
*/