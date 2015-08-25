package com.utoo.chunguanyouli.server;

import java.util.HashMap;
import java.util.Map.Entry;

public class GETRequest {
	private String host;
	private HashMap<String, Object> params;

	public GETRequest() {
		params = new HashMap<String, Object>();
	}

	public GETRequest setHost(String host) {
		this.host = host;
		return this;
	}

	public GETRequest setParam(String key, String value) {
		params.put(key, value);
		return this;
	}

	public String getRequestUrl() {
		StringBuilder request = new StringBuilder(host);
		if (params != null) {
			request.append("?");
			boolean isFirst = true;
			for (Entry<String, Object> item : params.entrySet()) {
				if (isFirst) {
					request.append(item.getKey() + "=" + item.getValue());
					isFirst = false;
				} else {
					request.append("&" + item.getKey() + "=" + item.getValue());
				}
			}
			return request.toString();
		} else {
			return host;
		}
	}

	public GETRequest setUserId(String userId) {
		params.put("id", userId);
		return this;
	}

}
