package com.example.demo.reader;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/* Spring의 JdbcTemplate의 경우 분할처리를 지원하지 않기 때문에(쿼리 결과를 직접 반환)
 * 개발자가 직접 limit 혹은 offset 등을 통해 작업을 해야 한다.
 * 
 * Spring Batch는 이러한 문제를 해결하기 위해 2가지 타입의 Reader를 제공한다.
 * 1. Cursor 형식 : JDBC의 기본 기능으로 1 Row씩 검색한다.
 * 2. Paging 형식 : Page 단위로 데이터베이스에서 데이터를 조회한다.
 *  
 * Cursor 방식은 데이터베이스와 커넥션을 맺은 후, ResultSet에서 Cursor를 한칸씩 옮겨 가면서 지속적으로 데이터를 추출
 * Paging 방식은 한번에 Paging 크기만큼 데이터를 가져온다.
 * 
 * Cursor 방식은 하나의 Connection으로 Batch가 끝날때까지 사용되기 때문에, Batch가 끝나기 전에
 * 어플리케이션과 데이터베이스 연결이 끊어질 수 있다. 그러므로 SocketTimeout값을 큰 값으로 설정한다.
 * 
 * Batch 수행 시간이 오래 걸리는 경우에는 PagingItemReader를 사용하는것이 좋다.
 * Paging의 경우 한 페이지를 읽을때마다 Connection을 맺고 끊기 때문에 아무리 많은 데이터라도 타임아웃과 부하 없이 수행될 수 있다.
 * 
 * 참고 : https://dayone.me/1Sm6zrv
 * */
@Configuration
public class JdbcPagingItemReaderConfiguration 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	private final int chunk_size = 10;

	@Bean
	public Job jdbcPagingItemReaderJob() throws Exception 
	{
		return jobBuilderFactory
				.get("jdbcPagingItemReaderJob")
				.start(jdbcPagingItemReaderStep())
				.build();
	}

	@Bean
	public Step jdbcPagingItemReaderStep() throws Exception 
	{
		return stepBuilderFactory.get("jdbcPagingItemReaderStep")
				.<Pay, Pay>chunk(chunk_size) // <InputType, OutputType>
				.reader(jdbcPagingItemReader())
				.writer(jdbcPagingItemWriter())
				.build();
	}

	@Bean
	// public ItemReader<InputType>
	public JdbcPagingItemReader<Pay> jdbcPagingItemReader() throws Exception
	{
		Map<String, Object> parameterValues = new HashMap();
		parameterValues.put("amount", "2000");

		return new JdbcPagingItemReaderBuilder<Pay>()
				.fetchSize(chunk_size)
				.dataSource(dataSource)
				.rowMapper(new BeanPropertyRowMapper<>(Pay.class))
				.queryProvider(createQueryProvider())
				.parameterValues(parameterValues)
				.name("jdbcPagingItemReader") //Reader 이름 지정. Bean의 이름이 아니며 Spring Batch의 ExecutionContext에서 저장되어질 이름이다.
				.build();
	}

	@Bean
	public PagingQueryProvider createQueryProvider() throws Exception 
	{
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();

		/* 각 데이터베이스마다 Paging을 지원하는 자체적인 전략이 있다. 그러므로, Spring Batch에는 각 데이터베이스에 맞는 Paging
		 * 전략이 구현되어야 한다. 예로, provider.put(DB2, new Db2PagingQueryProvider()); 등이 있다.
		 * 
		 * 데이터베이스마다 Provider 코드를 변경해야 하는 불편함이 있다. 그러므로, Spring Batch에서는
		 * SqlPagingQueryProviderFactoryBean을 통해 Datasource 설정값을 보고 위 이미지에서 작성된
		 * Provider중 하나를 자동으로 선택하도록 한다.
		 */
		queryProvider.setDataSource(dataSource); // Database에 맞는 PagingQueryProvider를 선택하기 위해
		queryProvider.setSelectClause("id, amount, tx_name, tx_date_time");
		queryProvider.setFromClause("from pay");
		queryProvider.setWhereClause("where amount >= :amount");

		Map<String, Order> sortKeys = new HashMap<>(1);
		sortKeys.put("id", Order.ASCENDING);

		queryProvider.setSortKeys(sortKeys);

		return queryProvider.getObject();
	}

	@Bean
	//public ItemWriter<OutputType>
	public ItemWriter<Pay> jdbcPagingItemWriter() 
	{
		return list -> {
			for (Pay pay : list) {
				System.out.println("JDBC Current Pay = " + pay.getTxName() + " / " + pay.getAmount() + " / " + pay.getId() + " / " + pay.getTxDateTime());
			}
		};
	}
}
