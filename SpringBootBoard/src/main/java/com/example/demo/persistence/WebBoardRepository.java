package com.example.demo.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.QWebBoard;
import com.example.demo.domain.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard>
{
	/* Predicate는 단언하다, 확신하다는 의미이다.
	 * 즉 boolean으로 리턴되는 결과 데이터를 만들어야 한다.
	 * 이 때 주로 BooleanBuilder를 이용하여 생성한다.
	 * */
	public default Predicate makePredicate(String type, String keyword)
	{
		/* Predicate는 주로 BooleanBuilder를 이용해 생성한다.
		 * Predicate는 필요한 곳에서 생성하는 방식을 이용하기도 하지만, 별도의 클래스 등을 만들어 사용할 수 있다. 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		
		/* Querydsl을 이용하는 경우 Entity 클래스는 Querydsl에서 사용하는 '쿼리 도메인 클래스(QDomain)'라는 것을 생성해야 한다.
		 * QDomain은 pom.xml의 plugin에서 설정할 수 있다. 
		 * */
		QWebBoard board = QWebBoard.webBoard;

		builder.and(board.bno.gt(0));

		if(type == null)
		{
			return builder;
		}
		
		switch(type)
		{
		case "t":
			builder.and(board.title.like("%" + keyword + "%"));
			break;
		case "c":
			builder.and(board.content.like("%" + keyword +"%"));
			break;
		case "w":
			builder.and(board.writer.like("%" + keyword +"%"));
			break;
		}
		
		return builder;
	}
	
}
