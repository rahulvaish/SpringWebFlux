package com.example.SpringBootAsync.controller;

import com.example.SpringBootAsync.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    AppService appService;

    @GetMapping("/getData")
    public String getData(){
        //NOTE: If callMethod was in same controller the execution would be synchronous.
        //When callMethod is in Service, the execution happens asynchronously.
        appService.callMethod();
        return "Data";
    }
}
