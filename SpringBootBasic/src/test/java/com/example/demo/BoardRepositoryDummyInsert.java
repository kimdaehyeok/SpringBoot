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
public class BoardRepositoryDummyInsert 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void insertDummy()
	{
		for(int i = 0; i <=200; i ++)
		{
			Board board = new Board();
			
			board.setTitle("제목 : " + i);
			board.setContent("내용 : " + i);
			board.setWriter("user0" + (i%10));
			
			boardRepo.save(board);
		}
	}
}
