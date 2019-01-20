package com.example.demo.writer.custom;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.example.demo.reader.Pay;

public class CustomPayItemWriter implements ItemWriter<Pay> 
{
	public CustomPayItemWriter()
	{
		System.out.println(">>>>> CustomPayItemWriter Constructor <<<<<");
	}
	
	@Override
	public void write(List<? extends Pay> items) throws Exception 
	{
		System.out.println(">>>>> CustomPayItemWriter Start <<<<<");
		// TODO Auto-generated method stub
		for (Pay item : items) 
		{
            System.out.println(item.getId() + " / " + item.getTxName() + " / " + item.getAmount() + " / " + item.getTxDateTime());
        }

		System.out.println(">>>>> CustomPayItemWriter Finish <<<<<");
	}
}
