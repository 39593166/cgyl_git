package com.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.R;

/**
 * 自定义倒计时文本控件
 * 
 * @author Administrator
 * 
 */
public class TimeTextView extends TextView implements Runnable {
	Paint mPaint; // 画笔,包含了画几何图形、文本等的样式和颜色信息
	private long[] times;
	private long mday, mhour, mmin, msecond;// 天，小时，分钟，秒
	private boolean run = false; // 是否启动了

	public TimeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.TimeTextView);
		array.recycle(); // 一定要调用，否则这次的设定会对下次的使用造成影响
	}

	public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mPaint = new Paint();
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.TimeTextView);
		array.recycle(); // 一定要调用，否则这次的设定会对下次的使用造成影响
	}

	public TimeTextView(Context context) {
		super(context);
	}

	public long[] getTimes() {
		return times;
	}

	// public void setTimes(long[] times) {
	// this.times = times;
	// mday = times[0];
	// mhour = times[1];
	// mmin = times[2];
	// msecond = times[3];
	// }

	/**
	 * 倒计时计算
	 */
	private void ComputeTime() {
		msecond--;
		if (msecond < 0) {
			mmin--;
			msecond = 59;
			if (mmin < 0) {
				mmin = 59;
				mhour--;
				if (mhour < 0) {
					// 倒计时结束
					mhour = 0;
					// mday--;
				}
			}
		}
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	@Override
	public void run() {
		// 标示已经启动
		run = true;
		ComputeTime();
		String strTime = mhour
				+ "</span><pre>小时</pre><span style=\"color:red;\">" + mmin
				+ "</span><pre>分钟</pre><span style=\"color: red;\">" + msecond
				+ "</span><pre>秒";
		// mday + "</span><pre>"
		// + "天</pre><span style=\"color:red;\">" +
		this.setText(Html.fromHtml(strTime));
		postDelayed(this, 1000);
	}

	public long setEndTime(String endTime,String dfStr) {

		DateFormat df = new SimpleDateFormat(dfStr);
//		SimpleDateFormat sDateFormat1 = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		String date1 = sDateFormat.format(new Date());
//		System.out.println("现在时间：" + date);
		long diff = 0;
		try {
			Date d1 = new Date();
			Date d2 = df.parse(endTime);
			diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
			System.out.println("现在时间：diff " + diff);
			mday = diff / (1000 * 60 * 60 * 24);
			mhour = (diff - mday * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			mmin = (diff - mday * (1000 * 60 * 60 * 24) - mhour
					* (1000 * 60 * 60))
					/ (1000 * 60);
			msecond = (diff - mday * (1000 * 60 * 60 * 24) - mhour
					* (1000 * 60 * 60) - mmin * (1000 * 60)) / (1000);
			// txtView.setText(""+days+"天"+hours+"小时"+minutes+"分"+seconds+"秒");

			System.out.println(mhour + "小时" + mmin + "分" + msecond + "秒");
		} catch (Exception e) {
		}
		return diff;
	}

	public void setEndTime(long endTime) {
		try {
			long diff = endTime - System.currentTimeMillis() / 1000;
			mhour = diff / (60 * 60);
			mmin = (diff - mhour * (60 * 60)) / 60;
			msecond = (diff - mhour * (60 * 60) - mmin * 60);

		} catch (Exception e) {
		}
	}
}
