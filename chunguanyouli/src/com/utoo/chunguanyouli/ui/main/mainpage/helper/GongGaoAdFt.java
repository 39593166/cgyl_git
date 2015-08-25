package com.utoo.chunguanyouli.ui.main.mainpage.helper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.main.information.ArticleListActivity;
import com.utoo.chunguanyouli.ui.main.innanchuan.ContryFindActivity;
import com.utoo.chunguanyouli.ui.main.innanchuan.GoInNanchuanActivity;
import com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner.AddGoodsActivity;

public class GongGaoAdFt implements OnClickListener {
	Activity ctx;
	View pView;

	public GongGaoAdFt(Activity ctx, View pView) {
		this.ctx = ctx;
		this.pView = pView;
		findViews();
	}

	private Button mainpageGonanchuan;
	private Button mainpageCunguandiandi;
	private Button mainpageWoyaotuijian;
	private TextView gonggao;

	private void findViews() {
		mainpageGonanchuan = (Button) pView
				.findViewById(R.id.mainpage_gonanchuan);
		mainpageCunguandiandi = (Button) pView
				.findViewById(R.id.mainpage_cunguandiandi);
		mainpageWoyaotuijian = (Button) pView
				.findViewById(R.id.mainpage_woyaotuijian);
		gonggao = (TextView) pView.findViewById(R.id.mainpage_gonggao);
		mainpageGonanchuan.setOnClickListener(this);
		mainpageCunguandiandi.setOnClickListener(this);
		mainpageWoyaotuijian.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == mainpageGonanchuan) {
			Intent intent = new Intent();
			intent.setClass(ctx, GoInNanchuanActivity.class);
			ctx.startActivity(intent);
		} else if (v == mainpageCunguandiandi) {
			Intent intent = new Intent();
			intent.setClass(ctx, ArticleListActivity.class);
			ctx.startActivity(intent);
		} else if (v == mainpageWoyaotuijian) {
			Intent intent = new Intent();
			intent.setClass(ctx, AddGoodsActivity.class);
			ctx.startActivity(intent);
		}
	}

}
