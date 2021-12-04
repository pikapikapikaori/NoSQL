package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import com.nosql.nosqlproject.dao.StationRepository;
import com.nosql.nosqlproject.repository.Demand3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StationService {
    StationRepository stationrepository;
    LineRepository linerepository;

    @Autowired
    public StationService(StationRepository stationrepository, LineRepository linerepository){
        this.stationrepository = stationrepository;
        this.linerepository = linerepository;
    }


    //3
    public JSONArray find_stationName_routeName(String stationName){
        JSONArray arr = new JSONArray();
        ArrayList<Demand3> result;
        result =  stationrepository.find_stationName_routeName(stationName);
        if(!result.isEmpty()){
            for(int i=0;i<result.size();i++)
            {
                JSONObject obj = new JSONObject();
                Demand3 demand3 = new Demand3();
                demand3 = result.get(i);
                obj.put("stationId",demand3.stationId);
                String str = null;
                ArrayList<String> lineIds;
                lineIds =demand3.lineIds;
                ArrayList<String> direction;
                direction = demand3.directions;
                if(!lineIds.isEmpty()){
                    if(!direction.isEmpty()){
                        for(int j = 0 ; j < lineIds.size() ; j++)
                        {
                            str += "\"";
                            str += lineIds.get(i);
                            str += direction.get(i);
                            str += "\" ";
                        }
                    }
                }
                obj.put("routeName",str);
                arr.add(obj);
            }
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
