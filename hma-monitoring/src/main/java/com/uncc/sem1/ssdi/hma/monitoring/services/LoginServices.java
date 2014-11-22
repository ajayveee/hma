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
import com.uncc.sem1.ssdi.hma.monitoring.domain.User;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Response;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Status;

/**
 * All login services
 * 
 * @author ajay&aswathi
 * 
 */
@Path("login")
public class LoginServices {
	private static Logger logger = Logger.getLogger(LoginServices.class);

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserDetails(@PathParam("param") String username) {
		Response resp = new Response(username, Status.SUCCESS);
		Connection conn = null;
		try {

			conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from user where name=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int age = rs.getInt("age");
				resp.setResponseMsg("age =" + age);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DBHelper.closeQuietly(conn);
		}
		return resp;
	}
	@POST
	@Path("/set")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response signUp(User user){
		Response response = new Response();
		Connection conn;
		try {
			conn = DBHelper.getInstance().getConnection();
			String sql = "insert into user (userID, username, password, email, firstname, lastname, birthday) values (?,?,?,?,?,?,?)";
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
			ps.setDate(7, new java.sql.Date(user.getDob().getTime()));
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
