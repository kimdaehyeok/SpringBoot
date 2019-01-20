package com.example.demo.writer;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.example.demo.reader.Pay;

@Configuration
public class JdbcItemWriterJobConfiguration 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;

	private final int chunk_size = 10;
	
	@Bean
	public Job jdbcBatchItemWriterJob()
	{
		return jobBuilderFactory.get("jdbcBatchItemWriterJob")
				.start(jdbcBatchItemWriterStep())
				.build();
	}
	
	@Bean
	public Step jdbcBatchItemWriterStep()
	{
		return stepBuilderFactory.get("jdbcBatchItemWriterStep")
				.<Pay, Pay>chunk(chunk_size)
				.reader(jdbcBatchItemWriterReader())
				.writer(jdbcBatchItemWriter())
				.build();
	}
	
	@Bean
	public JdbcCursorItemReader<Pay> jdbcBatchItemWriterReader()
	{
		JdbcCursorItemReaderBuilder<Pay> jdbcCursorItemReaderBuilder = new JdbcCursorItemReaderBuilder<Pay>();
		
		jdbcCursorItemReaderBuilder.fetchSize(chunk_size);
		jdbcCursorItemReaderBuilder.dataSource(dataSource);
		jdbcCursorItemReaderBuilder.name("jdbcCursorItemRader");
		jdbcCursorItemReaderBuilder.rowMapper(new BeanPropertyRowMapper<>(Pay.class));
		jdbcCursorItemReaderBuilder.sql("SELECT id, amount, tx_name, tx_date_time FROM batch.pay");
		
		return jdbcCursorItemReaderBuilder.build();
	}
	
	@Bean
	public JdbcBatchItemWriter<Pay> jdbcBatchItemWriter()
	{
		JdbcBatchItemWriterBuilder<Pay> jdbcBatchItemWriterBuilder = new JdbcBatchItemWriterBuilder<Pay>();
		
		jdbcBatchItemWriterBuilder.dataSource(dataSource);
		jdbcBatchItemWriterBuilder.sql("insert into pay2(amount, tx_name, tx_date_time) values (:amount, :txName, :txDateTime)");
		
		/* columnMapped : Key, Value 기반으로 Insert 수행
		 * beanMapped : Pojo 기반으로 Insert 수행
		 * 
		 * values(:field) : Dto의 Getter나 Map의 Key에 매핑되어 값이 할당 된다.
		 * */
		jdbcBatchItemWriterBuilder.beanMapped();
		
		return jdbcBatchItemWriterBuilder.build();
	}
}