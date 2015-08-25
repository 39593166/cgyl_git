package com.utoo.chunguanyouli.ui.village;

/**
 * 本村之声
 */
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.ui.activity.adapter.VillageDropsAdapter;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

@ContentView(R.layout.activity_ben_zhen_zhi_sheng_activity)
public class NewsForVillageActivity extends NetDataBaseActionBarActivity
		implements OnRefreshListener2<ListView> {

	private int p = 1;
	private int tid = 1;
	private int cid;

	private ListView listView;
	private VillageDropsAdapter adapter;
	private List<CgNewsId> list = new ArrayList<CgNewsId>();

	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	@ViewInject(R.id.group)
	private RadioGroup group;

	@ViewInject(R.id.pullToRefreshListView)
	private PullToRefreshListView pullToRefreshListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		cid = getIntent().getIntExtra("cid", 0);
		initToolbar("本村之声");

		pullToRefreshListView.setOnRefreshListener(this);
		pullToRefreshListView.setMode(Mode.BOTH);
		listView = pullToRefreshListView.getRefreshableView();

		adapter = new VillageDropsAdapter(this, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				CgNewsId info = (CgNewsId) adapter.getItem(position - 1);

				Intent intent = new Intent();
				intent.setClass(NewsForVillageActivity.this,
						VillageDropsDetailActivity.class);
				intent.putExtra("news", info);
				startActivity(intent);
			}
		});

		apply(API_C.getVillageNews(0, cid, tid, p++));

		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radiobutton1:

					tid = 1;
					break;

				case R.id.radiobutton2:
					tid = 2;
					break;

				case R.id.radiobutton3:
					tid = 3;
					break;
				case R.id.radiobutton4:
					tid = 4;
					break;
				default:
					break;
				}
				p = 1;
				apply(API_C.getVillageNews(1, cid, tid, p++));
			}
		});
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		p = 1;
		apply(API_C.getVillageNews(1, cid, tid, p++));
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		apply(API_C.getVillageNews(0, cid, tid, p++));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {

	}

	@Override
	protected void onReturned(String response, int TAG) {
		pullToRefreshListView.onRefreshComplete();
		List<CgNewsId> typesList = null;
		try {
			JSONObject json = new JSONObject(response);
			int state = json.getInt("state");
			if (state == 0) {
				return;
			}

			JSONArray data = json.optJSONArray("val");
			Gson gson = new Gson();
			typesList = gson.fromJson(data.toString(),
					new TypeToken<List<CgNewsId>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (TAG == 0) {
			this.list.addAll(typesList);
			adapter.notifyDataSetChanged();
		} else if (TAG == 1) {

			this.list.clear();
			this.list.addAll(typesList);
			adapter.notifyDataSetChanged();
		}
	}
}
