package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.example.demo"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpringBootConfigurationPropertiesApplication
{
	public static void main(String[] args) 
	{
		SpringApplication.run(SpringBootConfigurationPropertiesApplication.class, args);
	}
}
