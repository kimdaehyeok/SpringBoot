package com.example.demo.pds;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.pds_repository.PDSFileCascadingRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PDSBoardLeftOuterJoin 
{
	@Autowired
	private PDSFileCascadingRepository pdsFileRepo;
	
	@Test
	public void leftOuterJoinTest()
	{
		pdsFileRepo.getSummary().forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
}
