package com.example.demo.domain;

import java.sql.Timestamp;

public class MemberVO 
{
	private int mno;
	private String mid;
	private String mpw;
	private String mname;
	private Timestamp regdate;
	
	public MemberVO(int mno, String mid, String mpw, String mname, Timestamp regdate)
	{
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.regdate = regdate;
	}
	
	public int getMno() 
	{
		return mno;
	}
	public void setMno(int mno) 
	{
		this.mno = mno;
	}
	public String getMid() 
	{
		return mid;
	}
	public void setMid(String mid) 
	{
		this.mid = mid;
	}
	public String getMpw() 
	{
		return mpw;
	}
	public void setMpw(String mpw) 
	{
		this.mpw = mpw;
	}
	public String getMname() 
	{
		return mname;
	}
	public void setMname(String mname) 
	{
		this.mname = mname;
	}
	public Timestamp getRegdate() 
	{
		return regdate;
	}
	public void setRegdate(Timestamp regdate) 
	{
		this.regdate = regdate;
	}
}
