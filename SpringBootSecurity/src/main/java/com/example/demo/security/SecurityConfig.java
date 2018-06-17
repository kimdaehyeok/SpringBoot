package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.java.Log;

/* https://docs.spring.io/spring-security/site/docs/5.0.5.RELEASE/reference/htmlsingle/#security-filter-chain
 * https://gist.github.com/arandilopez/c6b92e50618e425497cc
 * https://github.com/spring-projects/spring-security/blob/master/web/src/main/java/org/springframework/security/web/SecurityFilterChain.java
 * */
@Log
@EnableWebSecurity // 아무런 어노테이션이 없다면, 스프링 빈(Bean)으로 인식되지 않으므로, SecurityConfig가 인식되도록 어노테이션을 추가함
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter // 설정을 담당하는 클래스를 상속한다.
{
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private CustomUserService customUserService;

	/*
	 * configure() 메소드를 오버라이드 하는 경우에는 HttpSecurity 클래스 타입을 르 파라미터로 처리하는 메소드를 선택해야
	 * 한다.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		// TODO Auto-generated method stub
		// super.configure(http);

		log.info("Security Configuration..................");

		// authorizeRequests는 시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미한다.
		http.authorizeRequests().antMatchers("/guest/**").permitAll();

		http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");

		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

		http.formLogin().loginPage("/login");

		http.exceptionHandling().accessDeniedPage("/accessDenied");

		/*
		 * 스프링 시큐리티가 웹을 처리하는 방식의 기본은 HttpSession이므로, 브라우저가 완전히 종료된다면 로그인한 정보를 잃게 된다.
		 * 브라우저가 종료되지 않은 경우, 사용자가 로그아웃을 행해서 자신이 로그인했던 모든 정보를 삭제해야 한다.
		 */
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
//	    http.userDetailsService(customUserService);
	}

	/*
	 * 모든 인증은 인증매니저(AuthenticationManager)를 통해 이루어진다. 인증 매니저를 생성하기 위해서는 인증 매니저
	 * 빌더(AuthenticationManagerBuilder)라는 것이 존재한다.
	 * 
	 * 인증 매니저를 이용해서 인증(Authentication) 이라는 작업이 수행된다.
	 * 
	 * 인증 매니저들은 인증/인가를 위한 UserDetailsService를 통해서 필요한 정보들을 가지고 온다.
	 * UserDetailsService에는 loadUserByUsername 메소드가 존재한다.
	 * 
	 * UserDetails는 사용자의 정보와 권한 정보들의 묶음이다.
	 * 
	 * 스프링 시큐리티는 기본적으로 많이 사용되는 인증 매니저들과 UserDetailsService를 구현한 클래스들이 다수 존재함
	 */
	
	// eGlobalAuthenticationAutowiredConfigurer : Eagerly initializing {securityConfig=com.example.demo.security.SecurityConfig$$EnhancerBySpringCGLIB$$12e5bc4f@4c3fde9
	@Autowired 
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}1111").roles("ADMIN");
		
//		String queryForUser = "SELECT uid as username, upw as password, true as enabled FROM tbl_members WHERE uid =?";
//		String queryForRole = "SELECT member as uid, role_name as role FROM tbl_member_roles WHERE member = ?";
		
		/* org.springframework.security.core.userdetails.User@ce2b2c2b: 
		 * 
		 * Username: user99; Password: [PROTECTED]; Enabled: true; 
		 * AccountNonExpired: true; credentialsNonExpired: true; 
		 * AccountNonLocked: true; Granted Authorities: ROLE_ADMIN; 
		 * Credentials: [PROTECTED]; Authenticated: true; 
		 * Details: org.springframework.security.web.authentication.WebAuthenticationDetails@fffe9938: 
		 * RemoteIpAddress: 0:0:0:0:0:0:0:1; 
		 * SessionId: 9DEF38EADDE40A32E56737876FE5226F; 
		 * Granted Authorities: ROLE_ADMIN'
		 * 
		 * 로그인에 성공하면 org.springframework.security.core.userdetails.User타입의 다양한 속성을 가진 객체가 만들어 지는 것을 확인할 수 있다.
		 */
//		auth.jdbcAuthentication().dataSource(datasource).usersByUsernameQuery(queryForUser).authoritiesByUsernameQuery(queryForRole).rolePrefix("ROLE_");
		
		auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
	}
	
	/* 스프링 시큐리티는 패스워드를 쉽게 암호화할수 있는 PasswordEncoder라는 인터페이스를 기준으로 제공하고 있다.
	 * API 상에서 PasswordEncoder, NoOpPasswordEncoder, Pbkdf2PasswordEncoder, SCryptPasswordEncorder
	 * StandardPasswordEncoder 등이 있다.
	 * 
	 * PasswordEncoder를 적용하는 방식은
	 * 1) 구현 클래스를 작성한다.
	 * 2) 시큐리티 설정에 추가한다.
	 * 3) 관련 컨트롤러나 서비스와 연동한다.
	 * 
	 * 구현 클래스는 이미 API로 제공되기 때문에, 2단계부터 바로 진행할 수 있다.
	 * */
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
