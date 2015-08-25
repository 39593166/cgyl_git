package com.utoo.chunguanyouli.ui.base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.utoo.chunguanyouli.MyApplication;
import com.utoo.chunguanyouli.server.ClientConfigs;
import com.utoo.chunguanyouli.server.Request;

public abstract class NetDataBaseFragment extends Fragment {
	private RequestQueue mQueue;
	protected MyApplication app;
	public BaseActionBarActivity act;
	List<Integer> diatags;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		diatags = new ArrayList<Integer>();
		act = (BaseActionBarActivity) getActivity();
		mQueue = Volley.newRequestQueue(act);
//		mQueue = ClientConfigs.getRequestQueue(act.getApplicationContext());
		app = (MyApplication) act.getApplication();
	}

	protected void apply(Request re) {
		isOpenDia(re.getTAG());
		if (re.getMethod() == Request.METHOD_GET) {
			System.out.println(re.getUrl());
			Log.e("url", re.getUrl());
			get(re.getTAG(), re.getGetParams());
		} else if (re.getMethod() == Request.METHOD_POST) {
			Log.e("请求地址：", re.getUrl());
			Log.e("请求参数：", re.getPostParams().toString());
			post(re.getTAG(), re.getUrl(), re.getPostParams());
		}
	}

	public void uploadImage(int userId, String filePath, final int TAG) {

		String url = "http://121.42.138.126:82/cg/pic.aspx?uid=" + userId;
		HttpUtils hu = new HttpUtils();

		RequestParams params = new RequestParams();
		File file = new File(filePath);
		Log.e("fragmentupload", file.length() + "");
		Log.e("fragmentupload", file.getPath());
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

	protected void apply(Request re, boolean isShowDia) {
		if (isShowDia) {
			isOpenDia(re.getTAG());
		}
		if (re.getMethod() == Request.METHOD_GET) {
			System.out.println(re.getUrl());
			get(re.getTAG(), re.getGetParams());
		} else if (re.getMethod() == Request.METHOD_POST) {
			System.out.println("请求地址：" + re.getUrl());
			System.out.println("请求参数：" + re.getPostParams());
			post(re.getTAG(), re.getUrl(), re.getPostParams());
		}
	}

	protected void get(final int TAG, String url) {
		Log.e("url", url);
		StringRequest mStringRequest = new StringRequest(url,

		new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {

				System.out.println("请求结果:" + response);
				Log.e("请求结果:", response.toString());
				onReturned(response, TAG);
				isCloseDia(TAG);
			}

		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

				System.out.println("请求错误:" + error.toString());
				NetFailed(error, TAG);
				isCloseDia(TAG);
			}

		});
		mQueue.add(mStringRequest);

	}

	protected void post(final int TAG, String url, JSONObject jsonObjectParams) {
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
						Log.e("TAGSuc", response.toString());
						Log.e("请求结果:", response.toString());
						onReturned(response.toString(), TAG);
						isCloseDia(TAG);
					}

				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAGError", error.getMessage(), error);
						NetFailed(error, TAG);
						isCloseDia(TAG);
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
		act.showProgressDialog("请稍等");
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
			act.closeProgressDialog();
		}

	}

	protected abstract void NetFailed(VolleyError error, int TAG);

	protected abstract void onReturned(String response, int TAG);

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		mQueue.cancelAll(this);
		super.onDestroy();
	}

}
