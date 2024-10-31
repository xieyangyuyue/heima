package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value={"com.itheima.mapper","com.itheima"})
public class BigEventApplication {

    public static void main(String[] args) {

        SpringApplication.run(BigEventApplication.class, args);
    }

}
