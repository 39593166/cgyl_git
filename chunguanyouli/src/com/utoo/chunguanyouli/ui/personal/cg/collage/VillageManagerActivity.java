package com.utoo.chunguanyouli.ui.personal.cg.collage;

/**
 * 修改村子
 */
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgCityId;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.comm.CityTitleAdapter;
import com.utoo.chunguanyouli.ui.main.VillageDropsActivity;
import com.utoo.chunguanyouli.ui.personal.cg.dianpu.MyShopActivity;

public class VillageManagerActivity extends NetDataBaseActionBarActivity
		implements OnClickListener {
	private static final int TAG_GETINFO = 0;
	CgCityId city;
	CityTitleAdapter adapter;
	UUserInfoId user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifyvillage);
		findViews();
		initToolbar("村子管理");
		user = getUser1();
		if (user != null) {
			getCityInfo();
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		getCityInfo();
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.modify_city_content, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.go_news) {
			if (city == null) {
				Toast.makeText(this, "请等待", 0).show();
				return true;
			}
			Intent intent = new Intent();
			intent.putExtra("cid", city.getId());
			intent.setClass(this, NewsManagerActivity.class);
			startActivity(intent);
			return true;
		} else if (id == R.id.go_sotre) {
			if (city == null) {
				Toast.makeText(this, "请等待", 0).show();
				return true;
			}
			Intent intent = new Intent();
			intent.putExtra("city", city);
			intent.setClass(this, MyShopActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void getCityInfo() {
		apply(API_C.getAboutVillagers(TAG_GETINFO, user.getCid()));
	}

	private void setCityInfo() {

		this.cityManagerName.setText(city.getName());
		this.cityManagerContent.setText(city.getContent());
		this.adapter.reset(city.getEffect());
		this.cityManagerDateSend.setText(city.getDatesend());
		this.cityManagerLeader.setText(user.getRealname());
		adapter.notifyDataSetChanged();
		Log.e("titls", adapter.getCount() + "");
		Log.e("viewChildSum", cityManagerTitles.getChildCount() + "");
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		Toast.makeText(this, "网络连接失败，请检查网络连接", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		CgResponse cr = new CgResponse(response);
		if (cr.getState() == 0) {
			return;
		}
		List<CgCityId> goodsList = null;
		Gson gson = new Gson();

		goodsList = gson.fromJson(cr.getValStr(),
				new TypeToken<List<CgCityId>>() {
				}.getType());
		if (goodsList != null && goodsList.size() > 0) {
			this.city = goodsList.get(0);
			setCityInfo();
		}
	}

	private TextView cityManagerName;
	private Button cityManagerStore;
	private Button cityManagerNews;
	private Button cityManagerUpdateContent;
	private TextView cityManagerContent;
	private Button cityManagerUpdateTitle;
	private GridView cityManagerTitles;
	private TextView cityManagerDateSend;
	private TextView cityManagerLeader;

	private void findViews() {
		cityManagerName = (TextView) findViewById(R.id.cityManagerName);
		cityManagerStore = (Button) findViewById(R.id.cityManagerStore);
		cityManagerNews = (Button) findViewById(R.id.cityManagerNews);
		cityManagerUpdateContent = (Button) findViewById(R.id.cityManagerUpdateContent);
		cityManagerContent = (TextView) findViewById(R.id.cityManagerContent);
		cityManagerUpdateTitle = (Button) findViewById(R.id.cityManagerUpdateTitle);
		cityManagerTitles = (GridView) findViewById(R.id.cityManagerTitles);
		cityManagerDateSend = (TextView) findViewById(R.id.cityManagerDateSend);
		cityManagerLeader = (TextView) findViewById(R.id.cityManagerLeader);
		cityManagerStore.setOnClickListener(this);
		cityManagerNews.setOnClickListener(this);
		cityManagerUpdateContent.setOnClickListener(this);
		cityManagerUpdateTitle.setOnClickListener(this);

		adapter = new CityTitleAdapter(this);
		cityManagerTitles.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		if (v == cityManagerStore) {
			if (city == null) {
				Toast.makeText(this, "请等待", 0).show();
				return;
			}
			Intent intent = new Intent();
			intent.putExtra("city", city);
			intent.setClass(this, MyShopActivity.class);
			startActivity(intent);
		} else if (v == cityManagerNews) {
			if (city == null) {
				Toast.makeText(this, "请等待", 0).show();
				return;
			}
			Intent intent = new Intent();
			intent.setClass(this, VillageDropsActivity.class);
			intent.putExtra("action", 1);
			startActivity(intent);
		} else if (v == cityManagerUpdateContent) {
			if (city == null) {
				Toast.makeText(this, "请等待", 0).show();
				return;
			}
			Intent intent = new Intent();
			intent.putExtra("city", city);
			intent.setClass(this, ModifyCityContentActivity.class);
			startActivityForResult(intent, 1001);
		} else if (v == cityManagerUpdateTitle) {
			if (city == null) {
				Toast.makeText(this, "请等待", 0).show();
				return;
			}
			Intent intent = new Intent();
			intent.putExtra("city", city);
			intent.setClass(this, ModifyTitlesActivity.class);
			startActivityForResult(intent, 1002);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case 1001:
				Bundle b1 = data.getExtras(); // data为B中回传的Intent
				String content = b1.getString("content");
				String cityName = b1.getString("name");
				this.cityManagerName.setText(cityName);
				this.cityManagerContent.setText(content);
				this.city.setName(cityName);
				this.city.setContent(content);
				break;
			case 1002:
				Bundle b2 = data.getExtras(); // data为B中回传的Intent
				String titles = b2.getString("titles");

				this.city.setEffect(titles);
				this.adapter.reset(titles);
				Log.e("resutl", titles);
				Log.e("resutlfromAdapter", adapter.getStr());
				break;
			default:
				break;
			}
		}
	}
}
