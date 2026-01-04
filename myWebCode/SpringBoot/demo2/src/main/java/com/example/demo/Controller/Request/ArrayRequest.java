package com.example.demo.Controller.Request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ArrayRequest {
    @RequestMapping("/Array")
    public String simpleParam(String[] hobby) {
        return Arrays.toString(hobby);
    }
}