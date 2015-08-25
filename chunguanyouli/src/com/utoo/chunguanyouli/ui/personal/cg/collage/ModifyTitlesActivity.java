package com.utoo.chunguanyouli.ui.personal.cg.collage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgCityId;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.ClientConfigs;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.comm.CityTitleAdapter;

public class ModifyTitlesActivity extends NetDataBaseActionBarActivity
		implements OnClickListener, OnItemLongClickListener {
	private static final int TAG_SUBMIT = 0;
	CityTitleAdapter adapter;
	CgCityId city;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_titles);
		initToolbar("修改村印象");
		findViews();
		city = (CgCityId) getIntent().getSerializableExtra("city");
		adapter.add(city.getEffect());
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
		aintent.putExtra("titles", city.getEffect());
		setResult(RESULT_OK, aintent); // 这理有2个参数(int resultCode, Intent
										// intent)
		finish();
		// }
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
	}

	private EditText updateCityTitleEdit;
	private Button updateCityTitleAdd;
	private GridView updateCityTitleList;
	private Button updateCityTitleSubmit;

	private void findViews() {
		updateCityTitleEdit = (EditText) findViewById(R.id.updateCityTitleEdit);
		updateCityTitleAdd = (Button) findViewById(R.id.updateCityTitleAdd);
		updateCityTitleList = (GridView) findViewById(R.id.updateCityTitleList);
		updateCityTitleSubmit = (Button) findViewById(R.id.updateCityTitleSubmit);

		adapter = new CityTitleAdapter(this);
		updateCityTitleList.setAdapter(adapter);

		updateCityTitleList.setOnItemLongClickListener(this);
		updateCityTitleAdd.setOnClickListener(this);
		updateCityTitleSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == updateCityTitleAdd) {
			// Handle clicks for updateCityTitleAdd
			String title = updateCityTitleEdit.getText().toString();
			adapter.add(title);
			adapter.notifyDataSetChanged();
		} else if (v == updateCityTitleSubmit) {
			// Handle clicks for updateCityTitleSubmit
			String effect = adapter.getStr();
			city.setEffect(effect);
			UUserInfoId user = getUser1();
			if (user != null)
				apply(API_F.updateCity(TAG_SUBMIT, user.getId(), user.getSid(),
						city));
		}
	}

	public AlertDialog.Builder builder;

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, long id) {
		builder = new AlertDialog.Builder(this);
		builder.setTitle("删除村印象标签");
		builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				adapter.remove(position);
				adapter.notifyDataSetChanged();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				builder = null;
			}
		});
		builder.show();
		return false;

	}

}
