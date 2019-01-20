package com.example.demo.writer;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.example.demo.reader.Pay;

@Configuration
public class MybatisItemWriterJobConfiguration 
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
	public Job mybatisBatchItemWriterJob() throws Exception
	{
		return jobBuilderFactory.get("mybatisBatchItemWriterJob")
				.start(mybatisBatchItemWriterStep())
				.build();
	}
	
	@Bean
	public Step mybatisBatchItemWriterStep() throws Exception
	{
		return stepBuilderFactory.get("mybatisBatchItemWriterStep")
				.<Pay, Pay>chunk(chunk_size)
				.reader(mybatisPagingItemRader())
				.writer(mybatisPagingItemWriter())
				.build();
	}
	
	@Bean
	public MyBatisPagingItemReader<Pay> mybatisPagingItemRader() throws Exception
	{
		Map<String, Object> parameterValues = new HashMap<String, Object>();
		parameterValues.put("amount", "500");
		
		MyBatisPagingItemReader<Pay> myBatisPagingItemReader = new MyBatisPagingItemReader<Pay>();
		
		myBatisPagingItemReader.setPageSize(chunk_size);
		myBatisPagingItemReader.setSqlSessionFactory(getSqlSessionFactory());
		myBatisPagingItemReader.setParameterValues(parameterValues);
		myBatisPagingItemReader.setQueryId("selectAmount");
		
		return myBatisPagingItemReader;
	}
	
	@Bean
	public MyBatisBatchItemWriter<Pay> mybatisPagingItemWriter() throws Exception
	{
		MyBatisBatchItemWriter<Pay> myBatisBatchItemWriter = new MyBatisBatchItemWriter<Pay>();
		
		myBatisBatchItemWriter.setSqlSessionFactory(getSqlSessionFactory());
		myBatisBatchItemWriter.setStatementId("insertPay2");
		
		return myBatisBatchItemWriter;
	}
}
