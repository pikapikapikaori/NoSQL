package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import com.nosql.nosqlproject.dao.StationRepository;
import com.nosql.nosqlproject.repository.Demand15;
import com.nosql.nosqlproject.repository.Demand16;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

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
            ArrayList<String> line;
            line = linerepository.get_lines_in_a_station(a.stationId);
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
        ArrayList<Demand15> result;
        result = stationrepository.most_connection();
        Collections.sort(result,new SortByCount());
        Demand15 a = new Demand15();
        if(!result.isEmpty()){
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
        ArrayList<Demand16> result1;
        result1 = stationrepository.most_station_up();
        ArrayList<Demand16> result2;
        result2 = stationrepository.most_station_down();
        Demand16 a,b = new Demand16();
        int i = 0 , j = 0;
        if(!result1.isEmpty() && !result2.isEmpty()){
            while(i+j < 15) {
                a = result1.get(i);
                b = result2.get(j);
                JSONObject obj = new JSONObject();
                if (a.count > b.count) {
                    obj.put("route", a.name + a.direction);
                    obj.put("num", a.count);
                    arr.add(obj);
                    i++;
                } else {
                    obj.put("route", b.name + b.direction);
                    obj.put("num", b.count);
                    arr.add(obj);
                    j++;
                }
                arr.add(obj);
            }
        }
        return arr;
    }

    //17
    public JSONArray longest_time(){
        JSONArray arr = new JSONArray();
        ArrayList<String> linenames;
        linenames = linerepository.get_all_line_names();
        if(!linenames.isEmpty()){
            for(int i = 0 ; i < linenames.size() ; i++)
            {
                ArrayList<String> start_end_time;
                String s = linenames.get(i);
                start_end_time = linerepository.get_start_end_time_in_one_run(s);
                SimpleDateFormat ft = new SimpleDateFormat ("mm:ss");
                Date t1;
                long l1;
                Date t2;
                long l2;
                int runtime;
                JSONObject obj = new JSONObject();
                obj.put("route",s);
                if(!start_end_time.isEmpty()){
                    try {
                        t1 = ft.parse(start_end_time.get(0));
                        l1 = t1.getTime();
                        t2 = ft.parse(start_end_time.get(1));
                        l2 = t2.getTime();
                        runtime = (int)((l2 - l1)/60000);
                        obj.put("time",runtime);
                    } catch (ParseException e) {
                        System.out.println("Unparseable using " + ft);
                    }
                    arr.add(obj);
                }
            }
        }
        return arr;
    }
}

