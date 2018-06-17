package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRole;
import com.example.demo.repository.MemberRepository;

/* 스프링 시큐리티는 인증 매니저(AuthenticationManager)가 UserDetailsService를 통해 인증 작업을 수행한 뒤 
 * org.springframework.security.core.userdetails.USER 타입의 객체를 반환한다.
 * USER 클래스는 UserDetails라는 인터페이스를 구현해서 상세한 사용자 정보를 처리한다.
 * 
 * 그러므로, 개발자가 인증 매니저를 커스터마이징 하기 위해서는 UserDetailsService 인터페이스를 구현하고
 * 이를 HttpSecurity가 사용할 수 있도록 지정하면 된다.
 * */
@Service
public class CustomUserService implements UserDetailsService 
{
    @Autowired
    private MemberRepository memberRepo;
    
    private	List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
//		roles.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
		
		Member member = memberRepo.findById(username).get();
		List<MemberRole> roleFromDatabase = member.getRoles();
		
		for(MemberRole role : roleFromDatabase)
		{
			roles.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		}
		
		User sampleUser = new User(username, member.getUpw(), roles);
		
		return sampleUser;
	}

}
