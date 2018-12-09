package com.example.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostMethodController 
{

	@PostMapping("/postNoParamMapping")
	public String postMethodNoParam(Locale locale, Model model)
	{
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "postMethodNoParam.jsp";
	}
	
	@PostMapping("/postWithParamMapping")
	public String postMethodWithParam(Locale locale
									 , Model model
									 , @RequestParam String param1
									 , @RequestParam String param2)
	{
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "postMethodWithParam.jsp + param1 : " + param1 + " / param2 : " + param2;
	}
}
