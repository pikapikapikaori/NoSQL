package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    @Autowired
    LineRepository linerepository;

    //10
    public JSONObject most_line_station(){
        JSONObject obj = new JSONObject();
        return obj;
    }

    //12
    public JSONObject count_type(){
        JSONObject obj = new JSONObject();
        return obj;
    }

    //15
    public JSONObject most_connections(){
        JSONObject obj = new JSONObject();
        return obj;
    }

    //16
    public JSONObject most_stations(){
        JSONObject obj = new JSONObject();
        return obj;
    }

    //17
    public JSONObject longest_time(){
        JSONObject obj = new JSONObject();
        return obj;
    }
}
