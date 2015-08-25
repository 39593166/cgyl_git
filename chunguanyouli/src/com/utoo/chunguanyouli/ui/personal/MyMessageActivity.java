package com.utoo.chunguanyouli.ui.personal;

/**
 * 我的消息
 */
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.info.MyMessageInfo;
import com.utoo.chunguanyouli.ui.base.RefleshableActivity;
import com.utoo.chunguanyouli.ui.personal.adapter.MyMessageAdapter;
import com.utoo.chunguanyouli.ui.personal.adapter.MyMessageAdapter.UserContorl;

@ContentView(R.layout.activity_mymessage)
public class MyMessageActivity extends RefleshableActivity implements UserContorl {

	// 参数
	private int num = 0;

	private List<MyMessageInfo> list = new ArrayList<MyMessageInfo>();
	private MyMessageAdapter adapter;

	// Toolbar
	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	// ListView
	@ViewInject(R.id.listView)
	private ListView listView;

	// LinearLayout
	@ViewInject(R.id.linearLayout)
	private LinearLayout layout;

	// 删除
	@ViewInject(R.id.delete)
	private TextView deleteTextView;

	// 点击事件
	@OnClick({ R.id.delete, R.id.clean })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.delete:// 删除

			adapter.notifyDataSetChanged();

			break;

		case R.id.clean:// 清空全部
			list.clear();
			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}

	// 选中checkbox
	@Override
	public void onChecked(boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked) {
			num++;
		} else {
			num--;
		}

		if (num == 0) {
			deleteTextView.setEnabled(false);
		} else {
			deleteTextView.setEnabled(true);
		}

		deleteTextView.setText(String.format("删除(%d)", num));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();

		List<MyMessageInfo> list = new ArrayList<MyMessageInfo>();
		for (int i = 0; i < 100; i++) {
			MyMessageInfo info = new MyMessageInfo();
			info.setTitle("测试title" + i);
			info.setTime("2015-01-1" + i);
			info.setState("已读");
			list.add(info);
		}

		this.list.addAll(list);
		adapter.init();
		adapter.notifyDataSetChanged();
		// adapter.showCheckBox();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_mymessage, menu);
		return super.onCreateOptionsMenu(menu);
	}

	private void init() {
		// TODO Auto-generated method stub
		ViewUtils.inject(this);

		toolbar.setTitle("我的消息");
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				switch (item.getItemId()) {
				case R.id.delete:
					if (adapter.integers.get(0) == View.GONE) {
						for (int i = 0; i < adapter.getCount(); i++) {
							adapter.integers.set(i, View.VISIBLE);
						}
						item.setIcon(null);
						layout.setVisibility(View.VISIBLE);
					} else {
						for (int i = 0; i < adapter.getCount(); i++) {
							adapter.integers.set(i, View.GONE);
						}
						item.setIcon(R.drawable.index);
						layout.setVisibility(View.GONE);
					}
					adapter.notifyDataSetChanged();
					break;

				default:
					break;
				}
				return true;
			}
		});
		adapter = new MyMessageAdapter(this, list, this);
		listView.setAdapter(adapter);
	}

	@Override
	protected void reflesh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		
	}

}
