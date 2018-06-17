package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/* 과거에는 web.xml에 스프링과 같은 웹 어플리케이션 컨텍스트를 구성하는 작업을 했다.
 * 대표적으로 서블릿 어플리케이션 컨텍스트(DispatcherServlet)를 등록하는 과정이다.
 * 그러나 서블릿 3.0부터는 web.xml 없이 배포가 가능해졌으며, 스프링은 서블릿 3.0 이상부터 web.xml을 프로그래밍 적으로 다룰 수 있게 된다.
 * web.xml 역할을 하는 것이 WebApplicationInitializer 인터페이스다.
 * SpringBootServletInitializer가 WebApplicationInitializer의 구현체이다.
 * 그러므로, Tomcat과 같은 서블릿 컨테이너에서 스프링 부트가 사용가능해지도록 설정해주는 역할을 한다.
 * */

/* <web-app>
		<servlet>
		  <servlet-name>dispatcher</servlet-name>
		  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		 </servlet>
		 
		 <servlet-mapping>
		  <servlet-name>dispatcher</servlet-name>
		  <url-pattern>*.do</url-pattern>
		 </servlet-mapping>
		</web-app>
 * */
public class ServletInitializer extends SpringBootServletInitializer 
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
		return application.sources(SpringBootSecurityApplication.class);
	}
}
