package com.utoo.chunguanyouli.server;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ClientConfigs {
	public static final String APIHOST_USER = "http://121.42.138.126:82/cg/user.aspx";
	public static final String APIHOST_CITY = "http://121.42.138.126:82/cg/city.aspx";
	public static final String APIHOST_INDEX = "http://121.42.138.126:82/cg/index.aspx";
	public static final String APIHOST_ORDER = "http://121.42.138.126:82/cg/order.aspx";
	public static final String APIHOST_ORDERGOODS = "http://121.42.138.126:82/cg/ordergoods.aspx";
	public static final String APIHOST_GOODS = "http://121.42.138.126:82/cg/goods.aspx";
	public static final String APIHOST_NEWS = "http://121.42.138.126:82/cg/news.aspx";
	public static final String APIHOST_REPLY = "http://121.42.138.126:82/cg/reply.aspx";
	public static final String APIHOST_TYPE = "http://121.42.138.126:82/cg/type.aspx";
	public static final String APIHOST_STORE = "http://121.42.138.126:82/cg/store.aspx";
	public static final String APIHOST_COLLECTION = "http://121.42.138.126:82/cg/collect.aspx";
	public static final String PICHOST = "http://121.42.138.126:82";

	// 全局业务数据
	public final static String BUSI_USER = "user";
	public final static String BUSI_MYSTORE = "mystore";

	final String USERID = "userId";

	private static RequestQueue mQueue;

	public static RequestQueue getRequestQueue(Context context) {
		if (mQueue == null) {
			Volley.newRequestQueue(context);
		}
		return mQueue;
	}
}
