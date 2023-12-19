package com.example.spring3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    final
    Environment env;

    public HelloController(Environment env) {
        this.env = env;
    }

    @GetMapping("/")
    public String index(){
        return "Greetings from Spring boot!!";
    }

    @GetMapping("/status")
    public String status(){
        return "Status - returned by Pod - " + env.getProperty("HOSTNAME");
    }
}
