package com.utoo.chunguanyouli.server;

import org.json.JSONException;
import org.json.JSONObject;

public class CgResponse {
	String response;

	public CgResponse(String response) {
		// TODO Auto-generated constructor stub
		this.response = response;
	}

	public int getState() {
		try {
			JSONObject json = new JSONObject(response);
			return json.optInt("state");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int getValNum() {
		try {
			JSONObject json = new JSONObject(response);
			return json.optInt("num");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getSid() {
		try {
			JSONObject json = new JSONObject(response);
			return json.optString("sid");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "0";
	}

	public String getValStr() {
		try {
			JSONObject json = new JSONObject(response);
			return json.optString("val");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "[]";
	}
	
}
