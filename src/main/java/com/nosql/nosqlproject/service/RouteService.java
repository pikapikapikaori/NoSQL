package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import com.nosql.nosqlproject.dao.StationRepository;
import com.nosql.nosqlproject.entity.Line;
import com.nosql.nosqlproject.entity.Station;
import com.nosql.nosqlproject.repository.Demand4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RouteService {
    @Autowired
    LineRepository linerepository;
    StationRepository stationrepository;

    //1
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

    //2
    public JSONArray find_route_station(String line_id,String direction){
        JSONArray arr = new JSONArray();
        ArrayList<Station> station=new ArrayList<>();
        station = stationrepository.find_route_station(line_id, direction);
        for(int i = 0; i<station.size();i++)
        {
            JSONObject obj = new JSONObject();
            Station s = station.get(i);
            obj.put("id",s.getId());
            obj.put("name",s.getName());
            obj.put("english",s.getEnglish());
            arr.add(obj);
        }
        return arr;
    }

    //4
    public JSONObject find_lineId_stationName_path(String lineId,String stationName1,String stationName2){
        JSONObject obj = new JSONObject();
        Demand4 result = new Demand4();
        result = linerepository.find_lineId_stationName_path(lineId,stationName1,stationName2);
        obj.put("lineName",result.getLineName());
        obj.put("runTime",result.getRunTime());
        JSONArray arr =new JSONArray();
        ArrayList<Station> sta = new ArrayList<Station>();
        sta = result.getStations();
        for(int i = 0; i < sta.size(); i++)
        {
            JSONObject s = new JSONObject();
            Station st = new Station();
            st = sta.get(i);
            s.put("id",st.getId());
            s.put("name",st.getName());
            s.put("english",st.getEnglish());
            arr.add(s);
        }
        obj.put("stations",arr);
        return obj;
    }

    //13
    public JSONObject find_sameStations(String id1,String direction1,String id2,String direction2){
        JSONObject obj = new JSONObject();
        return obj;
    }

    //20a
    public void delete_line(String lineId){
        ;
    }

    //20b
    public void change_line(String lineId,String stationId,String newStationId){
        ;
    }
}
