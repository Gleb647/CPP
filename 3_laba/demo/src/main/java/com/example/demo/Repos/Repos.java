package com.example.demo.Repos;

import com.example.demo.Results.Results;
import com.example.demo.Triangle.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Component
public class Repos{
    private HashMap<Triangle, Results> mp = new HashMap<>();

    public Repos(){}

    public HashMap<Triangle, Results> getMp() {
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

    public void addPerimeterWithExistingParams(Integer perimeter, int offset){
        int fl = 0;
        for (Map.Entry<Triangle, Results> entry : mp.entrySet()) {
            if (fl == offset - 1){
                if (entry.getValue().getPerimeter() == 0){
                    entry.getValue().setPerimeter(perimeter);
                }
            }
            fl++;
        }
    }
    public void addSquareWithExistingParams(Double square, int offset){
        int fl = 0;
        for (Map.Entry<Triangle, Results> entry : mp.entrySet()) {
            if (fl == offset - 1){
                if (entry.getValue().getSquare() == 0.0){
                    entry.getValue().setSquare(square);
                }
            }
            fl++;
        }
    }

    public int getOffset(Triangle triangle){
        int offset = 0;
        boolean result = false;
        try {
            if (mp != null) {
                Set<Triangle> trs = mp.keySet();
                Iterator<Triangle> iter = trs.iterator();
                while(iter.hasNext()){
                    Triangle dbtr = iter.next();
                    if (dbtr.getFirstSide() == triangle.getFirstSide() && dbtr.getSecondSide() == triangle.getSecondSide()
                            && dbtr.getThirdSide() == triangle.getThirdSide()){
                        result = true;
                        offset++;
                        break;
                    }
                    offset++;
                }
            }
        }
        catch(NullPointerException e){
            result = false;
        }
        if (!result){
            offset = 0;
        }
        return offset;
    }

    public Integer getPerimeterWithParams(Triangle triangle){
        Integer per = 0;
        int count = getOffset(triangle);
        int fl = 0;
        try {
            if (count != 0) {
                for (Map.Entry<Triangle, Results> entry : mp.entrySet()) {
                    if (fl == count - 1) {
                        per = entry.getValue().getPerimeter();
                    }
                    fl++;
                }
            }
        }
        catch(NullPointerException e){
                per = 0;
        }
        return per;
    }

    public Double getSquareWithParams(Triangle triangle){
        Double per = 0.0;
        int count = getOffset(triangle);
        int fl = 0;
        try {
            if (count != 0) {
                for (Map.Entry<Triangle, Results> entry : mp.entrySet()) {
                    if (fl == count - 1) {
                        per = entry.getValue().getSquare();
                    }
                    fl++;
                }
            }
        }
        catch(NullPointerException e){
            per = 0.0;
        }
        return per;
    }

    public int getMapSize(){
        return mp.size();
    }
}
