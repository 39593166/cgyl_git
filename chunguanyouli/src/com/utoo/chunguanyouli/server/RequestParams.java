package com.utoo.chunguanyouli.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

public class RequestParams implements Serializable {
	private static final long serialVersionUID = 5722324918731848126L;

	private HashMap<String, Object> param;

	public HashMap<String, Object> getParam() {
		return param;
	}

	public void setParam(HashMap<String, Object> param) {
		this.param = param;
	}

	public RequestParams() {
		this.param = new HashMap<String, Object>();
	}

	public void put(String key, Object value) {
		this.param.put(key, value);
	}

	

}
