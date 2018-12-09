package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.GetMethodController;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetMethodWithParamTest 
{
	private MockMvc mockMvc;
	
	@Before
	public void Setup()
	{
		mockMvc = MockMvcBuilders.standaloneSetup(new GetMethodController()).build();
	}
	
	@Test
	public void test() throws Exception
	{
		mockMvc.perform(get("/getWithParamMapping?param1=p1&param2=p2")) 
		.andDo(print()) 
		.andExpect(status().isOk()) 
		.andExpect(model().attributeExists("serverTime")); 
	}
}
