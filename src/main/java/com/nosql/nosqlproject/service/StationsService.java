package com.nosql.nosqlproject.service;

import com.nosql.nosqlproject.dao.StationsRepository;
import com.nosql.nosqlproject.entity.Stations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationsService {
    @Autowired
    StationsRepository stationsrepository;

    public Stations find_id_station(int id){
        return stationsrepository.find_id_station(id);
    }
}
