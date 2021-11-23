package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineService {
    @Autowired
    LineRepository linerepository;
    public JSONObject find_directRoute(String station1,String station2){
        JSONObject obj = new JSONObject();
        return obj;
    }
}
