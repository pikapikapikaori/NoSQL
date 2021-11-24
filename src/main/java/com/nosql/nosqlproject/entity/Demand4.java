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

@Node
public class Demand4{
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private int runtime;

    @Property("name")
    private String lineName;

    @Property
    private boolean is_up;

    @Property
    private List<Station> stations;
}
