package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.StationRepository;
import com.nosql.nosqlproject.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {
    @Autowired
    StationRepository stationrepository;

    public Station find_id_station(int id){
        return stationrepository.find_id_station(id);
    }

    public JSONObject find_stationName_routeName(String stationName){
        JSONObject obj = new JSONObject();
        return obj;
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
