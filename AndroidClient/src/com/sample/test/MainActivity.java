package com.sample.test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.gson.GsonRequest;
import com.uncc.sem1.ssdi.hma.monitoring.domain.ActivityType;
import com.uncc.sem1.ssdi.hma.monitoring.domain.Target;
import com.uncc.sem1.ssdi.hma.monitoring.domain.User;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.HMAResponse;
import com.uncc.sem1.ssdi.hma.monitoring.services.response.UserResponse;

public class MainActivity extends ActionBarActivity {

	public static final String EXTRA_MESSAGE = null;
	private Gson gson = new GsonBuilder().create();;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void fetchUrlGson(View view) throws JSONException {
		final TextView txtView = (TextView) findViewById(R.id.txt_message);
		txtView.setText("hi");
		String url = "http://10.0.3.2:8080/hma-monitoring/rest/target/set";

		Target target = new Target();
		target.setCompletedPercentage(10);
		Calendar calendar = Calendar.getInstance(); // this would default to now
		calendar.add(Calendar.DAY_OF_MONTH, -5);
		target.setStartDate(calendar.getTime());
		target.setEndDate(new Date());
		target.setActivityType(new ActivityType(1));
		target.setUser(new User(2));
		target.setCalories(20);

		String targetString = gson.toJson(target);
		JSONObject jsonRequest = new JSONObject(targetString);
		GsonRequest<HMAResponse> req = 
				new GsonRequest<HMAResponse>(Request.Method.POST, url, HMAResponse.class, jsonRequest, 
						new Response.Listener<HMAResponse>() {

					@Override
					public void onResponse(HMAResponse response) {

						try {
							txtView.setText("Response: " + response.getResponseMsg() + " :: " + response.getStatus());
							System.out.println(response);
						} catch (Throwable e) {
							System.out.println(e);// TODO: handle exception
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						txtView.setText("Error: " + error.getMessage());

					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json; charset=utf-8");
				// headers.put("User-agent", "My useragent");
				return headers;
			}
		};

		RequestQueue queue = Volley.newRequestQueue(this);
		queue.add(req);
	}


	/*public void fetchUrl(View view) throws JSONException {
		final TextView txtView = (TextView) findViewById(R.id.txt_message);
		txtView.setText("hi");
		String url = "http://10.0.3.2:8080/hma-monitoring/rest/target/set";

		Target target = new Target();
		target.setCompletedPercentage(10);
		target.setStartDate(new Date());
		target.setEndDate(new Date());
		target.setActivityType(new ActivityType(1));
		target.setUser(new User(2));
		target.setCalories(20);

		String targetString = gson.toJson(target);
		JSONObject jsonRequest = new JSONObject(targetString);
		JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url,
				jsonRequest, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						try {
							txtView.setText("Response: " + response.toString());
							com.uncc.sem1.ssdi.hma.monitoring.services.response.Response r = gson.fromJson(
									response.toString(),
									com.uncc.sem1.ssdi.hma.monitoring.services.response.Response.class);
							System.out.println(r);
						} catch (Throwable e) {
							System.out.println(e);// TODO: handle exception
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						txtView.setText("Error: " + error.getMessage());

					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json; charset=utf-8");
				// headers.put("User-agent", "My useragent");
				return headers;
			}
		};

		RequestQueue queue = Volley.newRequestQueue(this);
		queue.add(req);
	}*/
	public void fetchUrl(View view) {
		String url = "http://10.0.3.2:8080/hma-monitoring/rest/login/chan";
		final TextView txtView = (TextView) findViewById(R.id.txt_message);
		txtView.setText("hi");
		GsonRequest<UserResponse> req = 
				new GsonRequest<UserResponse>(Request.Method.GET, url, UserResponse.class, null, 
						new Response.Listener<UserResponse>() {

					@Override
					public void onResponse(UserResponse response) {

						try {
							txtView.setText("Response: " + response.getResponseMsg() + " :: " + response.getStatus());
							System.out.println(response);
						} catch (Throwable e) {
							System.out.println(e);// TODO: handle exception
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						txtView.setText("Error: " + error.getMessage());

					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json; charset=utf-8");
				// headers.put("User-agent", "My useragent");
				return headers;
			}
		};

		RequestQueue queue = Volley.newRequestQueue(this);
		queue.add(req);
	}
	public void fetchUrlGet(View view) {
		final TextView txtView = (TextView) findViewById(R.id.txt_message);
		txtView.setText("hi");
		String url = "http://10.0.3.2:8080/hma-monitoring/rest/login/ajay";
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(
				Request.Method.GET, url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						try {
							txtView.setText("Response: " + response.toString());
							HMAResponse r = gson.fromJson(
									response.toString(),
									HMAResponse.class);
							System.out.println(r);
						} catch (Throwable e) {
							System.out.println(e);// TODO: handle exception
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						txtView.setText("Error: " + error.getMessage());

					}
				});
		RequestQueue queue = Volley.newRequestQueue(this);
		queue.add(jsObjRequest);

	}
}
