package com.utoo.chunguanyouli.ui.personal.cg.collage;

/**
 * 新闻管理
 */
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
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
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.chunguanyouli.server.API_C;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.village.AddNewsActivity;
import com.utoo.chunguanyouli.ui.village.VillageDropsDetailActivity;

@ContentView(R.layout.activity_villagedrops)
public class NewsManagerActivity extends NetDataBaseActionBarActivity implements
		OnRefreshListener2<ListView> {

	private AlertDialog.Builder builder;

	private static final int TAG_MORE = 1;
	private static final int TAG_REFLESH = 2;
	private static final int TAG_DELETE = 11;
	private int tid = 1;
	private int cid;
	private int p = 1;
	private Intent intent = new Intent();
	// private VillageDropsAdapter adapter;
	private CommonAdapter<CgNewsId> adapter1;
	// private List<CgNewsId> list = new ArrayList<CgNewsId>();
	private ListView listView;
	private int index;

	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	@ViewInject(R.id.group)
	private RadioGroup group;

	// ListView
	@ViewInject(R.id.articleListView)
	private PullToRefreshListView pullToRefreshListView;

	// // ScrollView
	// @ViewInject(R.id.scrollView)
	// private ScrollView scrollView;

	@OnItemClick(R.id.articleListView)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		CgNewsId info = adapter1.getItem(position - 1);

		intent.setClass(this, VillageDropsDetailActivity.class);
		intent.putExtra("news", info);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initToolbar("村庄资讯管理");
		cid = getIntent().getIntExtra("cid", 0);
		init();
	}

	private void init() {
		listView = pullToRefreshListView.getRefreshableView();
		pullToRefreshListView.setMode(Mode.BOTH);
		pullToRefreshListView.setOnRefreshListener(this);

		// adapter = new VillageDropsAdapter(this, list);
		// pullToRefreshListView.setAdapter(adapter);
		adapter1 = new CommonAdapter<CgNewsId>(this,
				R.layout.adapter_villagedrops) {

			@Override
			public void convert(ViewHolderHelper helper, CgNewsId item) {
				// TODO Auto-generated method stub
				helper.setText(R.id.title, item.getTitle());
				helper.setText(R.id.content, item.getContent());
				helper.setImageByUrl(R.id.icon, item.getPic(),
						R.drawable.index_news_list_small,
						R.drawable.index_news_list_small);
			}
		};
		pullToRefreshListView.setAdapter(adapter1);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// Log.e("pos", position + "");
				// Log.e("adasum", adapter.getCount() + "");
				// Log.e("viewSum", pullToRefreshListView.getChildCount() + "");
				final CgNewsId info = adapter1.getItem(position - 1);

				builder.setPositiveButton("修改",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent();
								intent.setClass(NewsManagerActivity.this,
										ModifyDiandiActivity.class);
								intent.putExtra("news", info);
								startActivity(intent);
							}
						});
				builder.setNegativeButton("删除",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								index = position - 1;
								apply(API_C.getDelete(TAG_DELETE, info.getId()));
							}
						});
				builder.show();
				return true;
			}
		});
		applyData(TAG_MORE);

		builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setTitle("请选择？");
		builder.setNeutralButton("取消", null);

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
				adapter1.clearData();
				applyData(TAG_MORE);

			}
		});
	}

	/**
	 * 请求新闻数据
	 */
	private void applyData(int tag) {
		if (tag == TAG_MORE) {
			apply(API_C.getVillageNews(tag, cid, tid, p));
		} else if (tag == TAG_REFLESH) {
			apply(API_C.getVillageNews(tag, cid, tid, 1));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.cunguandiandi, menu);

		if (getUser1()!=null&&getUser1().getTid() == 1) {
			return super.onCreateOptionsMenu(menu);
		} else {
			return false;

		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.newsmanagerAdd) {
			Intent intent = new Intent();
			intent.setClass(NewsManagerActivity.this, AddNewsActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// @Override
	// protected void onResume() {
	// // TODO Auto-generated method stub
	// super.onResume();
	// scrollView.smoothScrollTo(0, 0);
	// }

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

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

		if (TAG == TAG_REFLESH) {
			p = 2;
			adapter1.resetData(typesList);

		} else if (TAG == TAG_MORE) {
			p++;
			// this.list.addAll(typesList);
			// adapter.notifyDataSetChanged();
			adapter1.addData(typesList);
		} else if (TAG == TAG_DELETE) {
			// list.remove(index);
			// adapter.notifyDataSetChanged();
			adapter1.delete(index);
		}

		// if (TAG == 1) {
		//
		// this.list.clear();
		// this.list.addAll(typesList);
		// adapter.notifyDataSetChanged();
		// }
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		applyData(TAG_REFLESH);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		applyData(TAG_MORE);
	}

}
