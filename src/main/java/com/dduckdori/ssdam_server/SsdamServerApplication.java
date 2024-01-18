package com.dduckdori.ssdam_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SsdamServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsdamServerApplication.class, args);
    }

}
