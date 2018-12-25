package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.bean.MyBean;

@Configuration
public class MyConfig 
{
	@Bean
	MyConfigBean myConfigBean () 
	{
        return new MyConfigBean();
    }	
}
