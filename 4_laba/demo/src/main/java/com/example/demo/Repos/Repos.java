package com.example.demo.Repos;

import com.example.demo.Results.Results;
import com.example.demo.Triangle.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component

public class Repos{
    private ConcurrentHashMap<Triangle, Results> mp = new ConcurrentHashMap<>();

    public Repos(){}

    public ConcurrentHashMap<Triangle, Results> getMp() {
        return mp;
    }

    public void addPerimeter(Triangle triangle, Integer perimeter){
        Results res = new Results();
        res.setPerimeter(perimeter);
        mp.put(triangle, res);
    }

    public void addSquare(Triangle triangle, Double square){
        Results res = new Results();
        res.setSquare(square);
        mp.put(triangle, res);
    }

    public int getMapSize(){
        return mp.size();
    }
}
