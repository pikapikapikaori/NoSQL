package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import com.nosql.nosqlproject.dao.StationRepository;
import com.nosql.nosqlproject.entity.Station;
import com.nosql.nosqlproject.repository.Demand3;
import com.nosql.nosqlproject.repository.Demand8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class StationService {
    @Autowired
    StationRepository stationrepository;
    LineRepository linerepository;


    //3
    public JSONArray find_stationName_routeName(String stationName){
        JSONArray arr = new JSONArray();
        ArrayList<Demand3> result = new ArrayList<>();
        result =  stationrepository.find_stationName_routeName(stationName);
        for(int i=0;i<result.size();i++)
        {
            JSONObject obj = new JSONObject();
            Demand3 demand3 = new Demand3();
            demand3 = result.get(i);
            obj.put("stationId",demand3.getStationID());
            String str = null;
            ArrayList<String> lineIds=new ArrayList<>();
            lineIds =demand3.getLineId();
            ArrayList<String> direction=new ArrayList<>();
            direction = demand3.getDirection();
            for(int j = 0 ; j < lineIds.size() ; j++)
            {
                str += "\"";
                str += lineIds.get(i);
                str += direction.get(i);
                str += "\" ";
            }
            obj.put("routeName",str);
            arr.add(obj);
        }
        return arr;
    }
/*
    //8
    public JSONArray find_station_time_line(String stationId,String baseTime,String lasttime){
        JSONArray arr = new JSONArray();
        ArrayList<Demand8> result = new ArrayList<>();
        Demand8 a = new Demand8();
        result = linerepository.find_station_time_line(stationId,baseTime,lasttime);
        for(int i = 0; i < result.size(); i++)
        {
            a = result.get(i);
            JSONObject obj = new JSONObject();
            obj.put("lineName",a.getLineName());
            String soon = a.soon + "";
            String s = soon + "分钟后到站";
            obj.put("soon",s);
            arr.add(obj);
        }
        return arr;
    }

    //9

    public JSONArray find_station_time_nearest3(String stationId,String basetime){
        JSONArray arr = new JSONArray();
        ArrayList<Demand8> result = new ArrayList<>();
        result = linerepository.find_station_time_nearest3(stationId,basetime);
        Demand8 a = new Demand8();
        int j = 1;
        for(int i = 0; i < result.size(); i++)
        {
            a = result.get(i);
            JSONObject obj = new JSONObject();
            String s1 = a.lineName + "班次" +(j +"");
            obj.put("lineName",s1);
            String s2 = a.soon + "分钟后到站";
            obj.put("soon",s2);
            arr.add(obj);
        }
        return arr;
    }
*/

}
