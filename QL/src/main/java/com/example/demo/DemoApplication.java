package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@ComponentScan(basePackages ={"com.example.demo.Controller"})
public class DemoApplication {
	public static void main(String [] args){
		SpringApplication.run(DemoApplication.class,args);
	}
}
