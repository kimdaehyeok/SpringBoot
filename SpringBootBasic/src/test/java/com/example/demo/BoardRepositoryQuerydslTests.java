package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Board;
import com.example.demo.entity.QBoard;
import com.example.demo.repository.BoardRepositoryQuerydsl;
import com.querydsl.core.BooleanBuilder;

/* Predicate는 단언하다, 확신하다는 의미이다.
 * 즉 boolean으로 리턴되는 결과 데이터를 만들어야 한다.
 * 이 때 주로 BooleanBuilder를 이용하여 생성한다.
 * */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardRepositoryQuerydslTests {
	@Autowired
	private BoardRepositoryQuerydsl boardRepo;

	@Test
	public void querydslTest()
	{
		String type = "t";
		String keyword = "17";
		
		/* Predicate는 주로 BooleanBuilder를 이용해 생성한다.
		 * Predicate는 필요한 곳에서 생성하는 방식을 이용하기도 하지만, 별도의 클래스 등을 만들어 사용할 수 있다. 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		
		/* Querydsl을 이용하는 경우 Entity 클래스는 Querydsl에서 사용하는 '쿼리 도메인 클래스(QDomain)'라는 것을 생성해야 한다.
		 * QDomain은 pom.xml의 plugin에서 설정할 수 있다. 
		 * */
		QBoard board = QBoard.board;
		
		if(type.equalsIgnoreCase("t"))
		{
			builder.and(board.title.like("%" + keyword + "%"));
		}
		
		builder.and(board.bno.gt(0L));
		
		Pageable paging = new PageRequest(0, 10);
		
		Page<Board> result = boardRepo.findAll(builder, paging);
		
		for(Board boardObj : result)
		{
			System.out.println(boardObj.getBno());
			System.out.println(boardObj.getContent());
		}
	}
}
