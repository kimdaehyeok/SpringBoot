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
public class GetMemberUseProfileTest 
{
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void profileDummyInsertTest()
	{
		List<Object[]> result = memberRepo.getMemberUseProfileInfo("user1");
		
		for(Object[] object : result)
		{
			System.out.println(Arrays.toString(object));
		}
	}
}
