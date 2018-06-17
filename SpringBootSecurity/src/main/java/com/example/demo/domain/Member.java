package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

/* 스프링 시큐리티에서는 username, password 등의 용어를 사용하므로 가능한 충돌이 나지 않는 이름을 지정하는 것이 좋다.
 * */
@Getter
@Setter
@Entity
@Table(name = "tbl_members")
public class Member 
{
	@Id
	private String uid;
	private String upw;
	private String uname;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	
	@UpdateTimestamp
	private LocalDateTime updatedate;
	
	/* 이번 예제는 하나의 Role에 여러명의 Id가 입력되는 구조이다.
	 * 즉, 데이터베이스의 컬럼은 기본적으로 하나의 값을 가지지만, @OneToMany인 경우에는
	 * 하나의 데이터에 다수의 값이 저장되어야 한다.
	 * 그러므로 @OneToMany가 지정되는 JPA에서는 무조건 여러개의 데이터를 저장하기 위해 별도의 테이블을 생선한다.
	 * 별도의 테이블이 생성되는 것이 싫다면, @JoinTable 혹은 @JoinColumn을 명시하면 된다.
	 * 또한 양방향 관계일 경우 @OneToMany에서는 mappedBy를 통해 PK, FK를 지정(해당 엔티티가 관계의 주체가 되지 않는 것을 명시)할 수 있다. 
	 * PK 쪽이 mappedBy 속성을 명시한다.
	 * 
	 * 
	 * @ManyToOne인 경우는 Many에 해당되는 데이터의 FK로 One이 할당되기 때문에
	 * 별도의 테이블을 생성하지 않는다.
	 * */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "member") // MemberRole 테이블에 컬럼이 생성된다.
	private List<MemberRole> roles;
}
