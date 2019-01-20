package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;

/* Spring Batch를 활성화시키기 위해 @EnableBatchProcessing을 추가한다.
 * 어노테이션을 선언함으로써, Spring Batch 기능을 사용할 수 있다.
 * 선언하지 않으면 Spring Batch 기능을 사용할 수 없기 때문에 필수로 선언해야 한다.
 */
@EnableBatchProcessing 
@SpringBootApplication
public class SpringBatchExamleApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(SpringBatchExamleApplication.class, args);
	}
}

