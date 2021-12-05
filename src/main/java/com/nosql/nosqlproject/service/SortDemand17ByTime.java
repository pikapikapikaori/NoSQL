package com.nosql.nosqlproject.service;

import com.nosql.nosqlproject.repository.Demand15;
import com.nosql.nosqlproject.repository.Demand17;

import java.util.Comparator;

class SortDemand17ByTime implements Comparator {
    public int compare(Object o1,Object o2){
        if(((Demand17)o1).time < ((Demand17)o2).time)
            return 1;
        if(((Demand17)o1).time > ((Demand17)o2).time)
            return -1;
        return 0;
    }
}
