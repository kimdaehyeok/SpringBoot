package com.example.demo.hello;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job 
{
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{
		// TODO Auto-generated method stub
		System.out.println(">>>>>> Hello Job");
	}
}
