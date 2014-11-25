package com.uncc.sem1.ssdi.hma.monitoring.helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.uncc.sem1.ssdi.hma.monitoring.db.DBHelper;
import com.uncc.sem1.ssdi.hma.monitoring.domain.ActivityType;

public class MonitoringHelper {
	private Map<Integer, ActivityType> activityTypes= new HashMap<Integer, ActivityType>();
	private static MonitoringHelper instance;

	private MonitoringHelper(Connection connection) {
		loadActivityTypes(connection);
	}

	private MonitoringHelper() {
		loadActivityTypes();
	}

	public static MonitoringHelper getInstance(Connection connection) {
		if(instance == null){
			instance = new MonitoringHelper(connection);
		}
		return instance ;
	}
	public static MonitoringHelper getInstance(){
		if(instance == null){
			instance = new MonitoringHelper();
		}
		return instance ;
	}
	private void loadActivityTypes(){
		Connection conn;
		try {
			conn = DBHelper.getInstance().getConnection();
			loadActivityTypes(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void loadActivityTypes(Connection conn) {
		try {
			//conn = DBHelper.getInstance().getConnection();
			String sql = "select * from knownactivities";
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				ActivityType activityType = new ActivityType();
				activityType.setActivityTypeId(rs.getInt("ACTIVITYTYPEID"));
				activityType.setActivity(rs.getString("ACTIVITYTYPE"));
				activityType.setCaloriesBurned(rs
						.getDouble("CALORIESBURNEDPERHOUR"));
				activityTypes.put(activityType.getActivityTypeId(),
						activityType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<Integer, ActivityType> getActivityTypes() {
		return activityTypes;
	}
}
