package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import com.nosql.nosqlproject.dao.StationRepository;
import com.nosql.nosqlproject.repository.Demand3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StationService {
    StationRepository stationrepository;
    LineRepository linerepository;

    @Autowired
    public StationService(StationRepository stationrepository, LineRepository linerepository){
        this.stationrepository = stationrepository;
        this.linerepository = linerepository;
    }


    //3
    public JSONArray find_stationName_routeName(String stationName){
        JSONArray arr = new JSONArray();
        ArrayList<String> res_stationId = stationrepository.find_stationName_routeName_stationId(stationName);

        ArrayList<ArrayList<String>> res_lineId = new ArrayList<>();

        if(!res_stationId.isEmpty()){

            for(int i = 0; i < res_stationId.size(); i ++){
                String tmpid = res_stationId.get(i);
                ArrayList<String> tmpres_lineId_t =  stationrepository.find_stationName_routeName_lineId(tmpid);

                ArrayList<String> tmpres_lineId = new ArrayList<>();

                if(!tmpres_lineId_t.isEmpty()){
                    for(int k = 0; k < tmpres_lineId_t.size(); k ++){
                        String tmp = tmpres_lineId_t.get(k);
                        if(tmp.contains("up"))
                            tmp = tmp.replace("up", "路上行");
                        else if(tmp.contains("down"))
                            tmp = tmp.replace("down", "路下行");
                        else if(tmp.contains("circle"))
                            tmp = tmp.replace("circle", "路环线");

                        tmpres_lineId.add(tmp);
                    }
                }

                res_lineId.add(tmpres_lineId);
            }
        }

        ArrayList<Demand3> result = new ArrayList<>();

        if(!res_stationId.isEmpty()){
            for(int i = 0; i < res_stationId.size(); i ++){
                Demand3 dem = new Demand3();
                dem.stationId = res_stationId.get(i);
                dem.lineIds = res_lineId.get(i);
                result.add(dem);
            }
        }

        if(!result.isEmpty()){
            for(int i=0;i<result.size();i++)
            {
                JSONObject obj = new JSONObject();
                Demand3 demand3 = new Demand3();
                demand3 = result.get(i);
                obj.put("id",demand3.stationId);
                String str = "";
                ArrayList<String> lineIds;
                lineIds =demand3.lineIds;
                if(!lineIds.isEmpty()){
                    for(int j = 0 ; j < lineIds.size() ; j++)
                    {
                        str += "\"";
                        str += lineIds.get(j);
                        str += "\" ";
                    }
                }
                obj.put("routes",str);
                arr.add(obj);
            }
        }
        return arr;
    }
/*
    //8
    public JSONArray find_station_time_line(String stationId,String baseTime,String lasttime){
        JSONArray arr = new JSONArray();
        ArrayList<Demand8> result = new ArrayList<>();
        Demand8 a = new Demand8();
        result = linerepository.find_station_time_line(stationId,baseTime,lasttime);
        for(int i = 0; i < result.size(); i++)
        {
            a = result.get(i);
            JSONObject obj = new JSONObject();
            obj.put("lineName",a.getLineName());
            String soon = a.soon + "";
            String s = soon + "分钟后到站";
            obj.put("soon",s);
            arr.add(obj);
        }
        return arr;
    }

    //9

    public JSONArray find_station_time_nearest3(String stationId,String basetime){
        JSONArray arr = new JSONArray();
        ArrayList<Demand8> result = new ArrayList<>();
        result = linerepository.find_station_time_nearest3(stationId,basetime);
        Demand8 a = new Demand8();
        int j = 1;
        for(int i = 0; i < result.size(); i++)
        {
            a = result.get(i);
            JSONObject obj = new JSONObject();
            String s1 = a.lineName + "班次" +(j +"");
            obj.put("lineName",s1);
            String s2 = a.soon + "分钟后到站";
            obj.put("soon",s2);
            arr.add(obj);
        }
        return arr;
    }
*/

}
