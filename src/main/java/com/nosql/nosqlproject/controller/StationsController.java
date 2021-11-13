package com.nosql.nosqlproject.controller;

import com.nosql.nosqlproject.entity.Stations;
import com.nosql.nosqlproject.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin //写这个注解保证不需要额外配置跨域请求
@RestController
@RequestMapping("/staions")
public class StationsController {
    @Autowired
    StationsService stationsservice;

    //这个函数我前端调用时地址为"/stations/find_id_station"，第一个/前默认为localhost:8080
    /*
    GetMapping表示匹配前端获取数据的请求，可以认为是不会对数据库的数据做出改变，这次项目中只有需求19、20需要用PostMapping
    在函数参数前可以添加注解@RequestBody或@RequestParam来进行与前端的参数匹配，具体支持的json格式与支持的请求模式（get/post）上网搜索
    get请求通过url传递参数，不在后端开发考虑范围内
     */
    @GetMapping("/find_id_station")
    public Stations find_id_station(int id){
        return stationsservice.find_id_station(id);
    }
}
