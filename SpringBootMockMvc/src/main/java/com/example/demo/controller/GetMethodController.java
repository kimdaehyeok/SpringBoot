package com.example.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GetMethodController 
{
	@GetMapping("/getNoParamMapping")
	public String getMethodNoParam(Locale locale, Model model)
	{
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "GetMethodNoParam.jsp";
	}
	
	@GetMapping("/getWithParamMapping")
	public String getMethodWithParam(Locale locale
									 , Model model
									 , @RequestParam String param1
									 , @RequestParam String param2)
	{
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "GetMethodWithParam.jsp + param1 : " + param1 + " / param2 : " + param2;
	}
}
