package com.uncc.sem1.ssdi.hma.monitoring.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.uncc.sem1.ssdi.hma.monitoring.db.DBHelper;
import com.uncc.sem1.ssdi.hma.monitoring.domain.Activity;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Response;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.Status;

@Path("activity")
public class ActivityServices {

	private static Logger logger = Logger.getLogger(ActivityServices.class);
	
	@POST
	@Path("/set")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setActivity(Activity activity){
		Response response = new Response();
		Connection conn;
		try {
			conn = DBHelper.getInstance().getConnection();
			String sql = "insert into activity (targetID, type, startdate, enddate, user) values (?,?,?,?,?)";
			ResultSet rs = conn.createStatement().executeQuery("SELECT activityid_seq.NEXTVAL FROM DUAL");
			rs.next();
			int activityID = rs.getInt(1);
			PreparedStatement ps = conn.prepareStatement(sql);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			response.setResponseMsg(e.getMessage());
			response.setStatus(Status.FAILURE);
		}
		return response;
	}
}
