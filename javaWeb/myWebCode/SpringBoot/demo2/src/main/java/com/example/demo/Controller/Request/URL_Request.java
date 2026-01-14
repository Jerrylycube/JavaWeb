package com.example.demo.Controller.Request;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URL_Request {
    @RequestMapping("path/{id}")
    public String path(@PathVariable String id) {
        return id;
    }

    @RequestMapping("path/{id}/{name}")
    public String[] path(@PathVariable String id, @PathVariable String name) {
        return new String[]{id, name};
    }
}


