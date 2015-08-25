package com.utoo.chunguanyouli.ui.village;

/**
 * 村官点滴详情
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

@ContentView(R.layout.activity_villagedropsdetail)
public class VillageDropsDetailActivity extends NetDataBaseActionBarActivity {

	private Picasso picasso;

	@ViewInject(R.id.toolBar)
	private Toolbar toolbar;

	@ViewInject(R.id.title)
	private TextView titleTextView;

	@ViewInject(R.id.content)
	private TextView contentTextView;

	@ViewInject(R.id.time)
	private TextView timeTextView;

	@ViewInject(R.id.icon)
	private ImageView icon;
	CgNewsId news;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		picasso = Picasso.with(this);
		news = (CgNewsId) getIntent().getSerializableExtra("news");
		initToolbar(news.getTitle());

		picasso.load(news.getPic()).placeholder(R.drawable.index_news_list_big)
				.resize(150, 500).into(icon);
		// }
		timeTextView.setText(news.getDatesend());
		titleTextView.setText(news.getTitle());
		contentTextView.setText(news.getContent());

	}

	public void goCity(View v) {
		Intent intent = new Intent();
		intent.setClass(this, VillagesIntroduceActivity.class);
		intent.putExtra("id_a", news.getCid());
		startActivity(intent);
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
