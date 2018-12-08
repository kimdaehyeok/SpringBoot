package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.configurationproperties.ConfigurationPropertiesExam;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigurationPropertiesExamTest 
{
	@Autowired
	private ConfigurationPropertiesExam configurationPropertiesExam;
	
	@Test
	public void test()
	{
		System.out.println(configurationPropertiesExam.getMenus().get(0).toString() );
		System.out.println(configurationPropertiesExam.getMenus().get(1).toString() );
		System.out.println(configurationPropertiesExam.getCompiler().toString() );
		System.out.println(configurationPropertiesExam.getSuccess() );
		System.out.println(configurationPropertiesExam.getError() );
		
	}
}
