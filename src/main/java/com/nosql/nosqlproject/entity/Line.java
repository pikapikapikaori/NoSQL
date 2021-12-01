package com.nosql.nosqlproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import java.util.List;

@Getter
@Setter
@Node
public class Line {
    @Id
    private String name;
    @Property
    private String direction;
    @Property
    private int interval;
    @Property
    private float kilometer;
    @Property
    private String onewayTime;
    @Property
    private String departure;
    @Property
    private String start_time;
    @Property
    private String destination;
    @Property
    private String end_time;
    @Property
    private String type;
    @Property
    private List<String> route_up_or_round;
    @Property
    private List<String> route_down;
}
