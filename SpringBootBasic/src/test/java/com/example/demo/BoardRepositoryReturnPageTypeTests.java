package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardRepositoryReturnPageTypeTests 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void returnPageTypeTest()
	{
		Pageable paging = new PageRequest(0, 10, Sort.Direction.ASC, "bno");
		
		Page<Board> result = boardRepo.findBoardByBnoGreaterThanEqual(0L, paging);
		
		System.out.println("Page Size : " + result.getSize());
		System.out.println("Total Page : " + result.getTotalPages());
		System.out.println("Total Count : " + result.getTotalElements());
		System.out.println("Next : " + result.nextPageable());
		
		List<Board> boardList = result.getContent();
		
		for(Board board : boardList)
		{
			System.out.println(board.getContent());
		}
	}
}