package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

/* Spring Boot JPA는 내부적으로 Hibernate라는 구현체를 사용한다.
 * Hibernate는 지정되는 데이터베이스에 맞게 SQL문을 생성하는 Dialect(방언)이 존재한다.
 * 내부적으로 OracleDialect, MySQLDialect 등 다수가 존재한다.
 * 내부적으로 SQL을 생성하기 때문에, SQL 튜닝 작업이 어렵다.
 * 조회 작업에는 내부적으로 1차 캐시 안에 엔티티가 존재하는지 확인하고, 없다면 SQL문을 통해 데이터베이스에서 가지고 온다.
 * */

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardRepositorySelectTests 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void selectTest()
	{
		List<Board> boardList = (List<Board>) boardRepo.findAll();
		
		for(Board board : boardList)
		{
			System.out.println(board.getBno());
			System.out.println(board.getTitle());
			System.out.println(board.getContent());
		}
	}
}
