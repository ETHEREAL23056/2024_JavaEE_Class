package com.my.finalwork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.my.finalwork.mappers")
public class FinalworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalworkApplication.class, args);
    }

}
