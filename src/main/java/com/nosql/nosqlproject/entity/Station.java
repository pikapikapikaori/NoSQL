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
public class Station {
    @Id
    private String id;

    @Property
    private String name;

    @Property
    private String english;

    @Property
    private ArrayList<String> line_names;

    @Relationship(direction = Relationship.Direction.INCOMING)
    private ArrayList<Station> toSelf;

    @Relationship(direction = Relationship.Direction.OUTGOING)
    private ArrayList<Station> fromSelf;
}
