package com.example.demo.flow;

import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* StepNextConditionalJobConfiguration 에서 알 수 있듯이
 * 분기처리를 위해 contribution.setExitStatus(ExitStatus.FAILED);를 변경해야 했고(ExitStatus 조작 필요) 
 * ExitStatus를 변경하기 위해 Listener를 선언해야 했다.
 * 
 * Step Flow 분기 처리를 위해 JobExecutionDecider를 이용할 수 있다.
 * */
@Configuration
public class DecideJobConfiguration 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	 @Bean
	 public Job decidejob()
	 {
		 return jobBuilderFactory.get("decideJob")
				 .start(startStep())
				 .next(decider())
				 .from(decider())
				  .on("ODD")
				  .to(oddStep())
				 .from(decider())
				  .on("EVEN")
				  .to(evenStep())
				 .end().build();
	 }

	@Bean
	public Step startStep() 
	{
		return stepBuilderFactory.get("startStep").tasklet((contribution, chunkContext) -> {
			System.out.println(">>> Start Step");
			return RepeatStatus.FINISHED;
		}).build();
	}

	@Bean
	public Step evenStep()
	{
		return stepBuilderFactory.get("evenStep").tasklet((contribution, chunkContext) -> {
			System.out.println("Even Step");
			return RepeatStatus.FINISHED;
		}).build();
	}

	@Bean
	public Step oddStep() 
	{
		return stepBuilderFactory.get("oddStep").tasklet((contribution, chunkContext) -> {
			System.out.println("odd Step");
			return RepeatStatus.FINISHED;
		}).build();
	}

	@Bean
	public JobExecutionDecider decider() {
		return new OddDecider();
	}

	public class OddDecider implements JobExecutionDecider 
	{
		@Override
		public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) 
		{
			Random rand = new Random();

			int randomNumber = rand.nextInt(50) + 1;
			System.out.println("랜덤숫자: {}" +  randomNumber);

			if (randomNumber % 2 == 0) 
			{
				return new FlowExecutionStatus("EVEN");
			} 
			else 
			{
				return new FlowExecutionStatus("ODD");
			}
		}
	}
}
