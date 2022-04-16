package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;


public class Exceptions extends RuntimeException{
    public Exceptions() {}

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public static ResponseEntity ResponseServerError(HttpServletRequest httpServletRequest, HttpStatus status) {
        Map<String, Object> response = new LinkedHashMap<>();
        final LocalDate date = LocalDate.now();
        final String error = "Triangle doesn't exist";
        final String path = httpServletRequest.getRequestURI();
        response.put("timestamp", date);
        response.put("status", status.value());
        response.put("error", error);
        response.put("path", path);
        return new ResponseEntity(response, status);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static ResponseEntity ResponseReqError(HttpServletRequest httpServletRequest, HttpStatus status) {
        Map<String, Object> response = new LinkedHashMap<>();
        final LocalDate date = LocalDate.now();
        final String error = "Validation error";
        final String path = httpServletRequest.getRequestURI();
        response.put("timestamp", date);
        response.put("status", status.value());
        response.put("error", error);
        response.put("path", path);
        return new ResponseEntity(response, status);
    }
}
