package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SimpleJobTaskletWithParameter 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
    public Job simpleJobParam() 
	{
        return jobBuilderFactory.get("simpleJobParam")
                .start(simpleJobWithParam(null))
                .build();
    }
	
	@Bean
	@JobScope
	public Step simpleJobWithParam(@Value("#{jobParameters[requestDate]}") String requestDate)
	{
		return stepBuilderFactory.get("simpleStepWithParam")
				.tasklet((contribution, chunkContext) -> {
					System.out.println(">> Simple Job Wit Param : " + requestDate);
					return RepeatStatus.FINISHED;
				}).build();
	}
}
