package com.example.demo.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class EnvironmentWithPropertySource 
{
	@Autowired
	private Environment environment;
	
	public String getValuesFromProperties()
	{
		String email = environment.getProperty("email");
		String name = environment.getProperty("name");
		String gender = environment.getProperty("gender");
		
		return email + " " + name + " " + gender;
	}
}
