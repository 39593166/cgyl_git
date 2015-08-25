package com.utoo.chunguanyouli.ui.personal.order;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.TempActivity;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.CgOrderGoodsId;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;

public class CommentGoodsListActivity extends BaseActionBarActivity {
	ArrayList<CgOrderGoodsId> goodsList;
	ListView goodsListView;
	BitmapUtils bu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wait_comment_goods);
		initToolbar("可评论商品");
		bu = new BitmapUtils(this);
		goodsList = (ArrayList<CgOrderGoodsId>) getIntent().getSerializableExtra("goods");
		goodsListView = (ListView) this
				.findViewById(R.id.waitcommend_goodsList);
		goodsListView.setAdapter(adapter);
	}

	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public int getCount() {
			return goodsList.size();
		}

		@Override
		public Object getItem(int position) {
			return goodsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		class ViewHolder {
			ImageView img;
			TextView name;
			TextView num;
			TextView price;
			TextView priceCount;
			Button goComm;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder;
			final CgOrderGoodsId goods = goodsList.get(position);
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = CommentGoodsListActivity.this.getLayoutInflater()
						.inflate(R.layout.commendgoods_checking_goods_item,
								null);
				holder.img = (ImageView) convertView
						.findViewById(R.id.commendgoodsgoods_item_img);
				holder.name = (TextView) convertView
						.findViewById(R.id.commendgoodsgoods_item_name);
				holder.num = (TextView) convertView
						.findViewById(R.id.commendgoodsgoods_item_num);
				holder.price = (TextView) convertView
						.findViewById(R.id.commendgoodsgoods_item_price);
				holder.priceCount = (TextView) convertView
						.findViewById(R.id.commendgoodsgoods_item_price_count);
				holder.goComm = (Button) convertView
						.findViewById(R.id.commendgoodsgoods_item_comm);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			// NetImgLoader.loadImage(goods.getGoodsImage(), holder.img,
			// R.drawable.index_goods_img, 0);
			bu.display(holder.img, goods.getPic());
			holder.name.setText(goods.getGoodsname());
			holder.num.setText("" + goods.getNum());
			holder.price.setText("￥" + String.valueOf(goods.getPrice()));
			holder.priceCount.setText("￥"
					+ String.valueOf(goods.getNum() * goods.getPrice()));
			holder.goComm.setVisibility(View.VISIBLE);
			holder.goComm.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(CommentGoodsListActivity.this,
							CommentActivity.class);
					intent.putExtra("goodsid", goodsList.get(position).getGoodsid());
					CommentGoodsListActivity.this.startActivity(intent);
				}
			});
			return convertView;
		}
	};
}
