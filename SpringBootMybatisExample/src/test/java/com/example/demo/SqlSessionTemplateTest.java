package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SqlSessionTemplateTest 
{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Test
	@Transactional
	public void test()
	{
		String result = (String)sqlSessionTemplate.selectOne("selectVersion");
		System.out.println(result);
	}
}
