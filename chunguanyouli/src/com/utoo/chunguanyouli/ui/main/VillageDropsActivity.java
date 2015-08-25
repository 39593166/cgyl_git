package com.utoo.chunguanyouli.ui.main;

/**
 * 村官点滴
 */
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.village.VillageDropsDetailActivity;

@ContentView(R.layout.activity_villagedrops)
public class VillageDropsActivity extends NetDataBaseActionBarActivity
		implements OnRefreshListener2<ListView> {

	private static final int TAG_MORE = 2;
	private static final int TAG_REFLESH = 1;
	private int p = 1;
	private Intent intent = new Intent();
	private CommonAdapter<CgNewsId> adapter1;
	private ListView listView;

	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;
	
	@ViewInject(R.id.articleListView)
	private PullToRefreshListView pullToRefreshListView;

	@ViewInject(R.id.group)
	private RadioGroup group;

	@OnItemClick(R.id.articleListView)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		CgNewsId news = adapter1.getItem(position-1);
		intent.setClass(this, VillageDropsDetailActivity.class);
		intent.putExtra("news", news);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initToolbar("公告活动");
		init();
		applyData(TAG_MORE);

	}

	private void init() {
		group.setVisibility(View.GONE);
		listView = pullToRefreshListView.getRefreshableView();
		pullToRefreshListView.setMode(Mode.BOTH);
		pullToRefreshListView.setOnRefreshListener(this);
		adapter1 = new CommonAdapter<CgNewsId>(this,
				R.layout.adapter_villagedrops) {

			@Override
			public void convert(ViewHolderHelper helper, CgNewsId item) {
				helper.setText(R.id.title, item.getTitle());
				helper.setText(R.id.content, item.getContent());
				helper.setImageByUrl(R.id.icon, item.getPic(),
						R.drawable.index_news_list_small,
						R.drawable.index_news_list_small);
			}
		};
		listView.setAdapter(adapter1);
	}

	private void applyData(int tag) {
		if (tag == TAG_MORE)
			apply(API_C.getVillageDrops(tag, 0, p));
		else if (tag == TAG_REFLESH) {
			apply(API_C.getVillageDrops(tag, 0, 1));
		}
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {

	}

	@Override
	protected void onReturned(String response, int TAG) {
		LogUtils.e("村官点滴" + response);
		pullToRefreshListView.onRefreshComplete();
		CgResponse cr = new CgResponse(response);
		List<CgNewsId> newsList = null;
		try {
			if (cr.getState() == 0) {
				return;
			}

			JSONArray data = new JSONArray(cr.getValStr());
			Gson gson = new Gson();
			newsList = gson.fromJson(data.toString(),
					new TypeToken<List<CgNewsId>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (TAG == TAG_REFLESH) {
			p = 2;
			adapter1.resetData(newsList);
		}

		if (TAG == TAG_MORE) {
			p++;
			adapter1.addData(newsList);
		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		applyData(TAG_REFLESH);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		applyData(TAG_MORE);
	}

}
