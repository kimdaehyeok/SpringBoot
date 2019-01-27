package com.example.demo.trigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.example.demo.hello.HelloJob;

public class CronTriggerExample 
{
	public static void main(String[] args) throws SchedulerException 
	{
		JobDetail helloJob = JobBuilder.newJob(HelloJob.class)
							.withIdentity("hellJobDetail", "group1")
							.build();
		
		Trigger helloTrigger = TriggerBuilder.newTrigger()
							  .withIdentity("helloTrigger", "group1")
							  .withSchedule(
									  CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
									  )
							  .build();
		
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(helloJob, helloTrigger);
							  
	}
}
