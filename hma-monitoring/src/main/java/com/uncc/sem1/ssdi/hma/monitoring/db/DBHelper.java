package com.uncc.sem1.ssdi.hma.monitoring.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBHelper {
	private DataSource ds;
	private static final DBHelper instance = new DBHelper();
	private static Logger logger = Logger.getLogger(DBHelper.class);

	private DBHelper() {

		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/MonitoringDB");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Connection getConnection() throws SQLException {
		return getConnection(false);
	}

	public Connection getConnection(boolean autoCommit) throws SQLException {
		Connection connection = ds.getConnection();
		connection.setAutoCommit(autoCommit);
		return connection;
	}

	public static DBHelper getInstance() {
		return instance;
	}

	public static void closeQuietly(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static java.sql.Date getCurrentSQLDate() {
		return new java.sql.Date(new Date().getTime());
	}

	public static java.sql.Date getSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static void rollbackQuitely(Connection connection) {
		try {
			if (connection != null) {
				connection.rollback();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void commit(Connection connection) {
		try {
			connection.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
