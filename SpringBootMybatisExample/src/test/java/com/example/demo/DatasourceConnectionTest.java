package com.example.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DatasourceConnectionTest 
{
	@Autowired
	private DataSource datasourceForMysql;

	@Test
	@Transactional
	public void contextLoads() throws SQLException 
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		// Get connection from DataSource
		conn = datasourceForMysql.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT version()");

		if (rs.next()) {
			System.out.println("Database Version : " + rs.getString(1));
		}
	}
}
