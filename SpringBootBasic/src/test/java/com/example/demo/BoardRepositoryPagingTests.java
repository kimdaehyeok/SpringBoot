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
public class BoardRepositoryPagingTests 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void pagingTest()
	{
		/* Pageable 인터페이스에는 여러 메소드가 존재하기 때문에 이를 구현하는 대신에
		 * PageRequest 클래스를 이용하는 것이 편리하다.
		 * */
		
		// 첫 번째 페이지(인덱스는 0 부터 시작)이고 10 건의 데이터를 가지고 오도록 한다.
		Pageable paging = new PageRequest(0, 10);
		
		List<Board> boradList = boardRepo.findBoardByBnoGreaterThanOrderByBnoDesc(0L,paging);
		
		for(Board board : boradList)
		{
			System.out.println(board.getBno());
		}
		
		/* select board0_.bno as bno1_0_, board0_.content as content2_0_, board0_.regdate as regdate3_0_, board0_.title as title4_0_, board0_.updatedate as updateda5_0_, board0_.writer as writer6_0_
		 *        from tbl_boards board0_ where board0_.bno>? 
		 *             order by board0_.bno desc limit ?
		 *             
		 *  실행되는 SQL을 보면 MySQL이기 때문에 limit가 적용된 것을 볼 수 있다.
		 *  정렬 처리에는 Pageable 인터페이스와 같이 Sort 클래스를 이용할 수 있다.
		 *  Sort는 쿼리 메소드에서 OrderBy로 처리해도 되지만, Sort를 이용하면 원하는 방향을 '파라미터'로 결정할 수 있다.            
		 * */
		
		/* PageRequest(int page, int Size) : 페이지 번호, 페이지당 항목수
		 * PageRequest(int page, int size, Sort.Direction direction, String..props) : 페이지번호, 페이지당 항목 수, 정렬방향, 속성(칼럼)
		 * PageRequest(int page, int size, Sort sort) : 페이지번호, 페이지당 항목 수, 정렬방 
		 * */
	}
}
