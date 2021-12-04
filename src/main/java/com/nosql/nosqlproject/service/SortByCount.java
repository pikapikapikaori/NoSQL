package com.nosql.nosqlproject.service;

import com.nosql.nosqlproject.repository.Demand15;

import java.util.Comparator;

public class SortByCount implements Comparator {
    public int compare(Object o1,Object o2){
        if(((Demand15)o1).count < ((Demand15)o2).count)
            return 1;
        if(((Demand15)o1).count > ((Demand15)o2).count)
            return -1;
        return 0;
    }
}
