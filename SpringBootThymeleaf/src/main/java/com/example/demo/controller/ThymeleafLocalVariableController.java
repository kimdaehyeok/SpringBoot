package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.MemberVO;

/* 특정한 범위에서만 유효한 지역변수를 th:with를 이용해서 선언할 수 있다.
 * */
@Controller
public class ThymeleafLocalVariableController 
{
	@GetMapping("thymeleafLocalVariable")
	public void thymeleafLocalVariable(Model model)
	{
		List<MemberVO> memberList = new ArrayList<>();
		
		for(int i = 0; i < 10; i ++)
		{
			memberList.add(new MemberVO(123, "u000" + i %3, "p000" + i %3, "김대혁." +i, new Timestamp(System.currentTimeMillis())));
		}
		
		model.addAttribute("memberList", memberList);
	}
}
