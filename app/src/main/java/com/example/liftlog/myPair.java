package com.example.liftlog;

import java.io.Serializable;

public class myPair implements Serializable {
    public Object first;
    public Object second;
    public myPair(Object k, Object v){
        this.first = k;
        this.second = v;
    }

    public myPair(){}
}
