package com.example.demo.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageVO 
{
	private static final int DEFAULT_SIZE=10;
	private static final int DEFAULT_MAX_SIZE=50;
	
	private int page;
	private int size;
	private int testValue;
	
	public PageVO()
	{
		this.page = 1;
		this.size = DEFAULT_SIZE;
	}
	
	public int getPage()
	{
		return page;
	}
	
	public void setPage(int page)
	{
		this.page = page;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getTestValue() {
		return testValue;
	}

	public void setTestValue(int testValue) {
		this.testValue = testValue;
	}

	public void setSize(int size)
	{
		if(size < DEFAULT_SIZE || size > DEFAULT_MAX_SIZE)
		{
			this.size = DEFAULT_SIZE;
		}
		else
		{
			this.size = size;
		}
	}
	
	public Pageable makePageable(int direction, String props)
	{
		Sort.Direction dir = direction == 0  ? Sort.Direction.DESC : Sort.Direction.ASC;
		
		return PageRequest.of(this.page -1, this.size, dir, props);
	}
}
