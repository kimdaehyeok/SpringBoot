package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRole;
import com.example.demo.repository.MemberRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberReadTest 
{
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void memberReadTest()
	{
		Optional<Member> result = memberRepo.findById("user99");
		List<MemberRole> roles = result.get().getRoles();
		
		System.out.println(result.get().getUid());
		System.out.println(result.get().getUname());
		System.out.println(result.get().getUpw());
		
		for(MemberRole role : roles)
		{
			System.out.println(role.getRoleName());
		}
	}
}
