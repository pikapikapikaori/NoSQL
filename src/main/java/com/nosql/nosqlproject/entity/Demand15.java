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
public class Demand15 {
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String name_in;
    @Property
    private String name_out;
    @Property
    private int count;
}
