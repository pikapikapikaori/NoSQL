package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import com.nosql.nosqlproject.dao.StationRepository;
import com.nosql.nosqlproject.repository.Demand15;
import com.nosql.nosqlproject.repository.Demand16;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

@Service
public class StatisticsService {
    @Autowired
    LineRepository linerepository;
    StationRepository stationrepository;

    //10
    public JSONObject most_line_station(){
        JSONObject obj = new JSONObject();
        return obj;
    }

    //12
    public JSONArray count_type(){
        JSONArray arr = new JSONArray();
        ArrayList<Integer> count = linerepository.count_type();
        ArrayList<String> type = new ArrayList<String>();
        type.add("常规公交");
        type.add("快速公交");
        type.add("高峰公交");
        type.add("夜班公交");
        for(int i = 0 ; i < 4; i++)
        {
            JSONObject obj = new JSONObject();
            int a = count.get(i);
            obj.put("type",type.get(i));
            obj.put("num",a);
            arr.add(obj);
        }
        return arr;
    }

    class sortByCount implements Comparator{

    }
    //15
    public JSONArray most_connections(){
        JSONArray arr = new JSONArray();
        ArrayList<Demand15> result = new ArrayList<Demand15>();
        result = stationrepository.most_connection();
        Collections.sort(result,new sortByCount());
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
        return arr;
    }

    //16
    public JSONArray most_stations(){
        JSONArray arr = new JSONArray();
        ArrayList<Demand16> result = new ArrayList<>();
        result = stationrepository.most_station();
        Demand16 a = new Demand16();
        for(int i = 0 ; i < 15 ;i++)
        {
            a = result.get(i);
            JSONObject obj = new JSONObject();
            obj.put("route",a.name+a.direction);
            obj.put("num",a.count);
            arr.add(obj);
        }
        return arr;
    }

    //17
    public JSONArray longest_time(){
        JSONArray arr = new JSONArray();
        ArrayList<String> linenames = new ArrayList<String>();
        linenames = linerepository.get_all_line_names();
        for(int i = 0 ; i < linenames.size() ; i++)
        {
            ArrayList<String> start_end_time = new ArrayList<String>();
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
        return arr;
    }
}

class sortByCount implements Comparator{
    public int compare(Object o1,Object o2){
        if(((Demand15)o1).count < ((Demand15)o2).count)
            return 1;
        return -1;
    }
}