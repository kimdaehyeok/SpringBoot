package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boot")
public class ThymeleafLinkController 
{
	@RequestMapping("/thymeleafLink")
	public void thymeleafLink(Model model)
	{
		
	}
}
