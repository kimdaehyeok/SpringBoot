package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.repository.BoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardRepositoryNativeQueryTests 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void nativeQueryTest()
	{
		List<Object[]> boardList = boardRepo.findBoardByTitleByUsingNativeQuery("17");
		
		for(Object[] board : boardList)
		{
			System.out.println(Arrays.toString(board));
		}
	}
}
