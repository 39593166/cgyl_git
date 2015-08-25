package com.utoo.chunguanyouli.ui.personal.cg.collage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgCityId;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

public class ModifyCityContentActivity extends NetDataBaseActionBarActivity
		implements OnClickListener {
	private static final int TAG_SUBMIT = 0;
	CgCityId city;
	UUserInfoId user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_city_content);
		city = (CgCityId) getIntent().getSerializableExtra("city");
		findViews();
		initToolbar("修改村信息");
		modifyCityContentEdit.setText(city.getContent());
		modifyCityNameEdit.setText(city.getName());
		user = getUser1();
		if (user == null) {
			goLogin();
		}
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		Toast.makeText(this, "连接超时，请重试", 0).show();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		// try {
		// JSONObject jo = new JSONObject(response);
		// int state = jo.optInt(response);
		// if (state == 1) {
		Toast.makeText(this, "修改成功", 0).show();
		Intent aintent = new Intent();
		aintent.setClass(this, VillageManagerActivity.class);
		aintent.putExtra("content", city.getContent());
		aintent.putExtra("name", city.getName());
		setResult(RESULT_OK, aintent); // 这理有2个参数(int resultCode, Intent
										// intent)
		finish();
		// }
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
	}

	private EditText modifyCityContentEdit;
	private Button modifyCityContentSubmit;
	private EditText modifyCityNameEdit;

	private void findViews() {
		modifyCityContentEdit = (EditText) findViewById(R.id.modifyCityContentEdit);
		modifyCityContentSubmit = (Button) findViewById(R.id.modifyCityContentSubmit);
		modifyCityNameEdit = (EditText) findViewById(R.id.modifyCityNameEdit);
		modifyCityContentSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == modifyCityContentSubmit) {
			city.setContent(modifyCityContentEdit.getText().toString());
			city.setName(modifyCityNameEdit.getText().toString());
			apply(API_F.updateCity(TAG_SUBMIT, user.getId(), user.getSid(),
					city));
		}
	}

}
