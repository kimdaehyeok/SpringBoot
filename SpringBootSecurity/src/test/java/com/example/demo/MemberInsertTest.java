package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRole;
import com.example.demo.repository.MemberRepository;

import lombok.extern.java.Log;

@Log
@Commit
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberInsertTest 
{
	@Autowired
	private MemberRepository memberRepo;
	
	/* Error during managed flush [org.hibernate.TransientObjectException: 
	 * object references an unsaved transient instance - save the transient instance before flushing: com.example.demo.domain.MemberRole] 
	 * 
	 * 엔티티들의 영속관계를 한번에 처리하지 못했기 때문에 발생한 에러이므로, cascade를 설정해준다.
	 * */
	@Test
	@Transactional
	public void memberInsert()
	{
		for(int i = 0; i <=100; i ++)
		{
			Member member = new Member();
			
			member.setUid("user" + i);
			member.setUpw("pw" + i);
			member.setUname("사용자" + i);
			
			List<MemberRole> roleList = new ArrayList<MemberRole>();
			MemberRole role = new MemberRole();
			
			if(i <= 80) 
			{
				role.setRoleName("BASIC");
			}
			else if(i <= 90)
			{
				role.setRoleName("MANAGER");
			}
			else
			{
				role.setRoleName("ADMIN");
			}
			
			roleList.add(role);
			
			member.setRoles(roleList);
			
			memberRepo.save(member);
		}
	}
}
