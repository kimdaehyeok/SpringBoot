package com.example.demo.multiple.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/* JobKey : Uniquely identifies a JobDetail.
 * */
public class MultipleCronJob 
{
	public static void main(String[] args) throws SchedulerException 
	{
		JobKey jobKeyA = new JobKey("jobA");
		JobDetail jobDetailA = JobBuilder.newJob(JobA.class)
								.withIdentity(jobKeyA)
								.build();
		
		JobKey jobKeyB = new JobKey("jobB");
		JobDetail jobDetailB = JobBuilder.newJob(JobB.class)
								.withIdentity(jobKeyB)
								.build();
		
		JobKey jobKeyC = new JobKey("jobC");
		JobDetail jobDetailC = JobBuilder.newJob(JobC.class)
								.withIdentity(jobKeyC)
								.build();
		
		Trigger triggerForJobA = TriggerBuilder.newTrigger()
								.withIdentity("triggerForJobA")
								.withSchedule(
										CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
										)
								.build();
		
		Trigger triggerForJobB = TriggerBuilder.newTrigger()
				.withIdentity("triggerForJobB")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
						)
				.build();
		
		Trigger triggerForJobC = TriggerBuilder.newTrigger()
				.withIdentity("triggerForJobC")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
						)
				.build();
		
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();

    	scheduler.start();
    	scheduler.scheduleJob(jobDetailA, triggerForJobA);
    	scheduler.scheduleJob(jobDetailB, triggerForJobB);
    	scheduler.scheduleJob(jobDetailC, triggerForJobC);
	}
}
