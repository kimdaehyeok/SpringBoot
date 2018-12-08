package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.single.SingleValue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SingleValueTest 
{
	@Autowired
	private SingleValue singleValue;
	
	@Test
	public void contextLoads() 
	{
		System.out.println(singleValue.getEmailFromApplicationProperties());
	}
}
