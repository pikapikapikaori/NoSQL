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
public class Station {
    @Id
    private String id;
    @Property
    private String name;
    @Property
    private String english;

    @Property
    private List<String> line_names;
}
