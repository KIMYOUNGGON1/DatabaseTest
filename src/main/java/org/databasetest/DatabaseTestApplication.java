package org.databasetest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.databasetest.mapper")
public class DatabaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseTestApplication.class, args);
    }

}
