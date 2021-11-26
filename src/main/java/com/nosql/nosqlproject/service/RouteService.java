package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import com.nosql.nosqlproject.entity.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    @Autowired
    LineRepository linerepository;
    public JSONObject find_lineId_line(String lineId){
        JSONObject obj = new JSONObject();
        Line line = new Line();
        line = linerepository.find_lineId_line(lineId);
        obj.put("route",line.getDeparture()+"-"+line.getDestination());
        obj.put("directional",line.getDirectional());
        obj.put("length",line.getKilometer());
        obj.put("lineId",line.getName());
        obj.put("interval",line.getInterval());
        obj.put("oneWayTime",line.getOnewayTime());
        obj.put("type",line.getType());
        obj.put("runtime",line.getStart_time()+"-"+line.getEnd_time());
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
