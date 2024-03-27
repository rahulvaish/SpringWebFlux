package com.example.SpringBootAsync.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Service
public class AppService {

    @Async
    public void callMethod() {
        try {
            Thread.sleep(10000);
            System.out.println("INSIDE CALLMETHOD");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}