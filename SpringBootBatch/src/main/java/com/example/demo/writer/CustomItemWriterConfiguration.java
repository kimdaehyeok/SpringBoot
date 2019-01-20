package com.example.demo.writer;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.example.demo.reader.Pay;
import com.example.demo.writer.custom.CustomPayItemWriter;

@Configuration
public class CustomItemWriterConfiguration 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;

	private final int chunk_size = 10;
	
	@Bean
	public Job customBatchItemWriterJob()
	{
		return jobBuilderFactory.get("customBatchItemWriterJob")
				.start(customBatchItemWriterStep())
				.build();
	}
	
	@Bean
	public Step customBatchItemWriterStep()
	{
		return stepBuilderFactory.get("customBatchItemWriterStep")
				.<Pay, Pay>chunk(chunk_size)
				.reader(customBatchItemWriterReader())
				.writer(customBatchItemWriter())
				.build();
	}
	
	@Bean
	public JdbcCursorItemReader<Pay> customBatchItemWriterReader()
	{
		JdbcCursorItemReaderBuilder<Pay> customCursorItemReaderBuilder = new JdbcCursorItemReaderBuilder<Pay>();
		
		customCursorItemReaderBuilder.fetchSize(chunk_size);
		customCursorItemReaderBuilder.dataSource(dataSource);
		customCursorItemReaderBuilder.name("customCursorItemRader");
		customCursorItemReaderBuilder.rowMapper(new BeanPropertyRowMapper<>(Pay.class));
		customCursorItemReaderBuilder.sql("SELECT id, amount, tx_name, tx_date_time FROM batch.pay");
		
		return customCursorItemReaderBuilder.build();
	}
	
	@Bean
	public ItemWriter<Pay> customBatchItemWriter()
	{
		return new CustomPayItemWriter();
	}
	
}
