package com.utoo.chunguanyouli.ui.account;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.utoo.chunguanyouli.MyApplication;
import com.utoo.chunguanyouli.MySharePerference;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.main.mainpage.MainActivity;
import com.utoo.gson.GsonHelper;

public class LoginActivity extends NetDataBaseActionBarActivity implements
		OnClickListener {
	MySharePerference msp;
	MyApplication app;
	private static final int TAG_LOGIN = 0;
	int tag = 0;
	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ViewUtils.inject(this);
		this.tag = getIntent().getIntExtra("tag", 1);
		msp = new MySharePerference(this);
		app = (MyApplication) getApplication();
		findViews();

		toolbar.setTitle("登录");
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		toolbar.setTitleTextColor(getResources()
				.getColor(android.R.color.white));
	}

	private EditText loginUserName;
	private EditText loginPassword;
	private Button loginsubmit;

	private void login(String userName, String password) {
		if (!AccountHelper.checkInputLength(userName, 6, 16)) {
			Toast.makeText(this, "请输入正确的用户名", 0).show();
		} else if (!AccountHelper.checkInputLength(password, 6, 16)) {
			Toast.makeText(this, "密码不正确", 0).show();
		} else {
			msp.saveUserNameAndPassword(userName, password);
			loginsubmit.setEnabled(false);
			apply(API_F.getLoginRequest(userName, password, TAG_LOGIN));
		}

	}

	public void forgetPd(View v) {
		Intent intent = new Intent();
		intent.setClass(this, ResetPasswordActivity.class);
		startActivity(intent);
	}

	public void goRegist(View v) {
		Intent intent = new Intent();
		intent.setClass(this, RegistActivity.class);
		startActivity(intent);
	}

	private void goMain() {
		if (tag == 0) {
			Intent intent = new Intent();
			intent.setClass(this, MainActivity.class);
			startActivity(intent);
			this.finish();
		} else if (tag == 1) {
			this.finish();
		}
	}

	private void findViews() {
		loginUserName = (EditText) findViewById(R.id.login_userName);
		loginPassword = (EditText) findViewById(R.id.login_password);
		loginsubmit = (Button) findViewById(R.id.loginsubmit);
		loginUserName.setText(msp.getUserName());
		loginPassword.setText(msp.getPassword());
		loginsubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == loginsubmit) {

			String userNameStr = loginUserName.getText().toString().trim();
			String passwordStr = loginPassword.getText().toString().trim();
			login(userNameStr, passwordStr);
		}
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		Toast.makeText(this, "网络连接失败", 0).show();
		loginsubmit.setEnabled(true);
	}

	@Override
	protected void onReturned(String response, int TAG) {
		Log.e("登陆数据", response);
		loginsubmit.setEnabled(true);
		if (TAG == TAG_LOGIN) {
			try {
				JSONObject json = new JSONObject(response);
				String sid = json.getString("sid");
				int sum = json.getInt("num");
				if (sum == 0) {
					Toast.makeText(this, "登陆验证失败", 0).show();
					return;
				}

				JSONArray data = json.optJSONArray("val");

				JSONObject userjson = data.optJSONObject(0);
				UUserInfoId user = GsonHelper.getBean(userjson.toString(),
						UUserInfoId.class);
				user.setSid(sid);
				app.user = user;
				goMain();
				// System.out.println(((UUserInfoId) (MyApplication
				// .getBusinessData(ClientContext.BUSI_USER)))
				// .getUsername());
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
