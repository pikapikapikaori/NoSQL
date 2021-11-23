package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    @Autowired
    LineRepository linerepository;
    public JSONObject find_lineId_line(String lineId){
        JSONObject obj = new JSONObject();
        return obj;
    }

    public JSONObject find_route_station(String station_id,String direction){
        JSONObject obj = new JSONObject();
        return obj;
    }

    public JSONObject find_lineId_stationName_path(String lineId,String stationName1,String stationName2){
        JSONObject obj = new JSONObject();
        return obj;
    }

    public JSONObject find_sameStations(String id1,String direction1,String id2,String direction2){
        JSONObject obj = new JSONObject();
        return obj;
    }

    public void delete_line(String lineId){
        ;
    }

    public void change_line(String lineId,String stationId,String newStationId){
        ;
    }
}
