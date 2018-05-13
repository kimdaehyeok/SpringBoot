package com.example.demo.freeboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.freeboard_repository.FreeBoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FreeBoardListTest 
{
	@Autowired
	private FreeBoardRepository freeBoardRepo;
	
	@Test
	public void freeBoardList()
	{
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		
		freeBoardRepo.findFreeBoardByBnoGreaterThan(0L, paging).forEach(board -> 
		{
			System.out.println(board.getTitle());
		});
	}
}
