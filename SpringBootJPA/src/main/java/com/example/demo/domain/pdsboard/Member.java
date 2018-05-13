package com.example.demo.domain.pdsboard;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_members")
public class Member 
{
	@Id
	private String uid;
	private String upw;
	private String uname;
	
	public String getUid() 
	{
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUpw() 
	{
		return upw;
	}
	public void setUpw(String upw) 
	{
		this.upw = upw;
	}
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) 
	{
		this.uname = uname;
	}
}
