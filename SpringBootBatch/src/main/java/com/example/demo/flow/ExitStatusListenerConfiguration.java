package com.example.demo.flow;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* ExitStatus를 수정하기 위해, Listener를 생성하고 JobFlow에 등록해야 하는 문제점이 있다.
 * 그래서 Decide를 이용한다.
 * 
 * 아래 예제는 Listener생성 및 JobFlow에 등록해 분기처리 하는 예제이다.
 * 
 * 참고 : https://howtodoinjava.com/spring-batch/spring-batch-event-listeners/#step
 * */
@Configuration
public class ExitStatusListenerConfiguration 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job exitStatusListener()
	{
		return jobBuilderFactory.get("exitStatusListener")
				.listener(new JobListener()) // Job Listener 등록.
				.start(completeStep())
				 .on("COMPLETE WITH LISTENER")
				 .to(toPrint())
				 .next(completeStep2())
				.from(completeStep2())
//				 .on("COMPLETED") // STEP 1, STEP 2 만 실행됨.
				 .on("COMPLETE WITH LISTENER")  // STEP 1, STEP 2, STEP 3 실행 됨.
				 .to(completeStep3())
				.end()
			    .build();
	}
	
	@Bean
	public Step completeStep()
	{
		return stepBuilderFactory.get("completeStep")
				.tasklet((contribution, chunkContext) -> {
					
					System.out.println(">> Complete Step");
					
					contribution.setExitStatus(ExitStatus.COMPLETED);
					return RepeatStatus.FINISHED;
				})
				.listener(new ExitStatusListener()) // Step Listener 등록.
				.build();
	}
	
	@Bean
	public Step completeStep2()
	{
		return stepBuilderFactory.get("completeStep2")
				.tasklet((contribution, chunkContext) -> {
					
					System.out.println(">> Complete2 Step");
					
					contribution.setExitStatus(ExitStatus.COMPLETED);
					return RepeatStatus.FINISHED;
				})
				.listener(new ExitStatusListener()) // Step Listener 등록.
				.build();
	}
	
	@Bean
	public Step completeStep3()
	{
		return stepBuilderFactory.get("completeStep3")
				.tasklet((contribution, chunkContext) -> {
					
					System.out.println(">> Complete3 Step");
					
					contribution.setExitStatus(ExitStatus.COMPLETED);
					return RepeatStatus.FINISHED;
				})
				.listener(new ExitStatusListener()) // Step Listener 등록.
				.build();
	}
	
	public class JobListener implements JobExecutionListener
	{
		@Override
		public void beforeJob(JobExecution jobExecution) 
		{
			System.out.println(">> Called beforeJob().");
		}
		
		@Override
		public void afterJob(JobExecution jobExecution) 
		{
			System.out.println(">> Called afterJob().");
		}
	}
	
    public class ExitStatusListener extends StepExecutionListenerSupport 
    {
    	public ExitStatus afterStep(StepExecution stepExecution) 
    	{
            String exitCode = stepExecution.getExitStatus().getExitCode();
            
            if ( exitCode.equalsIgnoreCase("COMPLETED") )
            {
            	System.out.println(">> COMPLETE WITH LISTENER");
            	
            	return new ExitStatus("COMPLETE WITH LISTENER");
            }
            else 
            {
            	System.out.println(">> Other With Listener");
            	
                return new ExitStatus("OTHER WITH LISTENER");
            }
        }
    }
    
    
    public Step completePrint()
    {
    	return stepBuilderFactory.get("CompletePrint")
    			.tasklet((contribution, chunkContext) -> {
    				System.out.println("** On Complete Step Print");
    				return RepeatStatus.FINISHED;
    			}).build();
    }
    
    public Step completePrint2()
    {
    	return stepBuilderFactory.get("CompletePrint2")
    			.tasklet((contribution, chunkContext) -> {
    				System.out.println("** On Complete2 Step Print");
    				return RepeatStatus.FINISHED;
    			}).build();
    }
    
    public Step completePrint3()
    {
    	return stepBuilderFactory.get("CompletePrint3")
    			.tasklet((contribution, chunkContext) -> {
    				System.out.println("** On Complete3 Step Print");
    				return RepeatStatus.FINISHED;
    			}).build();
    }
    
    public Step toPrint()
    {
    	return stepBuilderFactory.get("toPrint")
    			.tasklet((contribution, chunkContext) -> {
    				System.out.println(">> toPrint");
    				return RepeatStatus.FINISHED;
    			}).build();
    }
}
