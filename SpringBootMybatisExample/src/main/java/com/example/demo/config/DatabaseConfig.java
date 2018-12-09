package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // Spring IOC Container가 해당 클래스를 빈(Bean) 정의 소스로 사용한다는 의미.
public class DatabaseConfig 
{
	@Bean(name="mysqlDataSource")
	public DataSource datasource(Environment environment)
	{
		BasicDataSource basicDatasource = new BasicDataSource();
		basicDatasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDatasource.setUrl("jdbc:mysql://localhost:3306/jpa_ex?useSSL=false&&serverTimezone=UTC");
		basicDatasource.setUsername("root");
		basicDatasource.setPassword("eogur4415@");
		
		return basicDatasource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception
	{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/example/demo/xml/*.xml"));
		
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory)
	{
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
