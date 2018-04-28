package com.example.demo.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Board;

/* @Query를 사용할 경우 고정적인 쿼리만을 생산한다.
 * 그러므로, Querydsl(Query Domain Specific Language)을 통해 동적인 상황에 대한 처리가 필요하다.
 * Querydsl은 Java를 이용해서 쿼리 조건을 처리할 때 사용하는 라이브러리다.
 * Querydsl을 통해 필요한 조건을 처리하는 Java 코드를 생성하고, Repository를 통해 이를 처리한다. 
 * Hibernate인 경우 Criteria를 주로 이용해 왔지만, Spring Data JPA는 Querydsl 이용하는 것이 편리하다.
 * */

/* Querydsl을 이용하기 위해서는 다음과 같은 과정을 거쳐야 한다.
 * 
 * 1. pom.xml의 라이브러리와 Maven 설정의 변경 및 실행
 * 2. Predicate의 개발
 * 3. Repository를 통한 실행
 * */

/* Querydsl은 JPA를 처리하기 위해서 엔티티 클래스를 생성하는데 이를 QDmoain이라고 한다.
 * 동적 쿼리를 생성할 때, 이를 이용하기 때문에 pom.xml에 QDomain 클래스를 생성하는 작업을 위한 코드 생성기가 필요하다.
 * 그러므로, pom.xml에 plugin을 추가한다.
 * */

/* Repository에서 Predicate를 파라미터로 전달하기 위해서는 QueryDslPredicateExecutor<T> 인터페이스를 Repository에 추가해야 한다.
 * */
public interface BoardRepositoryQuerydsl extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> 
{
	
}
