package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardRepositoryDeleteTests 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void deleteTest()
	{
		Board board = new Board();
		
		board.setBno(1L);
		
		boardRepo.delete(board);
	}
}
