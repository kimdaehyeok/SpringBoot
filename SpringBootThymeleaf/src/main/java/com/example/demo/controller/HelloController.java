package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/* application.properties에서 별도의 확장자를 지정하지 않았다면 ".html"이 확장자로 사용된다.
 * */
@Controller
public class HelloController 
{
	@GetMapping("/helloworld")
	public void hello(Model model)
	{
		model.addAttribute("greeting","안녕하세요.");
	}
}
