package com.example.demo.processor;

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
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.example.demo.reader.PayForMybatis;

@Configuration
public class MybatisItemProcessorConfiguration 
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
	public Job mybatisBatchItemProcessorJob() throws Exception
	{
		return jobBuilderFactory.get("mybatisBatchItemProcessorJob")
				.start(mybatisBatchItemProcessorStep())
				.build();
	}
	
	 
	@Bean
	public Step mybatisBatchItemProcessorStep() throws Exception
	{
		return stepBuilderFactory.get("mybatisBatchItemProcessorStep")
				.<PayForMybatis, String>chunk(chunk_size)
				.reader(mybatisPagingItemRaderForItemProcessor())
				.processor(mybatisItemProcessor())
				.writer(myBatisPagingItemWriterForItemProcessor())
				.build();
	}
	
	@Bean
	public MyBatisPagingItemReader<PayForMybatis> mybatisPagingItemRaderForItemProcessor() throws Exception
	{
		Map<String, Object> parameterValues = new HashMap<String, Object>();
		parameterValues.put("amount", "500");
		
		MyBatisPagingItemReader<PayForMybatis> myBatisPagingItemReader = new MyBatisPagingItemReader<PayForMybatis>();
		
		myBatisPagingItemReader.setPageSize(chunk_size);
		myBatisPagingItemReader.setSqlSessionFactory(getSqlSessionFactory());
		myBatisPagingItemReader.setParameterValues(parameterValues);
		myBatisPagingItemReader.setQueryId("selectAmount");
		
		return myBatisPagingItemReader;
	}
	
	@Bean
	public ItemProcessor<PayForMybatis, String> mybatisItemProcessor() 
	{
		return PayForMybatis -> {
			
			if(PayForMybatis.getId() %2 == 0L)
			{
				return PayForMybatis.getTxName() + "EVEN";
			}
			else if(PayForMybatis.getId() == 3L)
			{
				System.out.println("ID :3, Can't pass");
				
				return null;
			}
			else
			{
				return PayForMybatis.getTxName() + "ODD";
			}
		};
	}
	
	@Bean
	public ItemWriter<String> myBatisPagingItemWriterForItemProcessor() 
	{
		return list -> {
			for (String pay : list) {
				System.out.println(">>>>>>> Writer Print : " + pay);
			}
		};
	}
}
