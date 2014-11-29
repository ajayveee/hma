package com.uncc.sem1.ssdi.hma.monitoring.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.uncc.sem1.ssdi.hma.monitoring.db.DBHelper;
import com.uncc.sem1.ssdi.hma.monitoring.domain.Activity;
import com.uncc.sem1.ssdi.hma.monitoring.domain.ActivityType;
import com.uncc.sem1.ssdi.hma.monitoring.domain.Target;
import com.uncc.sem1.ssdi.hma.monitoring.domain.User;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.HMAResponse;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Status;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.TargetResponse;

@Path("/target")
public class TargetServices {
	private static Logger logger = Logger.getLogger(TargetServices.class);

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
		Connection conn = null;
		try {
			conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from target t join knownactivities ka on t.activitytypeid = ka.activitytypeid join user u on u.userid = t.userid where username=?");
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
			e.printStackTrace();
			response.setResponseMsg(e.getMessage());
			response.setStatus(Status.FAILURE);
		} finally {
			DBHelper.closeQuietly(conn);
		}
		return response;
	}

	@POST
	@Path("/set")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HMAResponse setTarget(Target target) {
		HMAResponse response = new HMAResponse();
		Connection conn = null;
		try {
			conn = DBHelper.getInstance().getConnection();
			String sql = "insert into target (targetID, activitytypeid, startdate, enddate, userid, calories, DURATION) values (?,?,?,?,?,?,?)";
			ResultSet rs = conn.createStatement().executeQuery("SELECT targetid_seq.NEXTVAL FROM DUAL");
			rs.next();
			int targetID = rs.getInt(1);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, targetID);
			ps.setInt(2, target.getActivityType().getActivityTypeId());
			ps.setTimestamp(3, DBHelper.getSQLTimestamp(target.getStartDate()));
			ps.setTimestamp(4, DBHelper.getSQLTimestamp(target.getEndDate()));
			ps.setInt(5, target.getUser().getUserid());
			ps.setDouble(6, target.getCalories());
			ps.setDouble(7, target.getDurationInHrs());
			ps.executeUpdate();
			DBHelper.commit(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResponseMsg(e.getMessage());
			response.setStatus(Status.FAILURE);
		} finally {
			DBHelper.closeQuietly(conn);
		}
		return response;
	}

	@POST
	@Path("/getMatchingTarget")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TargetResponse getMatchingTarget(Activity activity) {
		TargetResponse response = new TargetResponse();
		List<Target> targets = new ArrayList<Target>();
		Connection conn = null;
		try {
			conn = DBHelper.getInstance().getConnection();
			targets.add(getMatchingTarget(activity, conn));
			response.setTargets(targets);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setResponseMsg(e.getMessage());
			response.setStatus(Status.FAILURE);
		} finally {
			DBHelper.closeQuietly(conn);
		}
		return response;
	}

	public Target getMatchingTarget(Activity activity, Connection conn) throws SQLException {
		String sql = "select * from target where userid=? and activitytypeid= ? "
				+ " and STARTDATE <= ? and ENDDATE >= ? and CURRENTPROGRESS != 100 order by startdate";
		Target target = null;
		// STARTDATE ENDDATE CURRENTPROGRESS
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, activity.getUser().getUserid());
		pstmt.setInt(2, activity.getActivityType().getActivityTypeId());
		pstmt.setTimestamp(3, DBHelper.getSQLTimestamp(activity.getStartDate()));
		pstmt.setTimestamp(4, DBHelper.getSQLTimestamp(activity.getEndDate()));
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			target = new Target();
			target.setActivityType(new ActivityType(activity.getActivityType().getActivityTypeId()));
			target.setStartDate(rs.getDate("STARTDATE"));
			target.setEndDate(rs.getDate("ENDDATE"));
			target.setTargetId(rs.getInt("TARGETID"));
			target.setDurationInHrs(rs.getDouble("DURATION"));
			target.setUser(activity.getUser());
			target.setCompletedPercentage(rs.getInt("CURRENTPROGRESS"));
		}
		return target;
	}

	public void updateTargetPercentage(int targetId, int percentage, Connection conn) {
		String sql = "update target set CURRENTPROGRESS=? where TARGETID=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, percentage);
			ps.setInt(2, targetId);
			int rowsUpdated = ps.executeUpdate();
			logger.info(rowsUpdated + " rows updated during target progress update for " + targetId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
