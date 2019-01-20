package com.example.demo.flow;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepNextConditionalJobConfiguration 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job stepNextConditionalJob()
	{
		return jobBuilderFactory.get("stepNextConditionalJob")
				.start(conditionalJobStep1())
				  .on("FAILED") // FAILED일 경우.
				  .to(conditionalJobStep3()) // Step 3 로 이동한다.
				  .on("*") // Step 3 결과에 상관 없이.
				  .end() // 종료.
				.from(conditionalJobStep1()) // Step 1으로 부터.
				  .on("*") // FAILED 외의 모든 경우.
				  .to(conditionalJobStep2()) // Step 2로 이동.
				  .next(conditionalJobStep3()) // Step 3가 정상 종료 되면 Step 3 수행.
				  .on("*") // Step 3 결과에 상관 없이.
				  .end() // FlowBuilder를 반환하는 End로 FlowBuilder를 반환하는 End 사용 시 계속해서 From을 이어갈 수 있음.
				.end() // FlowBuilder를 종료하는 End.
				.build();
	}

	/* BatchStatus : Job 또는 Step의 실행 결과를 Spring에 기록할 때 사용된다. 
	 * BatchStatus는 COMPLETED, STARTING, STARTED, STOPPING, STOPPED, FAILED, ABANDONED, UNKNOWN이 있다.
	 * 
	 * ExitStatus : Step의 실행 후의 상태를 말한다.
	 * 
	 * on("FAILED").to(stepB()) 에서 실제 참조되는 값은 ExitStatus이다.
	 * 
	 * Spring Batch는 기본적으로 ExitStauts의 exitCode는 Step의 BatchStatus와 같도록 설정되어 있다.
	 * 그리고 exitStatus는 custom이 가능하다.
	 * 
	 * .start(step1())
		    .on("FAILED")
		    .end()
		.from(step1())
		    .on("COMPLETED WITH SKIPS")
		    .to(errorPrint1())
		    .end()
		.from(step1())
		    .on("*")
		    .to(step2())
		    .end()
     * Step이 정상적으로 수행되었지만 COMPLETED WITH SKIPS를 출력한다. 이 경우는 다른 Step이 Error를 핸들링한 결과이다.
     * 
     * 참고 : http://docs.spring.io/spring-batch/trunk/reference/htmlsingle/#batchStatusVsExitStatus
	 * */
	@Bean
    public Step conditionalJobStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                	System.out.println(">>>>> This is stepNextConditionalJob Step1");
                	/* FAILED를 ExitStatus로 설정 후 수행하면
                	 * 
                	 * >>>>> This is stepNextConditionalJob Step1
                	 * >>>>> This is stepNextConditionalJob Step3
                	 * 만 수행되는 것을 확인할 수 있다.
                	 * 
                	 * 주석처리하고 수행한 경우
                	 * >>>>> This is stepNextConditionalJob Step1
                	 * >>>>> This is stepNextConditionalJob Step2
                	 * >>>>> This is stepNextConditionalJob Step3
                	 * 이 수행되는 것을 확인 할 수 있다.
                	 */
//                    contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step conditionalJobStep2() {
        return stepBuilderFactory.get("conditionalJobStep2")
                .tasklet((contribution, chunkContext) -> {
                	System.out.println(">>>>> This is stepNextConditionalJob Step2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step conditionalJobStep3() {
        return stepBuilderFactory.get("conditionalJobStep3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>>>> This is stepNextConditionalJob Step3");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    
    public Step customExitStatus()
    {
        return stepBuilderFactory.get("customExitStatus")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>>>> This is Custom Exit Status");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
