package com.nosql.nosqlproject.dao;

import com.nosql.nosqlproject.entity.Stations;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StationsRepositoryTest {
    @Test
    public void add_station_test(){
    Stations s1 = new Stations();
    s1.setId(1);
    s1.setEnglish("teststation");
    s1.setName("测试站");
    }
}