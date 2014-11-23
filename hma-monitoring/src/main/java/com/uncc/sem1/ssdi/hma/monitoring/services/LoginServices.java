package com.uncc.sem1.ssdi.hma.monitoring.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.uncc.sem1.ssdi.hma.monitoring.db.DBHelper;
import com.uncc.sem1.ssdi.hma.monitoring.domain.BiologicalProfile;
import com.uncc.sem1.ssdi.hma.monitoring.domain.Constants;
import com.uncc.sem1.ssdi.hma.monitoring.domain.User;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.HMAResponse;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Status;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.UserResponse;

/**
 * All login services
 * 
 * @author ajay&aswathi
 * 
 */
@Path("/login")
public class LoginServices {
	private static Logger logger = Logger.getLogger(LoginServices.class);

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponse getUserDetails(@PathParam("param") String username) {
		UserResponse resp = new UserResponse("", Status.SUCCESS);
		Connection conn = null;
		try {

			conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from user where username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resp.getUser().setPassword(rs.getString("PASSWORD"));
				resp.getUser().setFirstName(rs.getString("FIRSTNAME"));
				resp.getUser().setLastName(rs.getString("LASTNAME"));
				resp.getUser().setDob(rs.getDate("BIRTHDAY"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resp.setStatus(Status.FAILURE);
			resp.setResponseMsg(e.getMessage());
		} finally {
			DBHelper.closeQuietly(conn);
		}
		return resp;
	}

	@POST
	@Path("/set")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HMAResponse signUp(User user) {
		HMAResponse response = new HMAResponse();
		Connection conn = null;
		try {
			conn = DBHelper.getInstance().getConnection();
			String sql = "insert into user (userID, username, password, email, firstname, lastname, birthday, age) values (?,?,?,?,?,?,?,?)";
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT userid_seq.NEXTVAL FROM DUAL");
			rs.next();
			int userId = rs.getInt(1);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getFirstName());
			ps.setString(6, user.getLastName());
			if (user.getDob() != null) {
				ps.setDate(7, new java.sql.Date(user.getDob().getTime()));
			} else {
				ps.setDate(7, null);
			}
			ps.setDouble(8, user.getAge());
			ps.executeUpdate();
			if (user.getBiologicalProfile() != null) {
				// Now update biological profile
				BiologicalProfile bio = user.getBiologicalProfile();
				sql = "insert into biologicalProfile (userid, height, weight, hip, neck,"
						+ " waist, wrist, activefrom, activetill, SYSTOLICBLOODPRESSURE,"
						+ " DIASTOLICBLOODPRESSURE, RESTINGHEARTRATE  ) values "
						+ "(?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setDouble(2, bio.getHeight());
				ps.setDouble(3, bio.getWeight());
				ps.setDouble(4, bio.getHip());
				ps.setDouble(5, bio.getNeck());
				ps.setDouble(6, bio.getWaist());
				ps.setDouble(7, bio.getWrist());
				ps.setDate(8, DBHelper.getCurrentSQLDate());
				ps.setDate(9, DBHelper.getSQLDate(Constants.ACTIVE_TILL));
				ps.setDouble(10, bio.getSystolicBloodPressure());
				ps.setDouble(11, bio.getDiastolicBloodPressure());
				ps.setDouble(12, bio.getRestingHeartRate());
				ps.executeUpdate();
			}
			DBHelper.commit(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResponseMsg(e.getMessage());
			response.setStatus(Status.FAILURE);
			DBHelper.rollbackQuitely(conn);
		} finally {
			DBHelper.closeQuietly(conn);
		}
		return response;
	}
}
