package com.uncc.sem1.ssdi.hma.monitoring.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.uncc.sem1.ssdi.hma.monitoring.db.DBHelper;
import com.uncc.sem1.ssdi.hma.monitoring.domain.ActivityType;
import com.uncc.sem1.ssdi.hma.monitoring.domain.Target;
import com.uncc.sem1.ssdi.hma.monitoring.domain.User;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Response;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Status;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.TargetResponse;

@Path("/target")
public class TargetServices {

	/**
	 * View targets set by user
	 * 
	 * @param userid
	 * @return
	 */
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public TargetResponse getTargets(@PathParam("param") String username) {
		TargetResponse response = new TargetResponse();
		Connection conn;
		try {
			conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from targets t join knownactivities ka on t.activitytypeid = ka.activitytypeid join user u on u.userid = t.userid where username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Target target = new Target();
				target.setUser(new User(rs.getInt("targetId")));
				target.setStartDate(rs.getDate("startdate"));
				target.setEndDate(rs.getDate("enddate"));
				ActivityType at = new ActivityType();
				at.setActivityTypeId(rs.getInt("ACTIVITYTYPEID"));
				at.setActivity(rs.getString("ACTIVITYTYPE"));
				target.setActivityType(at);
				response.getTargets().add(target);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Path("/set")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setTarget(Target target) {
		Response response = new Response();
		Connection conn;
		try {
			conn = DBHelper.getInstance().getConnection();
			String sql = "insert into targets (targetID, activitytypeid, startdate, enddate, userid, calories) values (?,?,?,?,?,?)";
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT targetid_seq.NEXTVAL FROM DUAL");
			rs.next();
			int targetID = rs.getInt(1);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, targetID);
			ps.setInt(2, target.getActivityType().getActivityTypeId());
			ps.setDate(3, new java.sql.Date(target.getStartDate().getTime()));
			ps.setDate(4, new java.sql.Date(target.getEndDate().getTime()));
			ps.setInt(5, target.getUser().getUserid());
			ps.setDouble(6, target.getCalories());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResponseMsg(e.getMessage());
			response.setStatus(Status.FAILURE);
		}
		return response;
	}
}
