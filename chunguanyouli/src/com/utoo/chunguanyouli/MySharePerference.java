package com.utoo.chunguanyouli;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MySharePerference {
	private final String USERNAME = "userName";
	private final String PASSWORD = "password";
	private final String LOGINTIME = "loginTime";
	private SharedPreferences preferences;
	Context context;

	public MySharePerference(Context context) {
		this.context = context;
		preferences = context.getSharedPreferences("kanggou",
				Context.MODE_PRIVATE);
	}

	/**
	 * 保存用户名和密码
	 * 
	 * @param userName
	 * @param Password
	 */
	public void saveUserNameAndPassword(String userName, String Password) {
		Date date = new Date();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = fmt.format(date);
		Editor editor = preferences.edit();
		editor.putString(USERNAME, userName);
		editor.putString(PASSWORD, Password);
		editor.putString(LOGINTIME, dateStr);
		editor.commit();
	}

	public boolean IsFirstInMain() {
		return preferences.getBoolean("F_Main", true);
	}

	public boolean IsFirstInCatg() {
		return preferences.getBoolean("F_Catg", true);
	}

	public boolean IsFirstInVill() {
		return preferences.getBoolean("F_Vill", true);
	}

	public void setFirstInMainFalse() {
		Editor editor = preferences.edit();
		editor.putBoolean("F_Main", false);
		editor.commit();
	}

	public void setFirstInCatgFalse() {
		Editor editor = preferences.edit();
		editor.putBoolean("F_Catg", false);
		editor.commit();
	}

	public void setFirstInVillFalse() {
		Editor editor = preferences.edit();
		editor.putBoolean("F_Vill", false);
		editor.commit();
	}

	public String getUserName() {
		return preferences.getString(USERNAME, "");
	}

	public String getPassword() {
		return preferences.getString(PASSWORD, "");
	}
}
