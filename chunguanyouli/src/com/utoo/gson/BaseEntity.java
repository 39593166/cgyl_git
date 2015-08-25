package com.utoo.gson;

import java.io.Serializable;

import org.json.JSONObject;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8199731127166070003L;

	public JSONObject getGson() {
		return GsonHelper.getJson(this);
	}
}
