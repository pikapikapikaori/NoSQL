package com.nosql.nosqlproject.controller;

import com.nosql.nosqlproject.entity.Stations;
import com.nosql.nosqlproject.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/staions")
public class StationsController {
    @Autowired
    StationsService stationsservice;

    @GetMapping("/find_id_station")
    public Stations find_id_station(int id){
        return stationsservice.find_id_station(id);
    }
}
