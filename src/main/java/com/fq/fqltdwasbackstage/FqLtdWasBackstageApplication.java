package com.fq.fqltdwasbackstage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fq.fqltdwasbackstage.mapper")
public class FqLtdWasBackstageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FqLtdWasBackstageApplication.class, args);
    }
}
