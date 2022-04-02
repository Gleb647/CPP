package com.example.demo.Triangle;

import com.example.demo.Repos.Repos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.Objects;

@Component
public class Triangle {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int firstSide;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int secondSide;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int thirdSide;

    public Triangle(){}

    public Triangle(int firstSide, int secondSide, int thirdSide){
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return firstSide == triangle.firstSide && secondSide == triangle.secondSide && thirdSide == triangle.thirdSide;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstSide, secondSide, thirdSide);
    }

    public static boolean canExist(int first, int second, int third){
        return (first + second > third && first + third > second && second + third > first);
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

}
