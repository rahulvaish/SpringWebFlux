package com.example.HelloWorldApp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class HelloWorldController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getHelloWorld")
    public String getHelloWorld() {
        try{
            Thread.sleep(10000);
        }catch (Exception e){
            System.out.println("EXCEPTION" + e);
        }
        return "------HELLO WORLD------ " + port;
    }
}
