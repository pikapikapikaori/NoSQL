package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import com.nosql.nosqlproject.dao.StationRepository;
import com.nosql.nosqlproject.entity.Line;
import com.nosql.nosqlproject.repository.Demand15;
import com.nosql.nosqlproject.repository.Demand16;
import com.nosql.nosqlproject.repository.Demand17;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

@Service
public class StatisticsService {

    LineRepository linerepository;
    StationRepository stationrepository;

    @Autowired
    public StatisticsService(LineRepository linerepository, StationRepository stationrepository){
        this.linerepository = linerepository;
        this.stationrepository = stationrepository;
    }

    //10
    public JSONArray most_line_station(){
        JSONArray arr = new JSONArray();
        ArrayList<String> stations;
        stations = stationrepository.get_all_station_id();
        ArrayList<StationLines> sta_lin = new ArrayList<>();
        for(int i = 0;i < stations.size();i++)
        {
            StationLines a = new StationLines();
            a.stationId = stations.get(i);
            a.station = stationrepository.get_station_name_by_id(a.stationId);
            ArrayList<String> line_f;
            line_f = linerepository.get_lines_in_a_station(a.stationId);

            ArrayList<String> line = new ArrayList<>();

            if(!line_f.isEmpty()){
               for(int k = 0; k < line_f.size(); k ++){
                   String tmp = line_f.get(k);
                   if(tmp.contains("up"))
                       tmp = tmp.replace("up", "路上行");
                   else if(tmp.contains("down"))
                       tmp = tmp.replace("down", "路下行");
                   else if(tmp.contains("circle"))
                       tmp = tmp.replace("circle", "路环线");

                   line.add(tmp);
               }
            }

            String s = "";
            if(!line.isEmpty()){
                for(int j = 0;j<line.size();j++)
                {
                    s += line.get(j);
                    if(j < line.size() - 1)
                        s+=",";
                }
            }
            a.route = s;
            a.num = line.size();
            sta_lin.add(a);
        }
        Collections.sort(sta_lin,new SortByNum());
        if(!sta_lin.isEmpty()){
            for(int i = 0;i<15;i++)
            {
                JSONObject obj = new JSONObject();
                StationLines a = new StationLines();
                a = sta_lin.get(i);
                obj.put("stationId",a.stationId);
                obj.put("station",a.station);
                obj.put("num",a.num);
                obj.put("route",a.route);
                arr.add(obj);
            }
        }
        return arr;
    }

    //11a
    public JSONArray special_station(){
        JSONArray arr = new JSONArray();
        ArrayList<String> subway;
        subway = stationrepository.count_subway_station();
        ArrayList<String> start;
        start = stationrepository.count_start_station();
        ArrayList<String> end;
        end = stationrepository.count_end_station();
        if(!subway.isEmpty())
        {
            JSONObject obj = new JSONObject();
            obj.put("type","地铁站");
            obj.put("amount",subway.size());
            String s = "";
            for(int i =0;i<subway.size();i++)
            {

                s+=subway.get(i);
                s+=" ";
            }
            obj.put("stations",s);
            arr.add(obj);
        }
        if(!start.isEmpty())
        {
            JSONObject obj = new JSONObject();
            obj.put("type","始发站");
            obj.put("amount",start.size());
            String s = "";
            for(int i =0;i<start.size();i++)
            {

                s+=start.get(i);
                s+=" ";
            }
            obj.put("stations",s);
            arr.add(obj);
        }
        if(!end.isEmpty())
        {
            JSONObject obj = new JSONObject();
            obj.put("type","终点站");
            obj.put("amount",end.size());
            String s = "";
            for(int i =0;i<end.size();i++)
            {
                s+=end.get(i);
                s+=" ";
            }
            obj.put("stations",s);
            arr.add(obj);
        }

        return arr;
    }

    //12
    public JSONArray count_type(){
        JSONArray arr = new JSONArray();
        int[] count = new int[4];
        count[0] = linerepository.count_type_l();
        count[1] = linerepository.count_type_k();
        count[2] = linerepository.count_type_g();
        count[3] = linerepository.count_type_n();
        ArrayList<String> type = new ArrayList<>();
        type.add("常规公交");
        type.add("快速公交");
        type.add("高峰公交");
        type.add("夜班公交");
        for(int i = 0 ; i < 4; i++)
        {
            JSONObject obj = new JSONObject();
            int a = count[i];
            obj.put("type",type.get(i));
            obj.put("num",a);
            arr.add(obj);
        }
        return arr;
    }


    //15
    public JSONArray most_connections(){
        JSONArray arr = new JSONArray();
        ArrayList<String> res_in = stationrepository.most_connection_in();
        ArrayList<String> res_out = stationrepository.most_connection_out();
        ArrayList<Integer> res_cnt = stationrepository.most_connection_count();
        ArrayList<Demand15> result = new ArrayList<>();
        if(!res_cnt.isEmpty()){
            for(int i = 0; i < res_cnt.size(); i++){
                Demand15 dem = new Demand15();
                dem.name_in = res_in.get(i);
                dem.name_out = res_out.get(i);
                dem.count = res_cnt.get(i);
                result.add(dem);
            }

            Collections.sort(result,new SortByCount());
            Demand15 a = new Demand15();

            for(int i = 0 ; i < 15 ;i++)
            {
                a = result.get(i);
                JSONObject obj = new JSONObject();
                obj.put("station1",a.name_in);
                obj.put("station2",a.name_out);
                obj.put("num",a.count);
                arr.add(obj);
            }
        }
        return arr;
    }

    //16
    public JSONArray most_stations(){
        JSONArray arr = new JSONArray();

        ArrayList<String> res_name = stationrepository.most_station_name();
        ArrayList<String> res_direction = stationrepository.most_station_direction();
        ArrayList<Integer> res_cnt = stationrepository.most_station_count();
        ArrayList<Demand16> result = new ArrayList<>();

        if(!res_cnt.isEmpty()){
            for(int i = 0; i < res_cnt.size(); i ++){
                Demand16 dem = new Demand16();
                dem.name = res_name.get(i);
                dem.direction = res_direction.get(i);
                dem.count = res_cnt.get(i);
                result.add(dem);
            }
        }

        Demand16 res = new Demand16();
        if(!result.isEmpty()){
            for(int i = 0; i < result.size(); i ++){
                JSONObject obj = new JSONObject();
                res = result.get(i);
                if(Objects.equals(res.direction, "up"))
                    res.direction = "上行";
                else if(Objects.equals(res.direction, "down"))
                    res.direction = "下行";
                else if(Objects.equals(res.direction, "circle"))
                    res.direction = "环线";
                obj.put("route", res.name + "路" + res.direction);
                obj.put("num", res.count);
                arr.add(obj);
            }
        }
        return arr;
    }

    //17
    public JSONArray longest_time(){
        JSONArray arr = new JSONArray();
        ArrayList<Line> linenames;
        linenames = linerepository.get_all_line_names();

        ArrayList<Demand17> result = new ArrayList<>();

        if(!linenames.isEmpty()){
            for(int i = 0 ; i < linenames.size() ; i++)
            {
                String nam = linenames.get(i).getName();
                String direct = linenames.get(i).getDirection();
                String start_time = linerepository.get_start_time_in_one_run(nam, direct);
                String end_time = linerepository.get_end_time_in_one_run(nam, direct);
                SimpleDateFormat ft = new SimpleDateFormat ("HH:mm");
                Date t1;
                long l1;
                Date t2;
                long l2;
                int runtime;

                String dir = new String();
                if(Objects.equals(direct, "up"))
                    dir = "路上行";
                else if(Objects.equals(direct, "down"))
                    dir = "路下行";
                else if(Objects.equals(direct, "circle"))
                    dir = "路环线";

                nam += dir;

                Demand17 dem = new Demand17();
                dem.name = nam;

                try {
                    t1 = ft.parse(start_time);
                    l1 = t1.getTime();
                    t2 = ft.parse(end_time);
                    l2 = t2.getTime();
                    runtime = (int)((l2 - l1)/60000);
                    dem.time = runtime;
                } catch (ParseException e) {
                    System.out.println("Unparseable using " + ft);
                }

                result.add(dem);
            }
        }

        Collections.sort(result,new SortDemand17ByTime());

        ArrayList<Demand17> res = new ArrayList<>();

        for(int i = 0; i < 15; i ++){
            res.add(result.get(i));
        }

        if(!res.isEmpty()){
            for(int i = 0; i < res.size(); i ++){
                Demand17 tmp_dem = res.get(i);

                JSONObject obj = new JSONObject();
                obj.put("route", tmp_dem.name);
                obj.put("time", tmp_dem.time);
                arr.add(obj);
            }
        }
        return arr;
    }
}

