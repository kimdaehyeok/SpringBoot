package com.example.demo.reader;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class MybatisPagingItemReaderConfiguration 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	private final int chunk_size = 10;
	
	public SqlSessionFactory getSqlSessionFactory() throws Exception
	{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/example/demo/xml/*.xml"));
		
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
	
	@Bean
	public Job mybatisPagingItemReaderJob() throws Exception
	{
		return jobBuilderFactory.get("mybatisPagingItemReader")
				.start(mybatisPagingItemReaderStep())
				.build();
	}
	
	@Bean
	public Step mybatisPagingItemReaderStep() throws Exception 
	{
		return stepBuilderFactory.get("mybatisPagingItemReaderStep")
				.<PayForMybatis, PayForMybatis>chunk(chunk_size) // <InputType, OutputType>
				.reader(mybatisPagingItemReader())
				.writer(myBatisPagingItemWriter())
				.build();
	}
	
	@Bean
	// public ItemReader<InputType>
	public MyBatisPagingItemReader<PayForMybatis> mybatisPagingItemReader() throws Exception
	{
		Map<String, Object> parameterValues = new HashMap();
		parameterValues.put("amount", "2000");

		MyBatisPagingItemReader<PayForMybatis> MyBatisPagingItemReader =  new MyBatisPagingItemReader<PayForMybatis>();
		
		MyBatisPagingItemReader.setSqlSessionFactory(getSqlSessionFactory());
		MyBatisPagingItemReader.setPageSize(chunk_size);
		MyBatisPagingItemReader.setParameterValues(parameterValues);
		MyBatisPagingItemReader.setQueryId("selectAmount");
		
		return MyBatisPagingItemReader;
	}
	
	@Bean
	//public ItemWriter<OutputType>
	public ItemWriter<PayForMybatis> myBatisPagingItemWriter() 
	{
		return list -> {
			for (PayForMybatis pay : list) {
				System.out.println("Mybatis Current Pay = " + pay.getTxName() + " / " + pay.getAmount() + " / " + pay.getId() + " / " + pay.getTxDateTime());
			}
		};
	}
}
