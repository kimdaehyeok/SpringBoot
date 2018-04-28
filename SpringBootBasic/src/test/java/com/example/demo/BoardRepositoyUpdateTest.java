package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

/* JPA는 데이터베이스에 바로 작업하는 JDBC와 달리 스스로 엔티티 객체를 메모리(영속 컨텍스트)상에서 관리하고,
 * 필요한 경우 데이터베이스에 작업을 한다. 따라서 수정과 삭제 작업은 데이터베이스에 직접 SQL을 수행하는 것이 아니라
 * 엔티티 객체가 메모리에 먼저 상주하고 있어야 한다. 이 과정을 위해 select가 먼저 수행된다.  
 * */

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardRepositoyUpdateTest 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void updateTest()
	{
		Board board = boardRepo.findById(1L).get();
		
		System.out.println(board.getBno());
		System.out.println(board.getContent());
		System.out.println(board.getWriter());
		
		System.out.println("------------------------------");
		
		board.setWriter("user00");
		System.out.println("Change User");
		
		System.out.println("------------------------------");
		
		System.out.println("Call Save Method!");
		
		boardRepo.save(board);
	}
}
