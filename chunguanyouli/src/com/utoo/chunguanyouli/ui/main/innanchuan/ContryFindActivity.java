package com.utoo.chunguanyouli.ui.main.innanchuan;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.entity.Contry;
import com.utoo.chunguanyouli.entity.Town;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

/**
 * 走进南川-》找村子
 * 
 * @author fsm
 * 
 */
public class ContryFindActivity extends NetDataBaseActionBarActivity implements
		OnItemClickListener, OnRefreshListener2<GridView> {
	ListView toplist;
	PullToRefreshGridView childlist;
	TownListAdapter townAdapter;
	CommonAdapter<Contry> contryAdatper;
	Town selectTown;
	int choosedOldTownPos = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contry_find);
		toplist = (ListView) this.findViewById(R.id.town_list);
		childlist = (PullToRefreshGridView) this.findViewById(R.id.contry_list);
		toplist.setOnItemClickListener(this);
		childlist.setOnItemClickListener(this);
		init();
	}

	private void init() {
		childlist.setMode(Mode.PULL_FROM_START);
		childlist.setOnRefreshListener(this);
		contryAdatper = new CommonAdapter<Contry>(this,
				R.layout.item_contry_classify) {

			@Override
			public void convert(ViewHolderHelper helper, Contry item) {
				helper.setText(R.id.item_contry_classify_name, item.getName())
						.setImageByUrl(R.id.item_contry_classify_img,
								item.getImage(), R.drawable.index,
								R.drawable.index);
			}
		};
	}

	private void getTowns() {

	}

	private void getContries(Town town) {

	}

	private void setTowns(List<Town> towns) {
		townAdapter = new TownListAdapter(this, towns);
		toplist.setAdapter(townAdapter);
	}

	private void setContrys(List<Contry> contry) {
		this.contryAdatper.resetData(contry);
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		JSONObject responseJson = null;
		try {
			responseJson = new JSONObject(response);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if (arg0 == toplist) {
			if (arg2 != choosedOldTownPos) {
				townAdapter.selecter.set(arg2, true);
				this.selectTown = (Town) townAdapter.getItem(arg2);
				getContries(selectTown);
				this.choosedOldTownPos = arg2;
			}
		} else {
			Intent intent = new Intent();
			intent.setClass(this, GoInNanchuanActivity.class);
			intent.putExtra("contry", contryAdatper.getItem(arg2));
			startActivity(intent);
		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
		// TODO Auto-generated method stub
		getContries(selectTown);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
		// TODO Auto-generated method stub

	}
}
