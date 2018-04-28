package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/* 기존의 Spring MVC와 Restful 웹 서비스의 차이는 http response body가 생성되는 방식의 차이이다.
 * @Controller : 주된 용도는 화면(View)를 리턴하는 것이다.
 * @RestController : 데이터를 리턴하는 것이다. 객체 데이터는 JSON/XML 형식의 HTTP로 전송된다.
 * */

@RestController
public class SampleController 
{
	@GetMapping("/hello")
	public String sayHello()
	{
		return "hello world";
	}
}
