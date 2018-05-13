package com.example.demo.pds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.pdsboard.PDSBoard;
import com.example.demo.pds_repository.PDSFileRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PDSFileTest 
{
	@Autowired
	private PDSFileRepository pdsfileRepo;
	
	@Test
	public void pdsFileTest()
	{
		Iterable<PDSBoard> result = pdsfileRepo.findAll();
		
		for(PDSBoard board : result) 
		{
			System.out.println(board.toString());
		}
	}
}
