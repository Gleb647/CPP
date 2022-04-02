package com.example.demo.TriangleController;
import com.example.demo.Service.Service;
import com.example.demo.Triangle.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Validated
@RestController
public class TriangleController {


    @Autowired
    public TriangleController(){

    }

    @GetMapping("/volume/perimeter")
    public int getPerimeter(@Valid @RequestParam(name = "first", required = false) @NotNull @Min(1) String firstSide, @RequestParam(name = "second", required = false) @NotNull @Min(1) String secondSide, @RequestParam(name = "third", required = false) @NotNull @Min(1) String thirdSide, HttpServletRequest httpServletRequest) {
        int first = Integer.parseInt(firstSide);
        int second = Integer.parseInt(secondSide);
        int third = Integer.parseInt(thirdSide);
        return Service.calculatePerimeter(new Triangle(first, second, third));
    }

    @GetMapping("/volume/square")
    public double getSquare(@Valid @RequestParam(name = "first", required = false) @NotNull @Min(1) String firstSide, @RequestParam(name = "second", required = false) @NotNull @Min(1) String secondSide, @RequestParam(name = "third", required = false) @NotNull @Min(1) String thirdSide, HttpServletRequest httpServletRequest) {

        int first = Integer.parseInt(firstSide);
        int second = Integer.parseInt(secondSide);
        int third = Integer.parseInt(thirdSide);
        return Service.calculateSquare(new Triangle(first, second, third));
    }


}
