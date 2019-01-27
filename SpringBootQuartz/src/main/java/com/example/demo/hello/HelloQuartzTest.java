package com.example.demo.hello;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/* jobDetail : Job의 실제 구현 내용과 Job 실행에 필요한 제반 정보가 있다.
 * trigger : Job을 언제, 어떤 주기로 실행할지에 대한 정보가 담겨 있다.
 * scheduler : jobDetail과 trigger에 담긴 정보를 이용하여 실제 job을 스케줄링 한다.
 * */
public class HelloQuartzTest 
{
	public static void main(String[] args) throws SchedulerException, InterruptedException 
	{
		// Job 구현 내용이 담긴 HelloJob으로 JobDetail 생성
        JobDetail helloJobDetail = newJob(HelloJob.class).build();

        // 실행 시점을 결정하는 Trigger 생성
        Trigger helloTrigger = newTrigger().build();

        // 스케줄러 실행 및 JobDetail과 Trigger 정보로 스케줄링
        Scheduler helloScheduler = StdSchedulerFactory.getDefaultScheduler();
        
        helloScheduler.start();
        helloScheduler.scheduleJob(helloJobDetail, helloTrigger);
        
        Thread.sleep(3000);
        
        helloScheduler.shutdown();
	}
}
