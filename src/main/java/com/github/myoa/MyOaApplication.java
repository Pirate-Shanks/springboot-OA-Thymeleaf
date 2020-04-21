package com.github.myoa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.myoa.mapper")
public class MyOaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyOaApplication.class, args);
    }

}
