package com.alipay.sdk.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;

public class MyPay {
	/**
	 * 調用這個方法来支付
	 * 
	 * @param context
	 * @param handler
	 * @param msgWhat
	 * @param aliInfo
	 */
	public static void pay(Activity context, Handler handler, int msgWhat,
			String aliInfo) {
		MyPayThread payThread = new MyPayThread(aliInfo, context, handler,
				msgWhat);
		payThread.start();
	}

	public static void pay(Activity context, Handler handler, int msgWhat,
			String orderInfo, String sign) {
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ getSignType();
		MyPayThread payThread = new MyPayThread(payInfo, context, handler,
				msgWhat);
		payThread.start();
	}
	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public static String getSignType() {
		return "sign_type=\"RSA\"";
	}
	static class MyPayThread extends Thread {
		String alipayInfo;
		Activity context;
		Handler handler;
		int msgWhat;

		public MyPayThread(String alipayInfo, Activity context,
				Handler handler, int msgWhat) {
			this.alipayInfo = alipayInfo;
			this.context = context;
			this.handler = handler;
			this.msgWhat = msgWhat;
		};

		public void run() {
			PayTask alipay = new PayTask(context);
			String result = alipay.pay(alipayInfo);

			Message msg = new Message();
			msg.what = msgWhat;
			msg.obj = result;
			handler.sendMessage(msg);

		}
	};
}
