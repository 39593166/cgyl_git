package com.utoo.chunguanyouli.ui.account;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

public class AccountHelper {
	public static boolean checkInputLength(String userName, int minLen,
			int maxLen) {
		return (userName.length() >= minLen && userName.length() <= maxLen);
	}

	public static boolean checkRegist(Activity ac, EditText userName,
			EditText password1, EditText password2, int minLenUserName,
			int maxLenUserName, int minLenPassword, int maxLenPassword) {
		String userNameStr = userName.getText().toString().trim();
		String passwordStr1 = password1.getText().toString().trim();
		String passwordStr2 = password2.getText().toString().trim();
		if (userNameStr.length() > maxLenUserName) {
			Toast.makeText(ac, "用户名不能超过" + maxLenUserName + "个字符", 0).show();
			return false;
		} else if (userNameStr.length() < minLenUserName) {
			Toast.makeText(ac, "用户名不能少于" + minLenUserName + "个字符", 0).show();
			return false;
		} else if (!passwordStr1.equals(passwordStr2)) {
			Toast.makeText(ac, "两次输入密码不一致", 0).show();
			return false;
		} else if (passwordStr1.length() > maxLenPassword) {
			Toast.makeText(ac, "密码不能超过" + maxLenPassword + "个字符", 0).show();
			return false;
		} else if (passwordStr1.length() > maxLenPassword) {
			Toast.makeText(ac, "密码不能少于" + minLenPassword + "个字符", 0).show();
			return false;
		}
		return true;
	}
}
