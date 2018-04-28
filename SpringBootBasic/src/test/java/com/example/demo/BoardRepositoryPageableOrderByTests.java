package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardRepositoryPageableOrderByTests 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void pageableOrderByTest()
	{
		// bno 속성값을 순서대로 정렬한다.
		Pageable paging = new PageRequest(0,10, Sort.Direction.ASC, "bno");
		
		List<Board> boardList = boardRepo.findBoardByBnoGreaterThan(0L, paging);
		
		for(Board board : boardList)
		{
			System.out.println(board.getBno() + " " + board.getTitle());
		}
	}
}
