package com.uncc.sem1.ssdi.hma.monitoring.helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.uncc.sem1.ssdi.hma.monitoring.db.DBHelper;
import com.uncc.sem1.ssdi.hma.monitoring.domain.ActivityType;

public class MonitoringHelper {
	private Map<Integer, ActivityType> activityTypes;
	private static MonitoringHelper instance = new MonitoringHelper();

	private MonitoringHelper() {
		loadActivityTypes();
	}

	public static MonitoringHelper getInstance() {
		return instance;
	}

	private void loadActivityTypes() {
		Connection conn = null;
		try {
			conn = DBHelper.getInstance().getConnection();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Map<Integer, ActivityType> getActivityTypes() {
		return activityTypes;
	}
}
