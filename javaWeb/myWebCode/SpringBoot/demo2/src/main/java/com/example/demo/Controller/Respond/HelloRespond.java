package com.example.demo.Controller.Respond;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRespond {
    @RequestMapping("/Hello")
    public Result hello() {
        System.out.println("hello");
        return Result.success("hello");
    }
}
