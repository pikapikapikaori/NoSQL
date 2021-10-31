package com.nosql.nosqlproject;

import lombok.Value;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NosqlprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NosqlprojectApplication.class, args);
    }

}
