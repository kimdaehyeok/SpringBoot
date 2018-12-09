package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controller.PostMethodController;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostMethodWithParamTest 
{
	private MockMvc mockMvc;
	
	@Before
	public void Setup()
	{
		mockMvc = MockMvcBuilders.standaloneSetup(new PostMethodController()).build();
	}
	
	@Test
	public void test() throws Exception
	{
		mockMvc.perform(post("/postWithParamMapping").param("param1", "p1").param("param2", "p2"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("serverTime"));
	}
}
