package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.SampleController;

@RunWith(SpringRunner.class)
//@SpringBootTest -- 기존 주석 처리.
@WebMvcTest(SampleController.class)
/* @WebMvcTest 어노테이션을 사용하면 @Controller, @Component, @ControllerAdvice 등이 작성된 코드를 인식할 수 있다.
 * */
public class SpringBootBasicApplicationTests {

	/* 컨트롤러를 테스트 하기 위해서는 org.springframework.test.web.servlet.MockMvc 타입의 객체를 사용해야 한다.
	 * @WebMvcTest를 사용하면 별도의 생성 없이 주입(@Autowired)만으로 코드를 작성할 수 있다.
	 * */
	@Autowired
	MockMvc mock;
	
	@Test
	public void contextLoads() {
	}

}
