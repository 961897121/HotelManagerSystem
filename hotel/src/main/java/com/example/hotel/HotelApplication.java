package com.example.hotel;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelApplication {

    public static void main(String[] args){
//        DOMConfigurator.configure("log4j.xml");
        PropertyConfigurator.configure("E:\\hotel\\src\\main\\java\\log4j.properties");
        SpringApplication.run(HotelApplication.class, args);
    }

}
