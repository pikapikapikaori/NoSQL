package com.nosql.nosqlproject.service;

import java.util.Comparator;

class SortByNum implements Comparator {
    public int compare(Object o1,Object o2){
        if(((StationLines)o1).num < ((StationLines)o2).num)
            return 1;
        if(((StationLines)o1).num > ((StationLines)o2).num)
            return -1;
        return 0;
    }
}
