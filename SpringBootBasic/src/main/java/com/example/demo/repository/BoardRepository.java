package com.example.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board;

/* Repository<T,ID>
 * extends CrudRepository
 * extends PagingAndSortingRepository
 * extends JpaRepository
 * 와 같은 순서로 상속을 받고 있다.
 * */

/* T : 엔티티 타입의 클래스 (Java Generic이다. 클래스와 같이 배열 기반으로 되어 있는 구조에는 E가 어울리고, 그 외에는 T를 쓰면 된다.)
 * ID : 식별자(PK)를 의미한다. 이때 ID는 java.io.Serializable 인터페이스 타입이여야 한다.
 * */

/* Persistence란 데이터를 생성한 프로그램이 종료 되더라도, 데이터가 사라지지 않는 특성을 말한다.
 * Persistence를 구현하는 방법으로 파일 시스템, 관계형 데이터베이스, 객체 데이터베이스 등이 있다.
 * Persistence를 갖지 않는 데이터는 단지 메모리에만 상주하므로, 프로그램이 종료되면 모두 잃어버리게 된다.
 * */

/* Spring Data JPA는 이름만으로 원하는 질의(Query)를 수행할 수 있는 방법을 제공한다. 
 * 이 때, 쿼리는 'Select'만 해당한다는 것에 주의해야 한다.
 * 쿼리 메소드는 다음과 같은 단어들로 시작한다.
 * 
 * find..By..
 * read..By..
 * query..By..
 * get..By..
 * count..By
 * 
 * 예로, find..By로 쿼리 메소드를 작성한다면, find 뒤에 엔티티 타입을 지정한다. 지정하지 않으면 Repository 타입 정보를 기준으로 동작한다.
 * By의 뒤쪽에는 컬럼명을 이용해서 구성한다.
 * 하단을 기준으로 작성한다면, findBoardByTitle : 제목으로 검색한다.
 * 
 * 쿼리 메소드의 리턴 타입으로는 Page<T>, Slice<T>, List<T>와 같은 Collection<T> 형태가 될 수 있다.
 * */

/* SQL문에서 특정한 칼럼의 값을 조회하고 싶을 때, 메소드의 이름을 findBy로 시작하는 방식을 사용한다.
 * Collection<T> findBy + 속성명(속성 타입)
 * */

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> 
{
	public List<Board> findBoardByTitle(String title);
	
	// Board 클래스에서 writer의 속성 값은 String이기 때문에 속성타입을 String으로 지정한다.
	public Collection<Board> findBoardByWriter(String writer);
	
	/* 단순 like = Like 
	 * 키워드 + '%' = StartingWith
	 * '%' + 키워드 = EndingWith
	 * '%' + 키워드 + '%' = Containing
	 * */
	public Collection<Board> findBoardByWriterContaining(String writer);
	
	/* 선언된 메소드의 속성을 보면 title, content가 사용되었기 때문에, 파라미터도 두개를 입력한다.
	 * */
	public List<Board> findBoardByTitleContainingOrContentContaining(String title, String user);
	
	/* 쿼리 메소드에서 '>', '<' 과 같은 부등호는 GreaterThan, LessThan을 이용해서 처리할 수 있다.
	 * */
	public List<Board> findBoardByTitleContainingAndBnoGreaterThan(String title, Long bno);
	
	/* 조회하는 데이터의 순서를 지정하기 위해 order by를 사용할 수 있다.
	 * */
	public List<Board> findBoardByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	/* 쿼리 메소드들에는 특이하게도 모든 쿼리 메소드의 마지막 파라미터로 페이지를 처리할 수 있는
	 * Pageable 인터페이스와 정렬을 처리하는 Sort 인터페이를 제공한다.
	 * 
	 * Pageable은 org.springframework.data.domain.Pageable 인터페이스를 구현한 클래스 중에
	 * PageRequest 클래스를 이용하는 것이다. PageRequest 경우 스프링 부트 2.0이라면 new PageRequest는 deprecated이다
	 * 그러므로 PageRequest.of() 를 사용한다.
	 * */
	public List<Board> findBoardByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
	
	/* OrderBy 부분 없이 PageRequest()를 이용해서 OrderBy를 할 수 있다.
	 * */
	public List<Board> findBoardByBnoGreaterThan(Long bno, Pageable paging);
	
	/* Page<T> 타입을 이용하면 스프링 MVC와 연동할 때 상당한 편리함을 제공한다.
	 * https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/api/org/springframework/data/domain/Page.html
	 * 위 사이트에서 유용한 메소드를 찾을 수 있다.
	 * */
	public Page<Board> findBoardByBnoGreaterThanEqual(Long bno, Pageable paging);
	
	/* @Query에는 JPQL(객체 쿼리)를 이용합니다.
	 * SQL과 유사한 구문들로 구성되고, JPA의 구현체에서 이를 해석하여 실행한다.
	 * %?1% : ?는 JDBC 상에서 PreparedStatement를 사용한 것과 동일하다고 생각하면 되고, ?1은 첫번째 파라미터이다.
	 * */
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByTitle(String title);
	
	/* %:content%와 같이 사용되는 것을 볼 수 있다.
	 * 파라미터에는 @Param이라는 어노테이션을 사용하는 것을 볼 수 있다.
	 * @Param은 org.springframework.data.repository.query.Param 클래스를 이용한다
	 * 이를 통해 여러개의 파라미터를 전달할 때, 이름을 이용해 쉽게 구분해서 전달할 수 있다.
	 * */
	@Query("Select b FROM Board b WHERE b.content LIKE %:contentParam% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findBoardByContent(@Param("contentParam") String content);
	
	/* Select를 이용하여 특정 컬럼만 추출할 수 있다.
	 * 특정 컬럼만 추출할 경우 리턴 타입이 엔티티가 아니라 object[]이다.
	 * */
	@Query("Select b.bno, b.title, b.content FROM Board b where b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno")
	public List<Object[]> findByTitleSpecificColumn(String title);
	
	/* @Query에 nativeQuery 속성을 true로 지정하면 메소드 실행 시 @Query의 값을 그대로 실행하게 된다.
	 * 테이블명도 board가 아닌 tbl_boards를 사용한다.
	 * cf) Line 116 쿼리에 tbl_boards을 사용할 경우 org.hibernate.hql.internal.ast.QuerySyntaxException: tbl_boards is not mapped 발
	 * */
	@Query(value = "Select bno, title, writer FROM tbl_boards WHERE title like CONCAT('%',?1,'%') AND bno > 0 ORDER BY bno", nativeQuery = true)
	public List<Object[]> findBoardByTitleByUsingNativeQuery(String title);
}
