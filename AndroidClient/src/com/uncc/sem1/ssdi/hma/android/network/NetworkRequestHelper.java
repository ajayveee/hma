package com.uncc.sem1.ssdi.hma.android.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NetworkRequestHelper {
	private static NetworkRequestHelper mInstance;
	private RequestQueue mRequestQueue;
	private static Context mCtx;

	private NetworkRequestHelper(Context context) {
		mCtx = context;
	}

	public static synchronized NetworkRequestHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new NetworkRequestHelper(context);
		}
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			// getApplicationContext() is key, it keeps you from leaking the
			// Activity or BroadcastReceiver if someone passes one in.
			mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}

}
