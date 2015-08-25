package com.utoo.chunguanyouli.ui.base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.utoo.chunguanyouli.server.ClientConfigs;
import com.utoo.chunguanyouli.server.Request;
import com.utoo.chunguanyouli.tool.ImageSubTool;

public abstract class NetDataBaseActionBarActivity extends
		BaseActionBarActivity {
	private RequestQueue mQueue;
	List<Integer> diatags;
	boolean isCloseDiaAuto = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		diatags = new ArrayList<Integer>();
		 mQueue = Volley.newRequestQueue(this.getApplicationContext());
//		mQueue = ClientConfigs.getRequestQueue(getApplicationContext());
		Log.e("mQueue", mQueue.toString());
	}

	public void apply(Request re, boolean isShowDia) {
		if (isShowDia) {
			isOpenDia(re.getTAG());
		} else {
			isCloseDiaAuto = false;
		}
		if (re.getMethod() == Request.METHOD_GET) {
			System.out.println(re.getUrl());
			Log.e("url", re.getUrl());
			get(re.getTAG(), re.getGetParams(), isShowDia);
		} else if (re.getMethod() == Request.METHOD_POST) {
			System.out.println("请求地址：" + re.getUrl());
			System.out.println("请求参数：" + re.getPostParams());
			Log.e("请求地址：", re.getUrl());
			Log.e("请求参数：", re.getPostParams().toString());
			post(re.getTAG(), re.getUrl(), re.getPostParams(), isShowDia);
		}
	}

	public void apply(Request re) {
		isOpenDia(re.getTAG());
		if (re.getMethod() == Request.METHOD_GET) {
			System.out.println(re.getUrl());
			get(re.getTAG(), re.getGetParams(), true);
		} else if (re.getMethod() == Request.METHOD_POST) {
			System.out.println("请求地址：" + re.getUrl());
			System.out.println("请求参数：" + re.getPostParams());
			post(re.getTAG(), re.getUrl(), re.getPostParams(), true);
		}
	}

	public void uploadImage(int userId, String filePath, final int TAG) {
		File file = ImageSubTool.pressPic(filePath, 800, 800);
		String url = "http://121.42.138.126:82/cg/pic.aspx?uid=" + userId;
		HttpUtils hu = new HttpUtils();
		showProgressDialog("正在提交");
		RequestParams params = new RequestParams();
		params.addBodyParameter("file", file);
		hu.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				// LogUtils.e(arg1 + arg0.getExceptionCode(), arg0);
				NetFailed(null, TAG);
				isCloseDia(TAG);
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				// LogUtils.e(responseInfo.result);
				onReturned(responseInfo.result, TAG);
				isCloseDia(TAG);
			}
		});

	}

	protected void get(final int TAG, String url, boolean isShowdia) {

		StringRequest mStringRequest = new StringRequest(url,

		new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				isCloseDia(TAG);
				Log.e("请求结果:", response);
				onReturned(response, TAG);
			}

		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				isCloseDia(TAG);
				System.out.println("请求错误:" + error.toString());
				NetFailed(error, TAG);
			}

		});
		mQueue.add(mStringRequest);

	}

	protected void post(final int TAG, String url, JSONObject jsonObjectParams,
			final boolean isShowDia) {
		if (jsonObjectParams == null) {
			Log.e("requestParams", "null");
		} else {
			Log.e("requestParams", jsonObjectParams.toString());
		}
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Method.POST, url, jsonObjectParams,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						if (isShowDia)
							isCloseDia(TAG);
						onReturned(response.toString(), TAG);
					}

				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if (isShowDia)
							isCloseDia(TAG);
						NetFailed(error, TAG);
					}
				}) {
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Connection", "Keep-Alive");
				return headers;
			}
		};
		mQueue.add(jsonObjectRequest);
	}

	private void isOpenDia(int TAG) {
		diatags.add(TAG);
		showProgressDialog("请稍等");
	}

	private void isCloseDia(Integer TAG) {
		int temp = -1;
		for (int i = 0; i < diatags.size(); i++) {
			if (diatags.get(i) == TAG) {
				temp = i;
				break;
			}
		}
		if (temp > -1) {
			diatags.remove(temp);
		}
		if (diatags.size() == 0) {
			if (isCloseDiaAuto)
				closeProgressDialog();
		}

	}

	protected abstract void NetFailed(VolleyError error, int TAG);

	protected abstract void onReturned(String response, int TAG);

	// protected abstract void NetFailed(VolleyError error, int TAG1, int TAG2);
	//
	// protected abstract void onReturned(JSONObject response, int TAG1, int
	// TAG2);

	@Override
	protected void onStop() {
		super.onStop();
		mQueue.cancelAll(this);
	}
}
