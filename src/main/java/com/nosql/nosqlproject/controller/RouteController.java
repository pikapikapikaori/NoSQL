package com.nosql.nosqlproject.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.entity.Station;
import com.nosql.nosqlproject.service.RouteService;
import com.nosql.nosqlproject.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin //写这个注解保证不需要额外配置跨域请求
@RestController
@RequestMapping("/route")
public class RouteController {
    @Autowired
    RouteService routeservice;
    //需求一
    @GetMapping("/find_lineId_line")
    public JSONObject find_lineId_line(String lineId){
        return routeservice.find_lineId_line(lineId);
    }
    //需求二
    @GetMapping("/find_route_station")
    public JSONArray find_route_station(String line_id, String direction){
        return routeservice.find_route_station(line_id,direction);
    }
    //需求四
    @GetMapping("/find_lineId_stationName_path")
    public JSONObject find_lineId_stationName_path(String lineId,String stationName1,String stationName2){
        return routeservice.find_lineId_stationName_path(lineId,stationName1,stationName2);
    }
    //需求十三
    @GetMapping("/find_sameStations")
    public JSONArray find_sameStations(String id1,String direction1,String id2,String direction2){
        return routeservice.find_sameStations(id1,direction1,id2,direction2);
    }
    //需求二十a

    @PostMapping("/delete_line")
    public void delete_line(String lineId){
        routeservice.delete_line(lineId);
    }
    //需求20b
    @PostMapping("/change_line")
    public JSONArray change_line(String lineId,String direction,String stationId,String newStationId){
        return routeservice.change_line(lineId,direction,stationId,newStationId);
    }

}
