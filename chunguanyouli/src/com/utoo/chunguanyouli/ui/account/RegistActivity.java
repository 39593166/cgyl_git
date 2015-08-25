package com.utoo.chunguanyouli.ui.account;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

public class RegistActivity extends NetDataBaseActionBarActivity implements
		OnClickListener {

	private static final int TAG_REGIST = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		findViews();
		initToolbar("新用户注册");
	}

	private void regist() {
		String userName = registUserName.getText().toString().trim();
		String password1 = registPassword1.getText().toString().trim();
		// post(TAG_REGIST, clientContext.APIHOST_USER,
		// clientContext.getRegistParams(userName, password1));
		apply(API_F.Regist(userName, password1, TAG_REGIST));
	}

	private EditText registUserName;
	private EditText registPassword1;
	private EditText registPassword2;
	private Button registSubmit;

	private void findViews() {
		registUserName = (EditText) findViewById(R.id.regist_userName);
		registPassword1 = (EditText) findViewById(R.id.regist_password_1);
		registPassword2 = (EditText) findViewById(R.id.regist_password_2);
		registSubmit = (Button) findViewById(R.id.regist_submit);

		registSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == registSubmit) {
			if (AccountHelper.checkRegist(this, registUserName,
					registPassword1, registPassword2, 6, 16, 6, 16)) {
				regist();
			}
		}
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		Toast.makeText(this, error.getMessage(), 0).show();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		if (TAG == TAG_REGIST) {
			// {"state":"0","sid":"","id":"0"}
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 1) {
					Toast.makeText(this, "注册成功", 0).show();
					finish();
				}else {
					Toast.makeText(this, "该账号已被注册，请重新输入账号", 0).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
