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
public class Line {
    @Id
    private String name;

    @Property
    private boolean directional;

    @Property
    private String direction;

    @Property
    private int interval;

    @Property
    private double kilometer;

    @Property
    private int onewayTime;

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
    private ArrayList<String> route;

    @Relationship(direction = Relationship.Direction.INCOMING)
    private ArrayList<Run> timetable;

    public boolean getDirectional(){
        return this.directional;
    }
}
