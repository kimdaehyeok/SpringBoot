package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.MemberVO;

/* Thymeleaf의 inline을 이용해서 javascript나 text를 지정해서 사용하는 것이 가능하다.
 * */
@Controller
public class ThymeleafInlineController 
{
	@GetMapping("thymeleafInline")
	public void thymeleafInline(Model model)
	{
		String result = "Result";
		model.addAttribute("result", result);
		
		List<MemberVO> memberList = new ArrayList<>();
		
		for(int i = 0; i < 10; i ++)
		{
			memberList.add(new MemberVO(123, "u0" +i, "p0" +i, "김대혁." +i, new Timestamp(System.currentTimeMillis())));
		}
		
		model.addAttribute("memberList", memberList);
	}
}
