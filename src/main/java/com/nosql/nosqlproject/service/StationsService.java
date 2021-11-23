package com.nosql.nosqlproject.service;

import com.nosql.nosqlproject.dao.StationRepository;
import com.nosql.nosqlproject.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationsService {
    @Autowired
    StationRepository stationrepository;

    public Station find_id_station(int id){
        return stationrepository.find_id_station(id);
    }
}
