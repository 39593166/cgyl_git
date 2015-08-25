package com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.Pics;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.ClientConfigs;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.views.NoScrollGridView;

public class UpdateGoodsMainImgActivity extends NetDataBaseActionBarActivity
		implements OnItemClickListener {
	private static final int TAG_UPDATE = 0;
	CgGoodsId goods;
	CommonAdapter<Pics> adapter1;
	CommonAdapter<Pics> adapter2;
	CommonAdapter<Pics> adapter3;
	String path = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_goods_main_img);
		initToolbar("修改商品首图");
		goods = (CgGoodsId) getIntent().getSerializableExtra("goods");
		path = goods.getPic();
		findViews();
		init();
	}

	private void init() {
		adapter1 = new CommonAdapter<Pics>(this, goods.getPic1(),
				R.layout.item_add_goods_image) {

			@Override
			public void convert(ViewHolderHelper helper, Pics item) {
				helper.setImageByUrl(R.id.item_add_goods_imageview,
						item.getUrl(), R.drawable.index_goods_list,
						R.drawable.index_goods_list);
			}
		};
		adapter2 = new CommonAdapter<Pics>(this, goods.getPic2(),
				R.layout.item_add_goods_image) {

			@Override
			public void convert(ViewHolderHelper helper, Pics item) {
				helper.setImageByUrl(R.id.item_add_goods_imageview,
						item.getUrl(), R.drawable.index_goods_list,
						R.drawable.index_goods_list);
			}
		};
		adapter3 = new CommonAdapter<Pics>(this, goods.getPic3(),
				R.layout.item_add_goods_image) {

			@Override
			public void convert(ViewHolderHelper helper, Pics item) {
				helper.setImageByUrl(R.id.item_add_goods_imageview,
						item.getUrl(), R.drawable.index_goods_list,
						R.drawable.index_goods_list);
			}
		};
		updateGoodsImageList.setAdapter(adapter1);
		addPersonImageList.setAdapter(adapter2);
		addAreaImageList.setAdapter(adapter3);
		Picasso.with(this).load(goods.getPic())
				.resizeDimen(R.dimen.image_size_list, R.dimen.image_size_list)
				.error(R.drawable.index_goods_list).into(mainImg);
	}

	private ImageView mainImg;
	private NoScrollGridView updateGoodsImageList;
	private NoScrollGridView addPersonImageList;
	private NoScrollGridView addAreaImageList;

	private void findViews() {
		mainImg = (ImageView) findViewById(R.id.mainImg);
		updateGoodsImageList = (NoScrollGridView) findViewById(R.id.updateGoodsImageList);
		addPersonImageList = (NoScrollGridView) findViewById(R.id.addPersonImageList);
		addAreaImageList = (NoScrollGridView) findViewById(R.id.addAreaImageList);

		addAreaImageList.setOnItemClickListener(this);
		updateGoodsImageList.setOnItemClickListener(this);
		addPersonImageList.setOnItemClickListener(this);
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		if (TAG == TAG_UPDATE) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0) {
					Toast.makeText(this, "提交失败", Toast.LENGTH_SHORT).show();
					return;
				} else {
					Toast.makeText(this, "上传成功", 0).show();
					Intent intent = new Intent();
					intent.putExtra("pic", path);
					setResult(RESULT_OK, intent);
					this.finish();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public void submit(View v) {
		goods.setPic(path);
		apply(API_F.updateGoods(goods.copy(), userNeed.getId(),
				userNeed.getSid(), goods.getId(), TAG_UPDATE), false);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (parent == updateGoodsImageList) {
			path = adapter1.getItem(position).getOUrl();
		} else if (parent == addPersonImageList) {
			path = adapter2.getItem(position).getOUrl();
		} else if (parent == addAreaImageList) {
			path = adapter3.getItem(position).getOUrl();
		}
		Picasso.with(this).load(ClientConfigs.PICHOST + path)
				.resizeDimen(R.dimen.image_size_list, R.dimen.image_size_list)
				.error(R.drawable.index_goods_list).into(mainImg);
	}
}
