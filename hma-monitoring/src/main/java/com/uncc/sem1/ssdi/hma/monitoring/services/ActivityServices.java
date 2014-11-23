package com.uncc.sem1.ssdi.hma.monitoring.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.uncc.sem1.ssdi.hma.monitoring.db.DBHelper;
import com.uncc.sem1.ssdi.hma.monitoring.domain.Activity;
import com.uncc.sem1.ssdi.hma.monitoring.domain.ActivityType;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.ActivityResponse;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.HMAResponse;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Status;

@Path("/activity")
public class ActivityServices {

	private static Logger logger = Logger.getLogger(ActivityServices.class);

	@POST
	@Path("/set")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HMAResponse setActivity(Activity activity) {
		HMAResponse response = new HMAResponse();
		Connection conn = null;
		try {
			conn = DBHelper.getInstance().getConnection();
			String sql = "insert into activity (activityID, userid, activitytypeid, startdate, enddate, caloriesburned, temperature, humidity) values (?,?,?,?,?,?,?,?)";
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT activityid_seq.NEXTVAL FROM DUAL");
			rs.next();
			int activityID = rs.getInt(1);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, activityID);
			ps.setInt(2, activity.getUser().getUserid());
			ps.setInt(3, activity.getActivityType().getActivityTypeId());
			ps.setDate(4, new java.sql.Date(activity.getStartDate().getTime()));
			ps.setDate(5, new java.sql.Date(activity.getEndDate().getTime()));
			ps.setDouble(6, activity.getCaloriesBurned());
			ps.setDouble(7, activity.getTemperature());
			ps.setDouble(8, activity.getHumidity());
			ps.executeUpdate();
			DBHelper.commit(conn);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setResponseMsg(e.getMessage());
			response.setStatus(Status.FAILURE);
		} finally {
			DBHelper.closeQuietly(conn);
		}
		return response;
	}

	@POST
	@Path("/get")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ActivityResponse getActivitiesInTimeFrame(Activity activity) {
		ActivityResponse activityResponse = new ActivityResponse();
		Connection conn = null;
		try {
			conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from activities a join knownactivities ka on a.activitytypeid = ka.activitytypeid where userid=? and startdate >= ? and enddate <= ?");
			ps.setInt(1, activity.getUser().getUserid());
			ps.setDate(2, new java.sql.Date(activity.getStartDate().getTime()));
			ps.setDate(3, new java.sql.Date(activity.getEndDate().getTime()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Activity tactivity = new Activity();
				tactivity.setStartDate(rs.getDate("STARTDATE"));
				tactivity.setEndDate(rs.getDate("ENDDATE"));
				tactivity.setCaloriesBurned(rs.getDouble("CALORIESBURNED"));
				ActivityType at = new ActivityType();
				at.setActivityTypeId(rs.getInt("ACTIVITYTYPEID"));
				at.setActivity(rs.getString("ACTIVITYTYPE"));
				tactivity.setActivityType(at);
				activityResponse.getActivities().add(tactivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			activityResponse.setStatus(Status.FAILURE);
			activityResponse.setResponseMsg(e.getMessage());
		} finally {
			DBHelper.closeQuietly(conn);
		}
		return activityResponse;

	}
}
