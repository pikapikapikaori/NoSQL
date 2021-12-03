package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LineService {
    LineRepository linerepository;

    @Autowired
    public LineService(LineRepository linerepository){
        this.linerepository = linerepository;
    }

    //6
    public JSONArray find_directRoute(String station1, String station2){
        JSONArray arr = new JSONArray();
        ArrayList<String> route;
        route = linerepository.find_directRoute(station1, station2);
        String s = new String();
        if(!route.isEmpty()){
            for(int i = 0 ; i<route.size(); i++)
            {
                s += route.get(i);
                JSONObject obj = new JSONObject();
                obj.put("route",s);
                arr.add(obj);
            }
        }
        return arr;
    }
}
