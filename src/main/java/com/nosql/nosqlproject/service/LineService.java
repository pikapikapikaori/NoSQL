package com.nosql.nosqlproject.service;

import com.alibaba.fastjson.JSONObject;
import com.nosql.nosqlproject.dao.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LineService {
    @Autowired
    LineRepository linerepository;

    //6 改成Array
    public JSONObject find_directRoute(String station1,String station2){
        JSONObject obj = new JSONObject();
        ArrayList<String> route = new ArrayList<String>();
        route = linerepository.find_directRoute();
        String s = new String();
        for(int i = 0 ; i<route.size(); i++)
           {

               s+=route.get(i);
               if(i < route.size() - 1)
                   s+="、";
           }
        obj.put("直达路线",s);
        return obj;
    }
}
