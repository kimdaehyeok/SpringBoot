package com.example.demo.pds;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.pds_repository.MemberRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetMemberProfileTests 
{
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void getMemberProfileTest()
	{
		List<Object[]> result = memberRepo.getMemberWithProfileCount("user1");
		
		for(Object[] obj : result)
		{
			System.out.println(Arrays.toString(obj));
		}
	}
}
