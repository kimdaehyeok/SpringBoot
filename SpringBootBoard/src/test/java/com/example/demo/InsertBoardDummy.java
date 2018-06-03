package com.example.demo;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.WebBoard;
import com.example.demo.persistence.WebBoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InsertBoardDummy 
{
	@Autowired
	private WebBoardRepository boardRepo;
	
	@Test
	public void insertBoardDummy()
	{
		IntStream.range(0, 300).forEach(i -> 
		{
			WebBoard webBoard = new WebBoard();
			
			webBoard.setTitle("Sample Title " + i);
			webBoard.setContent("Sample Content " + i);
			webBoard.setWriter("User0" + (i%10));
			
			boardRepo.save(webBoard);
		});
	}
}
