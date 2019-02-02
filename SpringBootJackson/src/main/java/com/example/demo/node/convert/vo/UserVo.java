package com.example.demo.node.convert.vo;

import java.util.List;

public class UserVo 
{
	private String idField;
	private NameVo nameField;
	private List<ContactVo> contactField
	;
	public String getIdFieldMethod() 
	{
		return idField;
	}
	public void setId(String idField) 
	{
		this.idField = idField;
	}
	public NameVo getNameFieldMethod() 
	{
		return nameField;
	}
	public void setName(NameVo nameField) 
	{
		this.nameField = nameField;
	}
	public List<ContactVo> getContactFieldListMethod() 
	{
		return contactField;
	}
	public void setContact(List<ContactVo> contactField) 
	{
		this.contactField = contactField;
	}
}
