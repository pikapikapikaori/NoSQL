package com.nosql.nosqlproject.entity;

import java.util.ArrayList;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Node
public class Run {
    @Id
    private String line_id;

    @Property
    private String direction;

    @Property
    private ArrayList<String> time;

    @Relationship(direction = Relationship.Direction.OUTGOING)
    private ArrayList<Line> belongTo;
}
