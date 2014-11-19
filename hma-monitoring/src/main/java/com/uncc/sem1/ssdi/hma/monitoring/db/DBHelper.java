package com.uncc.sem1.ssdi.hma.monitoring.db;

import java.sql.Connection;
import java.sql.SQLException;

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
		return ds.getConnection();
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
}
