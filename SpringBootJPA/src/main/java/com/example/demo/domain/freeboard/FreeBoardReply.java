package com.example.demo.domain.freeboard;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tbl_free_replies")
public class FreeBoardReply 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	private String reply;
	private String replyer;
	
	@CreationTimestamp
	private Timestamp replydate;
	
	@UpdateTimestamp
	private Timestamp updatedate;

	@ManyToOne
	private FreeBoard board;
	
	public Long getRno() 
	{
		return rno;
	}
	public void setRno(Long rno) {
		this.rno = rno;
	}

	public String getReply() 
	{
		return reply;
	}
	public void setReply(String reply) 
	{
		this.reply = reply;
	}

	public String getReplyer() 
	{
		return replyer;
	}
	public void setReplyer(String replyer) 
	{
		this.replyer = replyer;
	}

	public Timestamp getReplydate() 
	{
		return replydate;
	}
	public void setReplydate(Timestamp replydate) 
	{
		this.replydate = replydate;
	}

	public Timestamp getUpdatedate() 
	{
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) 
	{
		this.updatedate = updatedate;
	}

	public FreeBoard getBoard() 
	{
		return board;
	}
	public void setBoard(FreeBoard board) 
	{
		this.board = board;
	}
}
