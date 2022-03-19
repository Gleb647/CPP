package com.example.demo.Repository;

import com.example.demo.Results.Results;
import com.example.demo.Triangle.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("singleton")
public class Repository {
    private LinkedHashMap<Triangle, Results> mp = new LinkedHashMap<>();

    public Repository(){}

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
        Integer per = 1;
        int count = 0;
        try {
            if (mp != null) {
                Set<Triangle> trs = mp.keySet();
                Iterator<Triangle> iter = trs.iterator();
                while(iter.hasNext()){
                    Triangle dbtr = iter.next();
                    if (dbtr.getFirstSide() == triangle.getFirstSide() && dbtr.getSecondSide() == triangle.getSecondSide()
                            && dbtr.getThirdSide() == triangle.getThirdSide()){
                        int fl = 0;
                        for (Map.Entry<Triangle, Results> entry : mp.entrySet()) {
                            if (fl == count){
                                per = entry.getValue().getPerimeter();
                            }
                            fl++;
                        }
                    }
                    count++;
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
        int count = 0;
        try {
            if (mp != null) {
                Set<Triangle> trs = mp.keySet();
                Iterator<Triangle> iter = trs.iterator();
                while(iter.hasNext()){
                    Triangle dbtr = iter.next();
                    if (dbtr.getFirstSide() == triangle.getFirstSide() && dbtr.getSecondSide() == triangle.getSecondSide()
                            && dbtr.getThirdSide() == triangle.getThirdSide()){
                        int fl = 0;
                        for (Map.Entry<Triangle, Results> entry : mp.entrySet()) {
                            if (fl == count){
                                per = entry.getValue().getSquare();
                            }
                            fl++;
                        }
                    }
                    count++;
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
