package com.utoo.chunguanyouli.server;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONObject;

import com.utoo.gson.GsonHelper;

public class Request {
	private String url;
	private int Method = 1;// 默认get
	private int TAG = 1;
	private RequestParams requestParam;

	public static final int METHOD_GET = 1;
	public static final int METHOD_POST = 2;

	public Request(int tag) {
		this.setTAG(tag);
		requestParam = new RequestParams();
	}
	
	public RequestParams getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(RequestParams requestParam) {
		this.requestParam = requestParam;
	}

	public void putParam(String key, Object value) {
		requestParam.put(key, value);
	}

	/**
	 * 获取post的json
	 * 
	 * @return
	 */
	public JSONObject getPostParams() {
		JSONObject json = GsonHelper.getJson(requestParam);
		return json;
	}

	/**
	 * 获取get请求连接
	 * 
	 * @return
	 */
	public String getGetParams() {
		HashMap<String, Object> reParam = requestParam.getParam();
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		for (Entry<String, Object> item : reParam.entrySet()) {
			sb.append("&");
			sb.append(item.getKey());
			sb.append("=");
			sb.append(item.getValue());
		}

		return sb.toString();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public int getMethod() {
		return Method;
	}

	public void setMethod(int method) {
		Method = method;
	}

	public int getTAG() {
		return TAG;
	}

	public void setTAG(int tAG) {
		TAG = tAG;
	}

}
