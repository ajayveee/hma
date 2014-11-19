package com.uncc.sem1.ssdi.hma.monitoring.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.uncc.sem1.ssdi.hma.monitoring.db.DBHelper;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Response;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Status;

/**
 * All login services
 * @author ajay&aswathi
 *
 */
@Path("login")
public class LoginServices {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.closeQuietly(conn);
		}
		return resp;
	}
}
