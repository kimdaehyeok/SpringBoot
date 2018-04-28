package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

//@SpringBootApplication =  @Configuration + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
@ComponentScan(basePackages= {"com.example.demo"})
public class SpringBootBasicApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(SpringBootBasicApplication.class, args);
	}
}
