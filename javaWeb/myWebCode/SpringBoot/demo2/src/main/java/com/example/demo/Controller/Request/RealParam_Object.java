package com.example.demo.Controller.Request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RealParam_Object {
    @RequestMapping("/Object")
    public String simpleParam(User user) {
        return user.toString();
    }
}
