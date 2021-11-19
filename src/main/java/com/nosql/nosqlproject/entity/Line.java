package com.nosql.nosqlproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import java.util.Date;

@Getter
@Setter
@Node
public class Line {
    @Id
    private String name;
    @Property
    private boolean directional;
    @Property
    private int interval;
    @Property
    private float kilometer;
    @Property
    private String onewayTime;
    @Property
    private String departure;
    @Property
    private Date start_time;
    @Property
    private String destination;
    @Property
    private Date end_time;
    @Property
    private String type;
    @Property
    private int[] route_up;
    @Property
    private int[][] time_up;
    @Property
    private int[] route_down;
    @Property
    private int[] time_down;
}
