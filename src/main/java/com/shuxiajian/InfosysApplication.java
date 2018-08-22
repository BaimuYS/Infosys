package com.shuxiajian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.shuxiajian.frame.mapper")
public class InfosysApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(InfosysApplication.class, args);
        DataSource dataSource = context.getBean(DataSource.class);

    }


}
