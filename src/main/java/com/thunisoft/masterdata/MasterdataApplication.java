package com.thunisoft.masterdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.thunisoft.masterdata.dao")
@SpringBootApplication
public class MasterdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterdataApplication.class,args);
    }
}
