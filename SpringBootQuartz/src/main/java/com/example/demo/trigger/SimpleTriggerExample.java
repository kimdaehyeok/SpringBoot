package com.example.demo.trigger;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.example.demo.hello.HelloJob;

public class SimpleTriggerExample 
{
	public static void main(String[] args) throws SchedulerException 
	{
		JobDetail helloJob = JobBuilder.newJob(HelloJob.class)
							 .withIdentity("helloJobDetail", "group1")
							 .build();
		
		Trigger helloSimpleTrigger = TriggerBuilder.newTrigger()
									 .withIdentity("helloJobTrigger", "group1")
									 .withSchedule(
											 SimpleScheduleBuilder.simpleSchedule()
											 .withIntervalInSeconds(5)
											 .repeatSecondlyForTotalCount(15))
									 .build();
		
		Scheduler helloJobSimpleScheduler = new StdSchedulerFactory().getScheduler();
		helloJobSimpleScheduler.start();
		helloJobSimpleScheduler.scheduleJob(helloJob, helloSimpleTrigger);
				
	}
}
