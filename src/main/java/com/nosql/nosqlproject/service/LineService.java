package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import com.nosql.nosqlproject.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LineService {
    LineRepository linerepository;

    @Autowired
    public LineService(LineRepository linerepository){
        this.linerepository = linerepository;
    }

    //5a
    public JSONArray find_shortestRoute_id(String station1, String station2){
        JSONArray arr = new JSONArray();
        ArrayList<String> station_id;
        station_id = linerepository.shortestpath_by_id_id(station1, station2);
        ArrayList<String> station_name;
        station_name = linerepository.shortestpath_by_id_name(station1, station2);
        ArrayList<String> station_eng;
        station_eng = linerepository.shortestpath_by_id_eng(station1, station2);
        if(!station_id.isEmpty()){
            for(int i = 0; i<station_id.size();i++)
            {
                JSONObject obj = new JSONObject();
                String tmpid = station_id.get(i);
                String tmpname = station_name.get(i);
                String tmpeng = station_eng.get(i);
                obj.put("id",tmpid);
                obj.put("name",tmpname);
                obj.put("english",tmpeng);
                arr.add(obj);
            }
        }
        return arr;
    }

    //5b
    public JSONArray find_shortestRoute_name(String station1, String station2){
        JSONArray arr = new JSONArray();
        ArrayList<String> station1_id = linerepository.get_all_station_ids_by_name(station1);
        ArrayList<String> station2_id = linerepository.get_all_station_ids_by_name(station2);
        ArrayList<Station> routes = new ArrayList<>();

        int count = Integer.MAX_VALUE;

        if(!station1_id.isEmpty()){
            if(!station2_id.isEmpty()){
                for(int i = 0; i < station1_id.size(); i ++){
                    for(int j = 0; j < station2_id.size(); j ++){
                        ArrayList<String> tmpids = linerepository.shortestpath_by_id_id(station1_id.get(i), station2_id.get(j));
                        ArrayList<String> tmpnames = linerepository.shortestpath_by_id_name(station1_id.get(i), station2_id.get(j));
                        ArrayList<String> tmpengs = linerepository.shortestpath_by_id_eng(station1_id.get(i), station2_id.get(j));

                        ArrayList<Station> tmproutes = new ArrayList<>();

                        for(int k = 0; k < tmpids.size(); k ++){
                            Station tmps = new Station();
                            tmps.setId(tmpids.get(k));
                            tmps.setName(tmpnames.get(k));
                            tmps.setEnglish(tmpengs.get(k));

                            tmproutes.add(tmps);
                        }

                        if(tmproutes.size() != 0 && tmproutes.size() < count){
                            count = tmproutes.size();
                            routes = tmproutes;
                        }
                    }
                }


            }
        }

        if(!routes.isEmpty()){
            for(int i = 0; i<routes.size();i++)
            {
                JSONObject obj = new JSONObject();
                Station s = routes.get(i);
                obj.put("id",s.getId());
                obj.put("name",s.getName());
                obj.put("english",s.getEnglish());
                arr.add(obj);
            }
        }
        return arr;
    }

    //6
    public JSONArray find_directRoute(String station1, String station2){
        JSONArray arr = new JSONArray();
        ArrayList<String> route;
        route = linerepository.find_directRoute(station1, station2);
        String s = "";
        if(!route.isEmpty()){
            for(int i = 0 ; i<route.size(); i++)
            {
                String tmp1 = route.get(i);
                if(tmp1.contains("up"))
                    tmp1 = tmp1.replace("up", "路上行");
                else if(tmp1.contains("down"))
                    tmp1 = tmp1.replace("down", "路下行");
                else if(tmp1.contains("circle"))
                    tmp1 = tmp1.replace("circle", "路环线");
                s += tmp1;
                JSONObject obj = new JSONObject();
                obj.put("route",s);
                arr.add(obj);
            }
        }
        return arr;
    }
}
