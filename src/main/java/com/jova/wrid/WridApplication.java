package com.jova.wrid;


import com.jova.wrid.pluins.MasterUpdateInterceptor;
import com.jova.wrid.pluins.WriteReadInterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.context.annotation.Bean;




@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.jova.wrid.mapper")
public class WridApplication {

    @Bean
    public WriteReadInterceptor writeReadInterceptor(){
        WriteReadInterceptor wridInterceptor = new WriteReadInterceptor();
        return wridInterceptor;
    }

    @Bean
    public MasterUpdateInterceptor masterUpdateInterceptor(){
        MasterUpdateInterceptor masterUpdateInterceptor = new MasterUpdateInterceptor();
        return masterUpdateInterceptor;
    }

    @Autowired
    public static void main(String[] args) {
		SpringApplication.run(WridApplication.class, args);
    }
}
