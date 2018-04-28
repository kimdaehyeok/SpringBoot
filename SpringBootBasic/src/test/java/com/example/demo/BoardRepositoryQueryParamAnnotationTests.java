package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardRepositoryQueryParamAnnotationTests 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void paramAnnotationTest()
	{
		List<Board> boardList = boardRepo.findBoardByContent("17");
		
		for(Board board : boardList)
		{
			System.out.println(board.getBno());
			System.out.println(board.getContent());
		}
	}
}
