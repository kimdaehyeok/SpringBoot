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
public class GetMethodNoParamTest 
{
	private MockMvc mockMvc;
	
	// 테스트 메소드 실행 전 셋업 메소드
	@Before
	public void Setup()
	{
		// HomeController의 객체를 MockMvc 객체로 만든다.
		this.mockMvc = MockMvcBuilders.standaloneSetup(new GetMethodController()).build();
	}
	
	@Test
	public void contextLoads() throws Exception 
	{
		mockMvc.perform(get("/getNoParamMapping")) //HomeController의 "/SpringBootMockMvc/getMapping"로 매핑한다.
		.andDo(print()) //처리 내용을 출력한다.
		.andExpect(status().isOk()) //상태값은 ok가 나와야 한다.
		.andExpect(model().attributeExists("serverTime")); // "serverTime"이라는 attribute가 존재해야 합니다.
	}
}
