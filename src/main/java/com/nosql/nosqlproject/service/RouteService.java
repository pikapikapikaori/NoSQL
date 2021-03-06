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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Service
public class RouteService {
    LineRepository linerepository;
    StationRepository stationrepository;

    @Autowired
    public RouteService(LineRepository linerepository, StationRepository stationrepository){
        this.linerepository = linerepository;
        this.stationrepository = stationrepository;
    }

    //1
    public JSONObject find_lineId_line(String lineId){
        JSONObject obj = new JSONObject();
        if(linerepository.find_lineId_line(lineId) != null){
            Line line = linerepository.find_lineId_line(lineId);
            obj.put("route",line.getDeparture()+"-"+line.getDestination());
            obj.put("directional",line.getDirectional());
            obj.put("length",line.getKilometer());
            obj.put("lineId",line.getName() + "路");
            obj.put("interval",line.getInterval());
            obj.put("oneWayTime",line.getOnewayTime());
            obj.put("type",line.getType());
            obj.put("runtime",line.getStart_time()+"-"+line.getEnd_time());
        }
        return obj;
    }

    //2
    public JSONArray find_route_station(String line_id,String direction){
        JSONArray arr = new JSONArray();
        ArrayList<Station> station;
        station = stationrepository.find_route_station(line_id, direction);
        if(!station.isEmpty()){
            for(int i = 0; i<station.size();i++)
            {
                JSONObject obj = new JSONObject();
                Station s = station.get(i);
                obj.put("id",s.getId());
                obj.put("name",s.getName());
                obj.put("english",s.getEnglish());
                arr.add(obj);
            }
        }
        return arr;
    }

    //4
    public JSONObject find_lineId_stationName_path(String lineId,String stationName1,String stationName2){
        JSONObject obj = new JSONObject();
        Demand4 result = new Demand4();

        String res_lineName = linerepository.find_lineId_stationName_path_lineName(lineId, stationName1, stationName2);
        String res_direction = linerepository.find_lineId_stationName_path_direction(lineId, stationName1, stationName2);
        String res_depttime = linerepository.find_lineId_stationName_path_departtime(lineId, stationName1, stationName2);
        String res_desttime = linerepository.find_lineId_stationName_path_desttime(lineId, stationName1, stationName2);
        int res_deptind = linerepository.find_lineId_stationName_path_departind(lineId, stationName1, stationName2);
        int res_destind = linerepository.find_lineId_stationName_path_destind(lineId, stationName1, stationName2);

        String res_direct = new String();
        if(Objects.equals(res_direction, "up"))
            res_direct = "上行";
        else if (Objects.equals(res_direction, "down"))
            res_direct = "下行";
        else if (Objects.equals(res_direction, "circle"))
            res_direct = "环线";

        result.lineName = res_lineName;
        result.direction = res_direction;
        result.departure_time = res_depttime;
        result.destination_time = res_desttime;
        result.departure_index = res_deptind;
        result.destination_index = res_destind;

        if(result != null){
            obj.put("lineName",result.lineName + "路" + res_direct);
            SimpleDateFormat ft = new SimpleDateFormat ("HH:mm");
            Date t1;
            long l1;
            Date t2;
            long l2;
            int runtime;
            if((result.departure_time!=null)&&(result.destination_time!=null))
            {
                try{
                    t1 = ft.parse(result.destination_time);
                    l1 = t1.getTime();
                    t2 = ft.parse(result.departure_time);
                    l2 = t2.getTime();
                    runtime = (int)((l1 - l2)/60000);
                    obj.put("runTime",runtime);
                }catch (ParseException e){
                    System.out.println("Unparseable using " + ft);
                }
            }
            JSONArray arr =new JSONArray();
            int departure_index = result.departure_index;
            int destination_index = result.destination_index;
            for(int i = departure_index;i<=destination_index;i++)
            {
                Station sta = stationrepository.find_route_station_by_index(result.lineName,result.direction,i);
                JSONObject s = new JSONObject();
                s.put("id",sta.getId());
                s.put("name",sta.getName());
                s.put("english",sta.getEnglish());
                arr.add(s);
            }
            obj.put("stations",arr);
        }

        return obj;
    }

    //13
    public JSONArray find_sameStations(String id1,String direction1,String id2,String direction2){
        JSONArray arr = new JSONArray();
        ArrayList<Station> result1;
        result1 = stationrepository.find_route_station(id1,direction1);
        ArrayList<Station> result2;
        result2 = stationrepository.find_route_station(id2,direction2);
        Station sta;
        //result1.retainAll(result2);



        ArrayList<Station> to_delete = new ArrayList<>();
        if(!result1.isEmpty()){
            for(int i = 0; i < result1.size(); i ++){
                boolean has = false;
                if(!result2.isEmpty()){
                    for(int j = 0; j < result2.size(); j ++){
                        if(Objects.equals(result1.get(i).getId(), result2.get(j).getId())){
                            has = true;
                            break;
                        }
                    }
                }
                if(!has){
                    to_delete.add(result1.get(i));
                }
            }
        }

        for(int i = 0; i < to_delete.size(); i ++)
            result1.remove(to_delete.get(i));

        if(!result1.isEmpty()){
            for(int i = 0 ; i < result1.size() ; i++)
            {
                sta = result1.get(i);
                JSONObject obj = new JSONObject();
                obj.put("station_id",sta.getId());
                obj.put("station_name",sta.getName());
                obj.put("station_english",sta.getEnglish());
                arr.add(obj);
            }
        }
        return arr;
    }

    //20a
    public void delete_line(String lineId){
        linerepository.delete_line(lineId);
    }

    //20b
    public JSONArray change_line(String lineId,String direction,String stationId,String newStationId){
        linerepository.change_line(lineId, stationId, newStationId);
        JSONArray arr = new JSONArray();
        ArrayList<Station> station;
        station = stationrepository.find_route_station(lineId, direction);
        if(!station.isEmpty()){
            for(int i = 0; i<station.size();i++)
            {
                JSONObject obj = new JSONObject();
                Station s = station.get(i);
                obj.put("id",s.getId());
                obj.put("name",s.getName());
                obj.put("english",s.getEnglish());
                arr.add(obj);
            }
        }
        return arr;
    }
}
