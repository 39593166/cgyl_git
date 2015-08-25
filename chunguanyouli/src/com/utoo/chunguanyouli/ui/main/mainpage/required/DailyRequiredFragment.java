package com.utoo.chunguanyouli.ui.main.mainpage.required;

/**
 * 点滴生活
 */
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;
import com.utoo.chunguanyouli.ui.main.mainpage.MainActivity;
import com.utoo.chunguanyouli.ui.village.VillageDropsDetailActivity;
import com.views.NoScrollListView;

public class DailyRequiredFragment extends NetDataBaseFragment implements
		OnRefreshListener2<ListView>, OnItemClickListener {

	private MainActivity act;
	private CommonAdapter<CgNewsId> adapter;
	private int p = 1;

	// ListView
	@ViewInject(R.id.listView_dail)
	private NoScrollListView listView;

	// Item点击事件
	// @OnItemClick(R.id.pullToRefreshListView)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		CgNewsId info = (CgNewsId) adapter.getItem(position);
		Intent intent = new Intent();
		intent.setClass(act, VillageDropsDetailActivity.class);
		intent.putExtra("news", info);
		startActivity(intent);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		act = (MainActivity) getActivity();
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_dailyrequired,
				container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		// listView.setMode(Mode.BOTH);
		// listView.setOnRefreshListener(this);

		// 初始化
		adapter = new CommonAdapter<CgNewsId>(getActivity(),
				R.layout.adapter_dailyrequired) {
			@Override
			public void convert(ViewHolderHelper helper, CgNewsId item) {
				helper.setText(R.id.title, item.getTitle());
				helper.setText(R.id.time, item.getDatesend());
				helper.setImageByUrl(R.id.icon, item.getPic(),
						R.drawable.index_news_list_big,
						R.drawable.index_news_list_big);
			}
		};
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		// 请求网络
		apply(API_C.getDailyRequired(0, p));
	}

	/**
	 * call from outsid
	 */
	public void refleshDailyRequired() {
		apply(API_C.getDailyRequired(0, 1));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		act.scrollView.onRefreshComplete();
		// TODO Auto-generated method stub
		// listView.onRefreshComplete();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		act.scrollView.onRefreshComplete();
		// TODO Auto-generated method stub
		// listView.onRefreshComplete();
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

		if (TAG == 1) {
			// VillageDropsInfo info = gson.fromJson(response,
			// VillageDropsInfo.class);
			// if (info.getState().equals("1")) {
			// List<Val> list = info.getVal();

			// this.list.clear();
			p++;
			adapter.addData(typesList);
			// }
		} else if (TAG == 0) {
			p = 2;
			adapter.resetData(typesList);
		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		apply(API_C.getDailyRequired(0, 1));
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		apply(API_C.getDailyRequired(1, p));
	}

}
