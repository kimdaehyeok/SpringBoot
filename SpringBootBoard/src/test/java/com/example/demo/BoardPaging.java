package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.WebBoard;
import com.example.demo.persistence.WebBoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardPaging 
{
	@Autowired
	private WebBoardRepository boardRepo;
	
	@Test
	public void boardPaging()
	{
		Pageable paging = PageRequest.of(0, 20, Direction.DESC, "bno");
		
		Page<WebBoard> result = boardRepo.findAll(boardRepo.makePredicate("t", "10"), paging);

		for(WebBoard board : result)
		{
			System.out.println(board.getTitle());
		}
	}
}
