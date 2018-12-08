package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.environment.EnvironmentWithPropertySource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EnvironmentWithPropertySourceTest 
{
	@Autowired
	private EnvironmentWithPropertySource environmentWithPropertySource;
	
	@Test
	public void environmentWithPropertySourceTest()
	{
		System.out.println(environmentWithPropertySource.getValuesFromProperties());
	}
}
