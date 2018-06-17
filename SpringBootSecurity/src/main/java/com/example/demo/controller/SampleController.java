package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Member;
import com.example.demo.modelattribute.Sample;
import com.example.demo.repository.MemberRepository;

import lombok.extern.java.Log;

/* 인증(Authentication)은 증명하다 라는 의미로 자격이나 신분을 나타낸다.
 * 인가(Authorization)은 권한부여, 허가의 의미로 보안에서는 특정 목적을 실현하도록 허용(Access)를 의미한다.
 * */
@Log
@Controller
public class SampleController 
{
	@Autowired
	private PasswordEncoder pwEncoder;
	
	@Autowired 
	private MemberRepository memberRepo;
	
	@GetMapping("/")
	public String index()
	{
		log.info("******* Log : index");
		return "index";
	}
	
	/* @Secured를 통해 컨트롤러에 권한에 대한 보안 설정을 할 수 있다.
	 * SecurityConfig 에 @EnableGlobalMethodSecurity(securedEnabled = true)를 추가한다.
	 * */ 
	@Secured(value = {"ROLE_ADMIN"})
	@GetMapping("/guest")
	public String guest()
	{
		log.info("******* Log : guest");
		return "guest";
	}
	
	@GetMapping("/manager")
	public String manager()
	{
		log.info("******* Log : manager");
		return "manager";
	}
	
	@GetMapping("/admin")
	public String admin()
	{
		log.info("******* Log : admin");
		return "admin";
	}
	
	/* 스프링 시큐리티가 적용되면, Post로 전송되는 모든 데이터는 CSRF 토근 값이 필요하다.
	 * 이 값이 없는 경우 "Invalid CSRF token found for http://localhost:8080/login" 와 같은 에러가 발생함.
	 * CSRF 토큰을 사용하지 않으려면, application.properties에 security.enable-csrf 속성을 이용해 사용하지 않도록 할 수 있다.
	 * */
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/accessDenied")
	public String accessDeny()
	{
		return "accessDeny";
	}
	
	@GetMapping("/logout")
	public String logout()
	{
		return "logout";
	}
	
	/* @ModelAttribute는 메소드와 메소드 인자에서 스프링의 Model 객체에 모델 특성을 저장 및 검색하는 역할을 한다.
	 * 모델 특성 : 양식 지원 객체나 참조 데이터이다.
	 *  
	 * 1. 메소드에 @ModelAttribute를 지정하면 해당 메소드가 Model 객체로 하나 이상의 모델 특성을 추가한다는 의미이다.
	 * 2. 메소드 인자에 @ModelAttribute를 지정하면 Model 객체에서 모델 특성을 가지고 오고 메소드 인자로 할당한다.
	 * 
	 * @ModelAttribute에서 value 특성을 지정하지 않은 경우 반환된 객체의 형식을 이름으로 사용한다.
	 * */
	
	@GetMapping("/join")
	public String join()
	{
		return "/member/join";
	}
	
	@Transactional
	@PostMapping("/join")
	public String joinPost(@ModelAttribute("member") Member member)
	{
		String encryptPw = pwEncoder.encode(member.getUpw());
		
		member.setUpw(encryptPw);
		
		memberRepo.save(member);
		
		return "/member/joinResult";
	}

	// 아래와 같이 @ModelAttribute를 통해 모델 특성을 직접 Model 객체로 추가할 수 있다.
	@ModelAttribute
	public void doSomething(Model model)
	{
		model.addAttribute("msg", "model Attribute Message");
	}

	@ModelAttribute(value = "personSample")
	public Sample addSample()
	{
		Sample sample = new Sample();
		
		sample.setGender("man");
		sample.setName("kdh");
		sample.setPhone("010-1234-5678");
		
		return sample;
	}
}
