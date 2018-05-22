package com.example.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafUtilityObjectController 
{
	@GetMapping("thymeleafUtility")
	public void thymeleafUtility(Model model)
	{
		model.addAttribute("now", new Date());
		model.addAttribute("price", 123456789);
		model.addAttribute("title", "This is a thymeleaf utility sample");
		
	}
}
