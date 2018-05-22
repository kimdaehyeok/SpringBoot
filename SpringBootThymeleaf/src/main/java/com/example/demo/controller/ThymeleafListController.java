package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.MemberVO;

@Controller
public class ThymeleafListController 
{
	@GetMapping("thymeleafList")
	public void thymeleafList(Model model)
	{
		List<MemberVO> memberList = new ArrayList<>();
		
		for(int i = 0; i < 10; i ++)
		{
			memberList.add(new MemberVO(123, "u0" +i, "p0" +i, "김대혁." +i, new Timestamp(System.currentTimeMillis())));
		}
		
		model.addAttribute("memberList", memberList);
	}
}
