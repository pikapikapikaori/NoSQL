package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.StationRepository;
import com.nosql.nosqlproject.entity.Station;
import com.nosql.nosqlproject.repository.Demand3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class StationService {
    @Autowired
    StationRepository stationrepository;

    public Station find_id_station(int id){
        return stationrepository.find_id_station(id);
    }

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

    public JSONObject find_station_time_line(int stationId,String baseTime,int lasttime){
        JSONObject obj = new JSONObject();
        return obj;
    }

    public JSONObject find_station_time_nearest3(int stationId,String basetime){
        JSONObject obj = new JSONObject();
        return obj;
    }
}
