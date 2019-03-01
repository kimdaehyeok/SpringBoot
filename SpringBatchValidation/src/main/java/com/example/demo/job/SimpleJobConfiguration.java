package com.example.demo.job;

import java.util.Arrays;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.validator.SpringValidator;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.example.demo.validation.CustomValidatingItemProcessorForInvalidRecordLog;
import com.example.demo.vo.Person;

/* Reference
 *  - https://gist.github.com/benas/163a4103d8850cf95b575020c167f1cc
 * */
@Configuration
@EnableBatchProcessing
public class SimpleJobConfiguration 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job() 
	{
		return jobBuilderFactory.get("simpleJob")
				.start(step1())
				.build();
	}
	
	@Bean
	public Step step1() 
	{
		return stepBuilderFactory.get("simpleStep")
				.<Person, Person>chunk(1)
				.reader(itemReader())
				.processor(itemProcessor())
				.writer(itemWriter())
				.build();
	}

	@Bean
	public ItemReader<Person> itemReader() 
	{
		Person person1 = new Person(1, "abcde");
		Person person2 = new Person(2, "");
		Person person3 = new Person(3, "fghij");
		
		return new ListItemReader<>(Arrays.asList(person1, person2, person3));
	}

	@Bean
	public org.springframework.validation.Validator validator() 
	{
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public Validator<Person> springValidator() 
	{
		SpringValidator<Person> springValidator = new SpringValidator<>();
		springValidator.setValidator(validator());
	
		return springValidator;
	}

	@Bean
	public ItemProcessor<Person, Person> itemProcessor() 
	{
		CustomValidatingItemProcessorForInvalidRecordLog<Person> validatingItemProcessor = new CustomValidatingItemProcessorForInvalidRecordLog<>(springValidator());
		validatingItemProcessor.setInvalidFlag(true);
	
		return validatingItemProcessor;
	}
	
	@Bean
	public ItemWriter<Person> itemWriter()
	{
		return list -> {
			for (Person person : list) {
				System.out.println(">>>>>> Valid Record (SimpleJobConfiguration.class) : " + person);
			}
		};
	}
}
