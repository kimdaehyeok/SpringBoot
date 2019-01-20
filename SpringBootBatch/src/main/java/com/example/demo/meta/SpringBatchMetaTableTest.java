package com.example.demo.meta;

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

/* 파라미터가 같은 경우 Job을 중복해서 등록할 수 없다.
 * 
 * Caused by: org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException: A job instance already exists and is complete for parameters={requestDate=20190101}.  
 * If you want to run this job again, change the parameters. 와 같은 에러가 발생한다.
 * */

/* "[INSERT into BATCH_JOB_INSTANCE(JOB_INSTANCE_ID, JOB_NAME, JOB_KEY, VERSION) values (?, ?, ?, ?)]; Duplicate entry '0' for key 'PRIMARY'; " 에러가 발생할 경우
 * INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ); 를 실행해준다.
 * */

/* 메타 테이블에 대한 정보는 아래를 참고한다.
 * https://docs.spring.io/spring-batch/trunk/reference/html/metaDataSchema.html
 * */

/* 테스트 수행 방법
 * 1. simpleJobError를 정상으로 소스코드를 설정 후 배치를 수행한다. 수행 시 파라미터 requestDate=20190101 준다.
 * 2. BATCH_STEP_EXECUTION 테이블 조회 결과 Complete로 2개의 Step이 저장되어 있다.
 * 3. requestDate 파라미터 변경과 simpleJobError를 에러 소스코드로 설정 후 배치를 수행한다.
 * 4. 콘솔 확인 결과 "java.lang.IllegalArgumentException: Error Step" 에러를 확인할 수 있다.
 * 5. BATCH_STEP_EXECUTION 조회 결과 simpleStepWithParam는 성공, simpleStepError은 실패를 확인할 수 있다.
 * 6. BATCH_JOB_EXECUTION 조회 결과 실패를 확인할 수 있으며, EXIT_MESSAGE는 java.lang.IllegalArgumentException: Error Step 이다.
 * 7. 소스코드를 정상으로 수정 후 '동일한 파라미터로' 배치를 다시 수행한다. (동일한 파라미터에서 성공 기록이 있을 때, 재수행이 안된다는 것을 알 수 있다.)
 * 8. BATCH_STEP_EXECUTION 확인 결과 실패했던 부분이 성공이 된 것을 알 수 있다.
 * */
@Configuration
public class SpringBatchMetaTableTest 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
    public Job simpleJobMetaTableTest() 
	{
        return jobBuilderFactory.get("simpleJobMetaTableTest")
                .start(simpleJobMetatableTest(null))
                .next(simpleJobError(null))
                .build();
    }
	
	// Step : Batch로 실제 처리할 기능과 설정을 모두 포함하는 장소라고 보면 된다.
	
	@Bean
	@JobScope
	public Step simpleJobError(@Value("#{jobParameters[requestDate]}") String requestDate )
	{
		return stepBuilderFactory.get("simpleStepError")
				.tasklet((contribution, chunkContext) -> {
//						throw new IllegalArgumentException("Error Step");
					System.out.println(">> Simple Job Metatable Test 2 : " + requestDate);
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	@JobScope
	public Step simpleJobMetatableTest(@Value("#{jobParameters[requestDate]}") String requestDate)
	{
		return stepBuilderFactory.get("simpleStepWithParam")
				.tasklet((contribution, chunkContext) -> {
					System.out.println(">> Simple Job Metatable Test : " + requestDate);
					return RepeatStatus.FINISHED;
				}).build();
	}
}
