package com.utoo.gson;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

public class PostVelly {

	public static <T> JsonObjectRequest VolleyPostJson(final Handler handler,
			String url, BaseRequestParam requestParams, final Class<T> responCls)
			throws JSONException {
		Log.e("e", GsonHelper.getJson(requestParams).toString());
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Method.POST, url, GsonHelper.getJson(requestParams),
				new Response.Listener<JSONObject>() {

					// 请求成功监听
					@Override
					public void onResponse(JSONObject response) {
						Log.e("TAGSuc", response.toString());
						// String res = response.toString();

						int state = response.optInt("State");
						Message msg = new Message();
						if (state == 1) {
							JSONObject jsonbean = response
									.optJSONObject("ReturnValue");
							Object obj = null;
							try {
								obj = GsonHelper.getBean(jsonbean.toString(),
										responCls);
							} catch (Exception e) {
								msg.what = 11;
								msg.obj = "生成类失败";
								handler.sendMessage(msg);
								e.printStackTrace();
							}

							msg.what = 1;
							msg.obj = obj;
							handler.sendMessage(msg);
						} else if (state == 0) {
							msg.what = 11;
							msg.obj = response.optString("Message");
							handler.sendMessage(msg);
						}

					}
				}, new Response.ErrorListener() {
					// 请求失败监听
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAGError", error.getMessage(), error);
					}
				}) {
			public Map<String, String> getHeaders() throws AuthFailureError {
				// Content-Type: application/json
				// Host: www.kanggou.com
				// Connection: Keep-Alive
				HashMap<String, String> headers = new HashMap<String, String>();

				headers.put("Connection", "Keep-Alive");

				// headers.put("Content-Type", "application/json");

				return headers;

			}
		};
		return jsonObjectRequest;
	}

	public static <T> StringRequest VolleyPostString(final Handler handler,
			String url, final HashMap<String, String> requestParams,
			final Class<T> responCls) {

		StringRequest mStringRequest = new StringRequest(Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// System.out.println("请求结果:" + response);
						try {
							String responseJson = response.substring(
									response.indexOf("{"),
									response.lastIndexOf("}"));// 截取json部分

							JSONObject jo = new JSONObject(responseJson);
							int state = jo.optInt("state");
							Message msg = new Message();
							if (state == 1) {
								JSONObject jsonbean = jo.optJSONObject("Value");
								Object obj = GsonHelper.getBean(
										jsonbean.toString(), responCls);

								msg.what = 1;
								msg.obj = obj;
								handler.sendMessage(msg);
							} else if (state == 0) {
								msg.what = 11;
								msg.obj = jo.optString("Message");
								handler.sendMessage(msg);
							}

						} catch (Exception e) {
							Message msg = new Message();
							msg.what = 12;
							msg.obj = "解析失败";
							handler.sendMessage(msg);
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						System.out.println("请求错误:" + error.toString());
						Message msg = new Message();
						msg.what = 12;
						msg.obj = error.toString();
						handler.sendMessage(msg);
					}
				}) {
			// 添加请求参数
			@Override
			protected HashMap<String, String> getParams()
					throws AuthFailureError {
				return requestParams;

			}

			// Volley请求类提供了一个 getHeaders（）的方法，重载这个方法可以自定义HTTP 的头信息。（也可不实现）
			public Map<String, String> getHeaders() throws AuthFailureError {

				HashMap<String, String> headers = new HashMap<String, String>();

				headers.put("Accept", "application/json");

				headers.put("Content-Type", "application/json; charset=UTF-8");

				return headers;

			}

		};
		return mStringRequest;
		// mRequestQueue.add(mStringRequest);
	}

}
