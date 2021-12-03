package com.nosql.nosqlproject.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.entity.Line;
import com.nosql.nosqlproject.service.LineService;
import com.nosql.nosqlproject.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin //写这个注解保证不需要额外配置跨域请求
@RestController
@RequestMapping(value="/line", produces="application/json;charset=UTF-8")
public class LineController {
    LineService lineservice;

    @Autowired
    public LineController(LineService lineservice){
        this.lineservice = lineservice;
    }

    //需求六
    @GetMapping("/find_directRoute")
    public JSONArray find_directRoute(String station1, String station2){
        return lineservice.find_directRoute(station1,station2);
    }
}
