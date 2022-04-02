package com.example.demo.TriangleController;
import com.example.demo.Exceptions.Exceptions;
import com.example.demo.MyLogger.MyLogger;
import com.example.demo.Repos.Repos;
import com.example.demo.Service.Service;
import com.example.demo.Triangle.Triangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
            Triangle triangle = new Triangle(first, second, third);

            if (db.getMapSize() == 0){
                MyLogger.info("map size " + db.getMapSize());
                db.addPerimeter(triangle, Service.calculatePerimeter(triangle));
            }
            else if (db.getMapSize() != 0) {
                MyLogger.info("map size " + db.getMapSize());
                if (db.getOffset(triangle) != 0){
                    db.addPerimeterWithExistingParams(Service.calculatePerimeter(triangle), db.getOffset(triangle));
                }
                else if (db.getPerimeterWithParams(triangle) == 0 || db.getPerimeterWithParams(triangle) == 1) {
                    db.addPerimeter(triangle, Service.calculatePerimeter(triangle));
                }
            }
            return new ResponseEntity(db.getPerimeterWithParams(triangle), HttpStatus.OK);
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
            Triangle triangle = new Triangle(first, second, third);

            if (db.getMapSize() == 0){
                MyLogger.info("map size " + db.getMapSize());
                db.addSquare(triangle, Service.calculateSquare(triangle));
            }
            else if (db.getMapSize() != 0) {
                MyLogger.info("map size " + db.getMapSize());
                if (db.getOffset(triangle) != 0){
                    db.addSquareWithExistingParams(Service.calculateSquare(triangle), db.getOffset(triangle));
                }
                else if(db.getSquareWithParams(triangle) == 0.0){
                    db.addSquare(triangle, Service.calculateSquare(triangle));
                }
            }
            return new ResponseEntity(db.getSquareWithParams(triangle), HttpStatus.OK);
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest httpServletRequest){
        MyLogger.error("Wrong request parameters provided");
        return Exceptions.ResponseReqError(httpServletRequest, HttpStatus.BAD_REQUEST);
    }
}
