package com.example.demo.single;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class SingleValue 
{
	@Value("${email}")
	private String emailFromApplicationProperties;
	
	public String getEmailFromApplicationProperties()
	{
		return emailFromApplicationProperties;
	}
}
