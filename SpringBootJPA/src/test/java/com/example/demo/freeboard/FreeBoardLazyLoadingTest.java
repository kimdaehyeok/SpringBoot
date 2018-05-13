package com.example.demo.freeboard;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.freeboard_repository.FreeBoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FreeBoardLazyLoadingTest 
{
	@Autowired
	private FreeBoardRepository freeBoardRepo;
	
	/* 영속성 컨텍스트란 엔티티(Entity)를 영구 저장 하는 환경을 말한다. 
	 * 엔티티 매니저로 엔티티를 저장하거나 조회하면 엔티티 매니저는 영속성 컨텍스트에 엔티티를 보관하고 관리한다.
	 * 
	 * 영속성 컨텍스트는 엔티티 매니저(Session)를 생성할 때 하나 만들어진다. 
	 * 그리고 엔티티 매니저(Session)를 통해서 영속성 컨텍스트에 접근할 수 있고 영속성 컨텍스트를 관리 할 수 있다
	 * 
	 * 여러 엔티티 매니저(Session)가 같은 영속성 컨텍스트에 접근할 수도 있다.
	 * 하나의 엔티티 매니저에 하나의 영속성 컨텍스트가 만들어진다고 생각하면 된다.
	 * */
	
	/* org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role:
	 * com.example.demo.domain.freeboard.FreeBoard.replies, could not initialize proxy - no Session
	 *  
	 * JPA는 연관관계가 있는 엔티티를 조회할 때, 기본적으로 지연 로딩(Lazy loading)을 한다.
	 * 지연 로딩이란 정보가 필요하기 전까지 최대한 테이블에 접근하지 않는 방식을 말한다.
	 * 이를 해결하기 위해 연관된 컬럼을 즉시 로딩(Eager Loading)을 하거나, @Transactional을 사용한다. 
	 * 또한 @Query를 이용해서 Join 처리를 할 수 있다.
	 *
	 * 지연로딩을 그대로 사용하면서, 관련된 테이블에 데이터를 필요한 순간 빨리 조회 될 수 있도록 인덱스를 추가한다.
	 * 예) @Table(name = "tbl_free_replies", indexes = {@Index(unique = false, columnList = "board_bno")}
	 * */
//	@Test
	@Transactional
	public void lazyLoadingTest()
	{
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
	
		freeBoardRepo.findFreeBoardByBnoGreaterThan(0L, paging).forEach(board -> 
		{
			System.out.println(board.getTitle() + " : " + board.getReplies().size());
		});
	}
	
	@Test
	public void queryAnnotationJoinForLazyLoading()
	{
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		
		freeBoardRepo.getPage(paging).forEach(board -> 
		{
			System.out.println(Arrays.toString(board));
		});
	}
}
