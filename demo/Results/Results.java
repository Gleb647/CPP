package com.example.demo.Results;


public class Results {
    private Integer perimeter;
    private Double square;

    public Results() {
        this.perimeter = 0;
        this.square = 0.0;
    }

    public void setPerimeter(Integer perimeter){
        this.perimeter = perimeter;
    }

    public void setSquare(Double square){
        this.square = square;
    }

    public Integer getPerimeter() {
        return perimeter;
    }

    public Double getSquare() {
        return square;
    }
}
