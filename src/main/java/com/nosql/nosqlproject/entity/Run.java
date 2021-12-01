package com.nosql.nosqlproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

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
