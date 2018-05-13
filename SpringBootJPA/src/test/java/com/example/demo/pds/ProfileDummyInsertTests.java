package com.example.demo.pds;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.pdsboard.Member;
import com.example.demo.domain.pdsboard.Profile;
import com.example.demo.pds_repository.ProfileRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProfileDummyInsertTests 
{	
	@Autowired
	private ProfileRepository profileRepo;
	
	@Test
	public void profileDummyInsertTest()
	{
		Member member = new Member();
		member.setUid("user1");
		
		for(int i = 1; i < 5; i ++)
		{
			Profile profile1 = new Profile();
			profile1.setFname("face" + i + ".jpg");
			
			if(i == 1)
			{
				profile1.setCurrent(true);
			}
			else
			{
				profile1.setCurrent(false);
			}

			profile1.setMember(member);
			
			profileRepo.save(profile1);
		}
	}
}