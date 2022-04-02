package com.example.demo.TriangleController;
import com.example.demo.Exceptions.Exceptions;
import com.example.demo.MyLogger.MyLogger;
import com.example.demo.Repos.Repos;
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

    private Repos db;

    @Autowired
    public TriangleController(Repos db){
        this.db = db;
    }

    @GetMapping("/volume/perimeter")
    public ResponseEntity getPerimeter(@Valid @RequestParam(name = "first", required = false) @NotNull @Min(1) String firstSide, @RequestParam(name = "second", required = false) @NotNull @Min(1) String secondSide, @RequestParam(name = "third", required = false) @NotNull @Min(1) String thirdSide, HttpServletRequest httpServletRequest) {
        MyLogger.info("GET from /volume/perimeter");
        int first = Integer.parseInt(firstSide);
        int second = Integer.parseInt(secondSide);
        int third = Integer.parseInt(thirdSide);
        if (!Triangle.canExist(first, second, third)){
            MyLogger.error("Triangle doesn't exist");
            return Exceptions.ResponseServerError(httpServletRequest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            MyLogger.info("map size " + db.getMapSize());
            Triangle tr = new Triangle(first, second, third);
            if (db.getMp().containsKey(tr)){
                if (db.getMp().get(tr).getPerimeter() == 0){
                    db.getMp().get(tr).setPerimeter(Service.calculatePerimeter(tr));
                }
            }
            else {
                db.addPerimeter(tr, Service.calculatePerimeter(tr));
            }
            return new ResponseEntity(db.getMp().get(tr).getPerimeter(), HttpStatus.OK);
        }
    }

    @GetMapping("/volume/square")
    public ResponseEntity getSquare(@Valid @RequestParam(name = "first", required = false) @NotNull @Min(1) String firstSide, @RequestParam(name = "second", required = false) @NotNull @Min(1) String secondSide, @RequestParam(name = "third", required = false) @NotNull @Min(1) String thirdSide, HttpServletRequest httpServletRequest) {
        MyLogger.info("GET from /volume/square");
        int first = Integer.parseInt(firstSide);
        int second = Integer.parseInt(secondSide);
        int third = Integer.parseInt(thirdSide);
        if (!Triangle.canExist(first, second, third)){
            MyLogger.error("Triangle doesn't exist");
            return Exceptions.ResponseServerError(httpServletRequest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            MyLogger.info("map size " + db.getMapSize());
            Triangle tr = new Triangle(first, second, third);
            if (db.getMp().containsKey(tr)){
                if (db.getMp().get(tr).getSquare() == 0.0){
                    db.getMp().get(tr).setSquare(Service.calculateSquare(tr));
                }
            }
            else {
                db.addSquare(tr, Service.calculateSquare(tr));
            }
            return new ResponseEntity(db.getMp().get(tr).getSquare(), HttpStatus.OK);
        }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest httpServletRequest){
        MyLogger.error("Wrong request parameters provided");
        return Exceptions.ResponseReqError(httpServletRequest, HttpStatus.BAD_REQUEST);
    }
}
