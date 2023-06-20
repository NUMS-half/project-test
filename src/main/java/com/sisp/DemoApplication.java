package com.sisp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sisp.dao")
public class DemoApplication {

    //wyx edit version
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    ////
}
