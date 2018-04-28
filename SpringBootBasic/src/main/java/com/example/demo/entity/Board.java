package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/* ORM(Object Relational Mapping)이란 객체지향과 관계형 데이터베이스를 매핑시킨 추상화된 개념
 * JPA는 ORM의 개념을 Java 언어에서 구현하기 위한 추상화된 구현 스펙이다.
 * JPA 자체로는 스펙에 불과하기 때문에 이를 실제로 구현한 제품이나 프레임워크가 필요하다.(Servlet이란 기술 스펙을 Tomcat에서 구현한것 처럼)
 * 스프링부트에서는 Hibernate를 이용하여 구현했다.
 * Hibernate 이외에도 EclipseLink, DataNucleus 등이 있다.
 * */

/* Entity란 데이터베이스상에서 데이터로 관리되는 대상을 의미한다.
 * JPA에서는 자바를 통해 이러한 엔티티를 관리하기 때문에 엔티티 타입의 존재는 클래스가 된다.
 * Entity Manager는 엔티티 객체들의 Life Cycle을 관리하는 역할을 한다.
 * Entity Manager는 영속 컨텍스트(Persistence Context)에 엔티티를 넣고 관리하게 된다.
 * */

/* New (비 영속 객체) : Entity 객체가 DB에 반영되지 않았고, Managed 상태가 아닌 상태를 말한다. 이 상태는 new 키워드를 사용해 생성한 Entity 객체를 말하고 영속화되지 않는다. 
 * Managed (영속 객체) : Entity 객체가 영속 객체가 된 상황은 크게 2가지가 있다. New (비 영속 객체) 상태에서 persist 메소드를 이용해 저장한 경우와 DB 테이블에 저장돼 있는 데이터를 find 메소드 또는 query를 사용해 조회한 경우다. 이 상태는 Persistence Context가 관리하는 상태이며, 해당 객체를 수정했는지(자동 변경 감지) 알아낼 수 있다.
 * Removed (삭제 객체) : Managed 상태인 객체를 remove 메소드를 이용해 삭제한 경우에 Removed (삭제 객체) 상태에 해당한다. 작업 단위가 종료되는 시점에 실제로 DB 테이블에 삭제가 동기화 된다. 이 상태에 객체는 작업 단위가 종료되는 동시에 DB에서 삭제되므로 재사용하면 안된다.
 * Detached (준 영속 객체) : 트랜잭션이 commit되었거나, clear, flush 메소드가 실행된 경우 Managed (영속 객체) 상태의 객체는 모두 Detached (준 영속 상태) 상태가 된다. 이 상태는 더 이상 DB와 동기화를 보장하지 않는다. 다시 Managed (영속 객체) 상태로 만들기 위한 merge 메소드가 존재한다.
 * */

/* Spring Data JPA는 동적으로 인터페이스를 구현하는 클래스를 만들어 내는 방식(Dynamic Proxy)를 사용한다.
 * 그러므로, 실제 클래스를 사용하지 않아도 자동으로 생성된다.
 * */

@Entity // Entity 클래스 임을 지정하며, 테이블과 매핑 된다.
@Table(name="tbl_boards") // Entity가 매핑될 테이블을 지정하며, 생략 시 Entity 클래스와 같은 이름으로 매핑된다.
public class Board 
{
	@Id //Primary Key
	@GeneratedValue(strategy=GenerationType.AUTO) // Primary Key 전
	private Long bno;
	
	private String title;
	private String writer;
	
	@Column(name = "content") // 지정된 필드나 속성의 테이블 칼럼에 매핑되며, 생략 시 속성과 같은 이름으로 매핑된다.
	private String content;
	
	@CreationTimestamp
	private Timestamp regdate;
	
	@UpdateTimestamp
	private Timestamp updatedate;
	
	public Long getBno() {
		return bno;
	}
	public void setBno(Long bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public Timestamp getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
}
