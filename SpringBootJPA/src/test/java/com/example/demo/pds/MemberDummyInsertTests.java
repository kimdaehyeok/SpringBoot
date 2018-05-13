package com.example.demo.pds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.pdsboard.Member;
import com.example.demo.pds_repository.MemberRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@Commit
public class MemberDummyInsertTests 
{
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void profileDummyInsertTest()
	{
		for(int i = 0; i < 101; i ++)
		{
			Member member = new Member();
			
			member.setUid("user" + i);
			member.setUpw("pw" + i);
			member.setUname("사용자" + i);
			
			memberRepo.save(member);
		}
	}
}
