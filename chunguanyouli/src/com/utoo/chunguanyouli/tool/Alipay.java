package com.utoo.chunguanyouli.tool;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;

public class Alipay {
	/**
	 * 調用這個方法来支付
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
