package com.example.demo.multiple.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobC implements Job 
{
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{
		// TODO Auto-generated method stub
		System.out.println(">>>>>> Job C");
	}

}
