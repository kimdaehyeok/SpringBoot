package com.example.demo.freeboard_repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.freeboard.FreeBoard;

/* 여러 엔티티들의 Repository를 구성할 때 중요한 것은 엔티티 객체의 라이프사이클 이다.
 * 각 엔티티가 별도의 라이프사이클을 가진다면 별도의 Repository를 생성하는 것이 좋다.
 * */
public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>
{
	public List<FreeBoard> findFreeBoardByBnoGreaterThan(Long bno, Pageable paging);
	
	@Query("Select b.bno, b.title, count(r) FROM FreeBoard b LEFT OUTER JOIN b.replies r WHERE b.bno > 0 GROUP BY b")
	public List<Object[]> getPage(Pageable paging);
}
