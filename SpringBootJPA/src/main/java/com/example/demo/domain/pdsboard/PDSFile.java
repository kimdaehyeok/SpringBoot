package com.example.demo.domain.pdsboard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_pdsfiles")
public class PDSFile 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fno;
	
	private String pdsfiles;

	public Long getFno() 
	{
		return fno;
	}

	public void setFno(Long fno) 
	{
		this.fno = fno;
	}

	public String getPdsfiles() 
	{
		return pdsfiles;
	}

	public void setPdsfiles(String pdsfiles) 
	{
		this.pdsfiles = pdsfiles;
	}
}
