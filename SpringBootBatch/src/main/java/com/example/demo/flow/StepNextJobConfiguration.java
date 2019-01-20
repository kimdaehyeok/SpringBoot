package com.example.demo.flow;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 1. application.properties에 spring.batch.job.names=${job.name}을 입력
 * 2. Run Configuration의 Environment에 job.name-stepNextJob 셋팅
 * 
 * 참고 : application.properties나 Environment에 값을 소스코드에서 사용하고 싶은 경우 @Value("${이름}")을 사용
 * 
 * @Value("${spring.datasource.username}")
 * private String getUserNameFromProperties;
 * 
 * 참고 : https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
 * */
@Configuration
public class StepNextJobConfiguration 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job stepNextJob()
	{
		return jobBuilderFactory.get("stepNextJob")
				.start(step1())
				.next(step2())
				.next(step3())
				.build();
	}
	
	 @Bean
	 public Step step1() 
	 {
	        return stepBuilderFactory.get("step1")
	                .tasklet((contribution, chunkContext) -> {
	                    System.out.println(">>>>> This is Step1");
	                    return RepeatStatus.FINISHED;
	                }).build();
	    }

	    @Bean
	    public Step step2() 
	    {
	        return stepBuilderFactory.get("step2")
	                .tasklet((contribution, chunkContext) -> {
	                	System.out.println(">>>>> This is Step2");
	                    return RepeatStatus.FINISHED;
	                }).build();
	    }

	    @Bean
	    public Step step3() 
	    {
	        return stepBuilderFactory.get("step3")
	                .tasklet((contribution, chunkContext) -> {
	                	System.out.println(">>>>> This is Step3");
	                    return RepeatStatus.FINISHED;
	                }).build();
	    }
}
