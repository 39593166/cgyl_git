package com.utoo.chunguanyouli.ui.base;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.utoo.chunguanyouli.MyApplication;
import com.utoo.chunguanyouli.MyApplication.LoginOutOfDateException;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.ui.account.LoginActivity;

/**
 * Activity基类，所有Activity继承于此，Activity初始化工作统一写在这里
 * 
 * @author fsm
 * 
 */
public class BaseActionBarActivity extends AppCompatActivity {
	protected Toolbar toolbar;
	public MyApplication app;// 本应用的Application实例，用于管理全局变量
	public boolean isNeedLogin = false;
	public UUserInfoId userNeed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (MyApplication) getApplication();
		if (isNeedLogin) {
			userNeed = getUser1();
			if (userNeed == null) {
				goLogin();
				this.finish();
			}
		}
	}

	public void goLogin() {
		Intent intent = new Intent();
		intent.setClass(this, LoginActivity.class);
		startActivity(intent);
	}

	public void setNeedLogin(boolean isNeedLogin) {
		this.isNeedLogin = isNeedLogin;
	}

	public UUserInfoId getUser1() {
		// try {
		try {
			return app.getLoginUser();
		} catch (LoginOutOfDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// } catch (LoginOutOfDateException e) {
		// Toast.makeText(this, e.getMessage(), 0).show();
		// Intent intent = new Intent();
		// intent.setClass(this, LoginActivity.class);
		// startActivity(intent);
		// e.printStackTrace();
		// }
		// return null;
		return null;
	}

	/**
	 * 设置toolbar，需在所在activity里面添加Toolbar，并设置id为toolBar
	 * 
	 * @param title
	 *            toolbar显示的title
	 */
	public void initToolbar(String title) {
		toolbar = (Toolbar) findViewById(R.id.toolBar);
		toolbar.setTitle(title);
		toolbar.setTitleTextColor(getResources()
				.getColor(android.R.color.white));
		Log.e("", toolbar.toString());
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	// 对话框
	private Builder builder;

	public void showDialog(Context context, String title, String message,
			DialogInterface.OnClickListener onEnsureClick) {
		builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("确定", onEnsureClick);
		builder.setNegativeButton("取消", null);
		builder.show();
	}

	public void showDialog(Context context, String title, String message,
			DialogInterface.OnClickListener onEnsureClick,
			DialogInterface.OnClickListener onQuitClick) {
		builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("确定", onEnsureClick);
		builder.setNegativeButton("取消", onQuitClick);
		builder.show();
	}

	// 提示进度框
	public Dialog dialog = null;

	public void showProgressDialog(String content) {
		if (!this.isFinishing()) {
			LayoutInflater inflater = LayoutInflater.from(this);
			LinearLayout layout = (LinearLayout) inflater.inflate(
					R.layout.progress_bar, null);
			TextView tip = (TextView) layout.findViewById(R.id.progress_tip);
			tip.setText(content);
			// 2. 新建对话框对象
			// 判断是否存在
			if (dialog == null) {
				dialog = new AlertDialog.Builder(this).create();
				dialog.setCanceledOnTouchOutside(false);
				dialog.setCancelable(true);
			}
			if (!dialog.isShowing()) {
				dialog.show();
				dialog.getWindow().setContentView(layout);
			}
		}

	}

	/**
	 * 关闭进度对话框
	 */
	public void closeProgressDialog() {
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}
}
