package com.example.demo.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/* Validation 검증 시 로그를 위해 toString() 메소드를 반드시 구현한다.
 * */
public class Person 
{
	private int id;
	
	@NotNull
	@Size(min = 5, max = 5)  
	private String name;
	
	public Person() 
	{
	}

	public Person(int id, String name) 
	{
		this.id = id;
		this.name = name;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	@Override
	public String toString() 
	{
		return "Person {" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
