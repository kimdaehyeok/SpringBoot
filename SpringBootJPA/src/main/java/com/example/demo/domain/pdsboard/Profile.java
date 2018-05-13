package com.example.demo.domain.pdsboard;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_profile")
public class Profile 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fno;
	
	private String fname;
	private Boolean current;
	
	/* @ManyToOne에는 아래와 같은 속성이 있다.
	 * 
	 * targetEntity : 연관된 엔티티의 타입 정보를 설정
	 * cascade : 영속성 전이 
	 * fetch :  FetchType.EAGER -> 즉시 조회해서 데이터를 입력한다. / FetchType.LAZY -> 필요 시 DB 조회를 한다.
	 * optional : false -> 해당 객체에 null이 들어갈 수 있다. / true -> 해당 객체에 null이 들어갈 수 없다.
	 * */
	
	/* Cascade 속성
	 * CascadeType.RESIST – 엔티티를 생성하고, 연관 엔티티를 추가하였을 때 persist() 를 수행하면 연관 엔티티도 함께 persist()가 수행된다. 부모를 영속화할 때 연관된 자식들도 함께 영속화 한다.
	 * CascadeType.MERGE – 트랜잭션이 종료되고 detach 상태에서 연관 엔티티를 추가하거나 변경된 이후에 부모 엔티티가 merge()를 수행하게 되면 변경사항이 적용된다.(연관 엔티티의 추가 및 수정 모두 반영됨)
	 * CascadeType.REMOVE – 삭제 시 연관된 엔티티도 같이 삭제됨
	 * CascadeType.DETACH – 부모 엔티티가 detach()를 수행하게 되면, 연관된 엔티티도 detach() 상태가 되어 변경사항이 반영되지 않는다.
	 * CascadeType.ALL – 모든 Cascade 적용한다.
	 * 
	 * By default no operations are cascaded.
	 * persist() : Insert a new register to the database, Attach the object to the entity manager.
	 * */
	@ManyToOne(targetEntity = Member.class, optional = false, fetch = FetchType.EAGER)
	private Member member;

	public Long getFno() 
	{
		return fno;
	}
	public void setFno(Long fno) 
	{
		this.fno = fno;
	}
	
	public String getFname() 
	{
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}

	public Boolean getCurrent() 
	{
		return current;
	}
	public void setCurrent(Boolean current) 
	{
		this.current = current;
	}

	public Member getMember() 
	{
		return member;
	}
	public void setMember(Member member) 
	{
		this.member = member;
	}
}
