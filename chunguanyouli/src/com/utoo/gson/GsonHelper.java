package com.utoo.gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonHelper {

	public static JSONObject getJson(Object obj) {
		Gson gson = new Gson();
		JSONObject json = null;
		try {
			json = new JSONObject(gson.toJson(obj));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public static JSONArray getJa(Object obj) {
		Gson gson = new Gson();
		JSONArray json = null;
		try {
			json = new JSONArray(gson.toJson(obj));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public static <T> T getBean(String jsonString, Class<T> cls)
			throws Exception {
		T t = null;
		try {
			Gson gson = new Gson();
			t = gson.fromJson(jsonString, cls);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return t;
	}

	public static <T> List<T> getBeans(String jsonString, Class<T> cls)
			throws Exception {
		List<T> list = new ArrayList<T>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
			}.getType());
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	public static List<Map<String, Object>> listKeyMaps(String jsonString)
			throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(jsonString,
					new TypeToken<List<Map<String, Object>>>() {
					}.getType());
		} catch (Exception e) {
			throw e;
		}
		return list;
	}
}
