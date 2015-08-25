package com.utoo.chunguanyouli.ui.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
	/**
	 * 得到时间差 里面不用看，就是得到毫秒数
	 */
	static long hours;
	static long minutes;
	static long seconds;

	public static long getTime(String endTime) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = sDateFormat.format(new Date());
		System.out.println("现在时间：" + date);
		long diff = 0;
		try {
			Date d1 = new Date();
			Date d2 = df.parse(endTime);
			diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
			System.out.println("现在时间：diff " + diff);
			// days = diff / (1000 * 60 * 60);
			hours = diff / (1000 * 60 * 60);
			;
			minutes = (diff - hours * (1000 * 60 * 60)) / (1000 * 60);
			seconds = (diff - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
			// txtView.setText(""+days+"天"+hours+"小时"+minutes+"分"+seconds+"秒");

			System.out.println(hours + "小时" + minutes + "分" + seconds + "秒");
		} catch (Exception e) {
		}
		return diff;
	}

	public static String getTimeStr(long time, String dfStr) {
		DateFormat df = new SimpleDateFormat(dfStr);
		Date date = new Date(time);
		String dateStr = df.format(date);
		return dateStr;
	}
}
