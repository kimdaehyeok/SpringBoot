package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* H2 DB를 사용할 경우엔 해당 테이블을 Boot가 실행될때 자동으로 생성해주지만, 
 * MySQL이나 Oracle과 같은 DB를 사용할때는 개발자가 직접 생성해야 한다.
 * */

/* H2를 이용해 테스트를 할 경우 JDBC URL은 jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
 * MySQL을 이용헤 테스트 할 경우 JDBC URL은 jdbc:mysql://localhost:3306/batch?useSSL=false&&serverTimezone=UTC
 * */
//@Configuration
public class SimpleJobTasklet 
{
	@Autowired
    private JobBuilderFactory jobBuilderFactory; 
	
	@Autowired
    private StepBuilderFactory stepBuilderFactory; 

	/* Spring Batch에서 Job은 하나의 배치 작업 단위를 말한다.
	 * Job 안에는 여러개의 Step이 존재한다.
	 * Step 안에는 tasklet 혹은 Reader & Processor & Writer 묶음이 존재한다.
	 * 
	 * Tasklet과 Reader & Processor & Writer 한 묶음이 같은 레벨이다
	 * 그러므로, Reader & Processor가 끝나고 Tasklet 으로 마무리 짓는 등은 할 수 없다.
	 * */
    @Bean
    public Job simpleJob() 
    {
    	/* simpleJob이라는 이름으로 Batch Job을 생성한다.
    	 * Job의 이름은 별도로 지정하지 않고, JobBuilder를 통해 지정한다.
    	 */
        return jobBuilderFactory.get("simpleJob") 
                .start(simpleStep())
                .build();
    }

    @Bean
    public Step simpleStep() 
    {
    	/* simpleStep 이란 이름으로 Batch Step을 지정한다.
    	 * JobBuilder와 마찬가지로 StepBuilder를 통해 이름을 지정한다.
    	 */
        return stepBuilderFactory.get("simpleStep")
        		// tasklet이란 Step 안에서 단일로 수행할 커스텀된 기능들을 선언할 때 사용한다.
                .tasklet((contribution, chunkContext) -> {
                	System.out.println(">> Simple Job Tasklet");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}