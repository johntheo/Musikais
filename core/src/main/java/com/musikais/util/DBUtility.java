package com.musikais.util;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtility {
	public static final String DB_NAME = "musikais_curitiba";
	public static final String DB_NAME_OLD = "musikais_db";
	public static final String DB_NAME_OPENSHIFT = System
			.getenv("OPENSHIFT_APP_NAME");

	public static DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://"
				+ System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":"
				+ System.getenv("OPENSHIFT_MYSQL_DB_PORT") + "/" + DB_NAME);
		dataSource.setUsername(System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"));
		dataSource.setPassword(System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(1800000L);
		dataSource.setNumTestsPerEvictionRun(3);
		dataSource.setMinEvictableIdleTimeMillis(1800000L);
		dataSource.setValidationQuery("SELECT 1");
		return dataSource;
	}

	public static DataSource dataSourceOld() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://"
				+ System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":"
				+ System.getenv("OPENSHIFT_MYSQL_DB_PORT") + "/" + DB_NAME_OLD);
		dataSource.setUsername(System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"));
		dataSource.setPassword(System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(1800000L);
		dataSource.setNumTestsPerEvictionRun(3);
		dataSource.setMinEvictableIdleTimeMillis(1800000L);
		dataSource.setValidationQuery("SELECT 1");
		return dataSource;
	}

	public static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}

}