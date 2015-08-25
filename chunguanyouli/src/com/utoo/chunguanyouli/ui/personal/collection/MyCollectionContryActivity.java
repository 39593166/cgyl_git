package com.utoo.chunguanyouli.ui.personal.collection;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgCityId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.SelectorAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.village.VillagesIntroduceActivity;

/**
 * 我收藏的村子
 * 
 * @author fsm
 * 
 */
public class MyCollectionContryActivity extends NetDataBaseActionBarActivity
		implements OnItemClickListener {
	private static final int TAG_GETCOLLECTION = 0;
	private static final int TAG_DELETE = 1;
	ListView contryListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_collection_contry);
		init();
		initToolbar("村子收藏");
		getMyCollection();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.my_collection_goods, menu);
		return super.onCreateOptionsMenu(menu);
	}

	private void getMyCollection() {
		apply(API_F.getCollectionCity(userNeed.getId(), userNeed.getSid(), 1,
				TAG_GETCOLLECTION));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_mycollect_goods_delete:
			deleteCollection();
			break;
		default:
			break;
		}
		return true;
	}

	int sum;

	private void deleteCollection() {
		sum = contryAdapter.getSelectedDatas().size();
		for (CgCityId item : contryAdapter.getSelectedDatas()) {
			apply(API_F.deleteCollect(item.getId(), userNeed.getId(),
					userNeed.getSid(), 1, TAG_DELETE));
		}
	}

	SelectorAdapter<CgCityId> contryAdapter;

	private void init() {
		contryListView = (ListView) findViewById(R.id.myCollectionContryList);
		contryAdapter = new SelectorAdapter<CgCityId>(this,
				R.layout.item_my_collection_contry) {

			@Override
			public void convert(ViewHolderHelper helper, CgCityId item,
					final int position) {
				helper.setText(R.id.collect_city_name, item.getName())
						.setText(R.id.collect_city_des, item.getContent())
						.setText(R.id.collect_city_title,
								item.getEffect().replace(":", ","))
						.setText(R.id.collect_city_fmlNum, "" + item.getNumhu())
						.setText(R.id.collect_city_pslNum,
								item.getNumpro() + "")
						.setText(R.id.collect_city_ofcNum,
								item.getNumorder() + "")
						.setText(R.id.collect_city_prodctNum,
								item.getNumgoods() + "")
						.setImageByUrl(R.id.collect_city_img, item.getPic(),
								R.drawable.index, R.drawable.index);
				helper.setChecBoxChangeLis(R.id.collect_isSelect_contry,
						contryAdapter.isSelect(position),
						new OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								contryAdapter.change(position, isChecked);
							}
						});
			}
		};
		contryListView.setAdapter(contryAdapter);
		contryListView.setOnItemClickListener(this);
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {

	}

	@Override
	protected void onReturned(String response, int TAG) {
		if (TAG == TAG_GETCOLLECTION) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			Gson gson = new Gson();
			List<CgCityId> cityList = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgCityId>>() {
					}.getType());
			setGoods(TAG, cityList);
		} else if (TAG == TAG_DELETE) {
			JSONObject jo = null;
			try {
				jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 1) {
					Toast.makeText(this, "删除成功", 0).show();
					removeSoft();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	private void removeSoft() {
		// TODO Auto-generated method stub
		List<CgCityId> deletes = new ArrayList<CgCityId>();
		for (int i = 0; i < contryAdapter.getCount(); i++) {
			if (contryAdapter.selects.get(i)) {
				deletes.add(contryAdapter.getItem(i));
			}
		}

		contryAdapter.deleteSelecters();
	}

	private void setGoods(int tAG, List<CgCityId> cityList) {
		contryAdapter.resetData(cityList);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.setClass(this, VillagesIntroduceActivity.class);
		intent.putExtra("id_a", contryAdapter.getItem(position).getId());
		this.startActivity(intent);
	}
}
