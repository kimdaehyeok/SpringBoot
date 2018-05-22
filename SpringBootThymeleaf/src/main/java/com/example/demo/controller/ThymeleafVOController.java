package com.example.demo.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.MemberVO;

@Controller
public class ThymeleafVOController 
{
	@GetMapping("thymeleafVo")
	public void thymeleafVo(Model model)
	{
		MemberVO member = new MemberVO(123,"u00","p00","김대혁.",new Timestamp(System.currentTimeMillis()));
		
		model.addAttribute("member", member);
	}
}
