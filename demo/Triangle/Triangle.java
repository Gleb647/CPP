package com.example.demo.Triangle;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class Triangle {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int firstSide;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int secondSide;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int thirdSide;
    private int perimeter;
    private double square;

    public Triangle(){}

    public Triangle(int firstSide, int secondSide, int thirdSide){
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    public int calculatePerimeter(){
        return this.perimeter = this.firstSide + this.secondSide + this.thirdSide;
    }

    public double calculateSquare(){
        double semiPerimeter;
        semiPerimeter = (firstSide + secondSide + thirdSide) / 2;
        this.square = Math.sqrt(semiPerimeter * Math.abs(semiPerimeter - firstSide) * Math.abs(semiPerimeter - secondSide) *
                Math.abs(semiPerimeter - thirdSide));
        return this.square;
    }

    public int getFirstSide() {
        return firstSide;
    }

    public void setFirstSide(int firstSide) {
        this.firstSide = firstSide;
    }

    public int getSecondSide() {
        return secondSide;
    }

    public void setSecondSide(int secondSide) {
        this.secondSide = secondSide;
    }

    public int getThirdSide() { return thirdSide; }

    public void setThirdSide(int thirdSide) {
        this.thirdSide = thirdSide;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }
}
