package com.example.demo.domain.freeboard;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tbl_freeboards")
public class FreeBoard 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp
	private Timestamp regdate;
	
	@UpdateTimestamp
	private Timestamp updatedate;

	/* mappedBy는 ~에 메이게 된다 이므로 종속적인 클래스의 인스턴스 변수를 지정한다.
	 * */
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL) // FreeBoardReply.board가 FreeBoard 인스턴스를 의미한다.
	private List<FreeBoardReply> replies;
	
	public Long getBno() 
	{
		return bno;
	}
	public void setBno(Long bno) 
	{
		this.bno = bno;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getWriter() 
	{
		return writer;
	}
	public void setWriter(String writer) 
	{
		this.writer = writer;
	}

	public String getContent() 
	{
		return content;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public Timestamp getRegdate() 
	{
		return regdate;
	}
	public void setRegdate(Timestamp regdate) 
	{
		this.regdate = regdate;
	}

	public Timestamp getUpdatedate() 
	{
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) 
	{
		this.updatedate = updatedate;
	}
	
	public List<FreeBoardReply> getReplies() 
	{
		return replies;
	}
	public void setReplies(List<FreeBoardReply> replies) 
	{
		this.replies = replies;
	}
}
