package com.travel.mybootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.travel.mybootdemo.mapper")
@SpringBootApplication
public class MybootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybootdemoApplication.class, args);
    }

}
