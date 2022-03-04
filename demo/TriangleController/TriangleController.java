package com.example.demo.TriangleController;
import com.example.demo.Triangle.Triangle;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TriangleController {
    private Triangle triangle;

    public TriangleController(@Autowired Triangle triangle){
        this.triangle = triangle;
    }

    @PostMapping("/volume")
    public void postData(@RequestParam(name = "first", required = true) String firstSide, @RequestParam(name = "second", required = true) String secondSide, @RequestParam(name = "third", required = true) String thirdSide) {
        this.triangle.setFirstSide(Integer.parseInt(firstSide));
        this.triangle.setSecondSide(Integer.parseInt(secondSide));
        this.triangle.setThirdSide(Integer.parseInt(thirdSide));
        this.triangle.calculatePerimeter();
        this.triangle.calculateSquare();
    }

    @GetMapping("/volume")
    public Triangle getData(){
        //String json = new Gson().toJson(this.triangle);
        return this.triangle;
    }
}
