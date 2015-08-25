package com.utoo.chunguanyouli.ui.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.utoo.chunguanyouli.R;

/**
 * 用于选取城市地址，返回为文本
 * 
 * @author Lenovo
 * 
 */
public class AddressChoose implements OnItemClickListener, OnClickListener {
	AppCompatActivity ac;
	ChoosedCallback callback;
	protected Toolbar toolbar;
	ListView city1GridView, city2GridView, city3GridView;
	LinearLayout chooseCityLayout;
	Button ensure, cancer, back;
	TextView choosingP, choosingC, choosingA;
	List<String> addPrivence, addCity, addArea;
	JSONArray all;
	JSONArray citysJa, areaJa;// selectJoPrivence, selectJoCity;
	String privence, city, area;
	int step = 1;
	TextView autoAddr;

	public interface ChoosedCallback {
		public void chooseDone(String address);
	}

	public AddressChoose(AppCompatActivity ac, ChoosedCallback callback) {
		this.ac = ac;
		this.callback = callback;
		initView();

	}

	private void initView() {
		initToolbar("设置收货地址");
		this.ensure = (Button) ac.findViewById(R.id.p19_add_sure);
		this.cancer = (Button) ac.findViewById(R.id.p19_add_cancer);
		this.back = (Button) ac.findViewById(R.id.p19_add_back);
		this.city1GridView = (ListView) ac
				.findViewById(R.id.p19_city_gridView1);
		this.city2GridView = (ListView) ac
				.findViewById(R.id.p19_city_gridView2);
		this.city3GridView = (ListView) ac
				.findViewById(R.id.p19_city_gridView3);
		this.choosingP = (TextView) ac.findViewById(R.id.p19_add_privence);
		this.choosingC = (TextView) ac.findViewById(R.id.p19_add_city);
		this.choosingA = (TextView) ac.findViewById(R.id.p19_add_area);
		this.autoAddr = (TextView) ac.findViewById(R.id.autoAddr);
		this.ensure.setOnClickListener(this);
		this.cancer.setOnClickListener(this);
		this.back.setOnClickListener(this);
		this.city1GridView.setOnItemClickListener(this);
		this.city2GridView.setOnItemClickListener(this);
		this.city3GridView.setOnItemClickListener(this);
		try {
			getAddress();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initToolbar(String title) {
		toolbar = (Toolbar) ac.findViewById(R.id.toolBar);
		toolbar.setTitle(title);
		toolbar.setTitleTextColor(ac.getResources().getColor(
				android.R.color.white));
		Log.e("", toolbar.toString());
		ac.setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (step == 1) {
				} else if (step == 3) {
					choosingA.setText("");
					step = 2;
					city1GridView.setVisibility(View.GONE);
					city2GridView.setVisibility(View.VISIBLE);
					city3GridView.setVisibility(View.GONE);
					set2(privence);
				} else if (step == 2) {
					choosingC.setText("");
					step = 1;
					city1GridView.setVisibility(View.VISIBLE);
					city2GridView.setVisibility(View.GONE);
					city3GridView.setVisibility(View.GONE);
					showString = addPrivence;
					adapter.notifyDataSetChanged();
				}
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		autoAddr.setVisibility(View.GONE);

		switch (parent.getId()) {
		case R.id.p19_city_gridView1:
			city1GridView.setVisibility(View.GONE);
			city2GridView.setVisibility(View.VISIBLE);
			city3GridView.setVisibility(View.GONE);
			this.privence = showString.get(position);
			set2(privence);
			this.choosingP.setText(privence);
			step = 2;
			break;
		case R.id.p19_city_gridView2:
			city1GridView.setVisibility(View.GONE);
			city2GridView.setVisibility(View.GONE);
			city3GridView.setVisibility(View.VISIBLE);
			this.city = showString.get(position);
			set3(city);
			this.choosingC.setText(city);
			step = 3;
			break;
		case R.id.p19_city_gridView3:
			this.area = showString.get(position);
			this.choosingA.setText(area);
			// popupWindow.dismiss();
			break;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.p19_add_sure:
			this.callback.chooseDone((privence == null
					|| privence.equals("null") ? "" : privence)
					+ (city == null || city.equals("null") ? "" : city)
					+ (area == null || area.equals("null") ? "" : area));
			break;
		case R.id.p19_add_cancer:
			ac.finish();
			break;
		case R.id.p19_add_back:
			if (step == 1) {
			} else if (step == 3) {
				this.choosingA.setText("");
				step = 2;
				city1GridView.setVisibility(View.GONE);
				city2GridView.setVisibility(View.VISIBLE);
				city3GridView.setVisibility(View.GONE);
				set2(privence);
			} else if (step == 2) {
				choosingC.setText("");
				step = 1;
				city1GridView.setVisibility(View.VISIBLE);
				city2GridView.setVisibility(View.GONE);
				city3GridView.setVisibility(View.GONE);
				showString = this.addPrivence;
				adapter.notifyDataSetChanged();
			}
			break;
		}
	}

	public List<String> showString;
	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = ac.getLayoutInflater().inflate(
						R.layout.activity_address_text_item, null);

			}
			TextView t = (TextView) convertView
					.findViewById(R.id.p19_add_text_item);
			t.setText(showString.get(position));
			return convertView;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public Object getItem(int position) {
			return showString.get(position);
		}

		@Override
		public int getCount() {
			return showString.size();
		}

	};

	/**
	 * 加载省
	 * 
	 * @throws IOException
	 * @throws JSONException
	 */
	private void getAddress() throws IOException, JSONException {
		AssetManager am = ac.getAssets();
		InputStream is = am.open("address");
		byte[] buff = new byte[1024];
		int hasRead = 0;
		StringBuilder sb = new StringBuilder("");
		while ((hasRead = is.read(buff)) >= 0) {
			sb.append(new String(buff, 0, hasRead));
			// Log.e("地址文件",new String(buff, 0, hasRead));
		}
		is.close();

		JSONArray ja = new JSONArray(sb.toString());
		this.all = ja;
		// 获取一级目录
		List<String> strings = new ArrayList<String>();
		for (int i = 0; i < all.length(); i++) {
			JSONObject jo = all.optJSONObject(i);
			String name = jo.optString("name");
			strings.add(name);
		}
		this.addPrivence = strings;
		showString = this.addPrivence;
		this.city1GridView.setAdapter(adapter);
		this.adapter.notifyDataSetChanged();
	}

	/**
	 * 点击省 获取二级目录(city) 缓存所有城市的JsonArray目录
	 */
	private void set2(String privence) {
		List<String> strings = new ArrayList<String>();

		for (int i = 0; i < all.length(); i++) {

			JSONObject jo = all.optJSONObject(i);

			String name = jo.optString("name");

			if (name.equals(privence)) {

				this.citysJa = jo.optJSONArray("city");

			}
		}
		for (int i = 0; i < citysJa.length(); i++) {
			JSONObject jo = citysJa.optJSONObject(i);
			String name = jo.optString("name");
			strings.add(name);
		}
		this.addCity = strings;
		showString = this.addCity;
		this.city2GridView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	/**
	 * 点击市 区县列表
	 * 
	 * @param city
	 */
	private void set3(String city) {
		List<String> strings = new ArrayList<String>();

		for (int i = 0; i < citysJa.length(); i++) {

			JSONObject jo = citysJa.optJSONObject(i);

			String name = jo.optString("name");

			if (name.equals(city)) {
				this.areaJa = jo.optJSONArray("area");
			}
		}
		for (int i = 0; i < areaJa.length(); i++) {
			JSONObject jo = areaJa.optJSONObject(i);
			String name = jo.optString("name");
			strings.add(name);
		}
		this.addArea = strings;
		showString = this.addArea;
		this.city3GridView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

}
