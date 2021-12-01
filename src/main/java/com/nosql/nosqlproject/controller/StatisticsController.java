package com.nosql.nosqlproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.entity.Station;
import com.nosql.nosqlproject.service.StationService;
import com.nosql.nosqlproject.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin //写这个注解保证不需要额外配置跨域请求
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsservice;
    //需求十
    @GetMapping("/most_line_station")
    public JSONObject most_line_station(){
        return statisticsservice.most_line_station();
    }
    //需求十二
    @GetMapping("/count_type")
    public JSONObject count_type(){
        return statisticsservice.count_type();
    }
    //需求十五
    @GetMapping("/most_connections")
    public JSONObject most_connections(){
        return statisticsservice.most_connections();
    }
    //需求十六
    @GetMapping("/most_stations")
    public JSONObject most_stations(){
        return statisticsservice.most_stations();
    }
    //需求十七
    @GetMapping("/longest_time")
    public JSONObject longest_time(){
        return statisticsservice.longest_time();
    }
}