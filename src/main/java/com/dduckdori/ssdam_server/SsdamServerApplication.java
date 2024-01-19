package com.dduckdori.ssdam_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class SsdamServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsdamServerApplication.class, args);
    }

}
