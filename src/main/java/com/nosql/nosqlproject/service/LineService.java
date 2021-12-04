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
        String s = "";
        if(!route.isEmpty()){
            for(int i = 0 ; i<route.size(); i++)
            {
                String tmp1 = route.get(i);
                if(tmp1.contains("up"))
                    tmp1 = tmp1.replace("up", "上行");
                else if(tmp1.contains("down"))
                    tmp1 = tmp1.replace("down", "下行");
                else if(tmp1.contains("circle"))
                    tmp1 = tmp1.replace("circle", "环线");
                s += tmp1;
                JSONObject obj = new JSONObject();
                obj.put("route",s);
                arr.add(obj);
            }
        }
        return arr;
    }
}
