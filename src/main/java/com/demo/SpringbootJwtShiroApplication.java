package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.demo.dao")//在这里添加之后就不需要在每个dao添加mapper
public class SpringbootJwtShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJwtShiroApplication.class, args);
    }

}
