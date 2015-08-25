package com.utoo.chunguanyouli.ui.shopping.shoppingcar;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.db.CgDbHelper;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.tool.SelectorAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;
import com.utoo.chunguanyouli.ui.shopping.MakeOrderActivity;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsInfoActivity;

/**
 * 购物车的话
 * 
 * @author fsm
 * 
 */
public class ShoppingcarActivity extends BaseActionBarActivity implements
		OnClickListener, OnItemClickListener {
	ListView goodsListView;
	RelativeLayout layout1, layout2;
	Button b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoppingcar);
		initToolbar("购物车");
		init();
		getMyCar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.shoppingcar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	private void deleteCar() {
		List<CgGoodsId> goods = goodsAdapter.getSelectedDatas();
		CgDbHelper.getInstance(this).deleteCar(goods);
		goodsAdapter.deleteSelecters();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_caar_delete:
			deleteCar();
			break;
		default:
			break;
		}
		return true;
	}

	private void getMyCar() {
		List<CgGoodsId> goodsList = CgDbHelper.getInstance(this)
				.getShoppingCar();
		if (goodsList.size() == 0) {
			layout1.setVisibility(View.GONE);
			layout2.setVisibility(View.VISIBLE);
		} else {
			layout2.setVisibility(View.GONE);
			layout1.setVisibility(View.VISIBLE);
		}
		goodsAdapter.resetData(goodsList);
	}

	SelectorAdapter<CgGoodsId> goodsAdapter;

	private void init() {
		layout1 = (RelativeLayout) this.findViewById(R.id.car_layout1);
		layout2 = (RelativeLayout) this.findViewById(R.id.car_layout2);

		goodsListView = (ListView) findViewById(R.id.car_goods);
		goodsAdapter = new SelectorAdapter<CgGoodsId>(this,
				R.layout.item_my_collection_goods) {

			@Override
			public void convert(ViewHolderHelper helper, CgGoodsId item,
					final int position) {
				helper.setText(R.id.collect_goods_name, item.getName())
						.setText(R.id.collect_goods_price,
								"￥" + item.getPrice())
						.setText(R.id.collect_goods_des, item.getExp())
						.setText(R.id.collect_goods_num_sell,
								item.getNumsell() + "\t人买过")
						// .setText(R.id.comm_goods_user, item.getUid() + "")
						.setImageByUrl(R.id.collect_goods_img, item.getPic(),
								R.drawable.index_goods_list,
								R.drawable.index_goods_list);
				helper.setChecBoxChangeLis(R.id.collect_isSelect,
						goodsAdapter.isSelect(position),
						new OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								goodsAdapter.change(position, isChecked);
							}
						});
			}

		};
		goodsListView.setOnItemClickListener(this);
		goodsListView.setAdapter(goodsAdapter);
		b = (Button) this.findViewById(R.id.car_go_pay);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == b) {
			if (goodsAdapter.getSelectedDatas().size() == 0) {
				Toast.makeText(this, "请选择要购买的商品", 0).show();
				return;
			}
			Intent intent = new Intent();
			intent.setClass(this, MakeOrderActivity.class);
			intent.putExtra("goods",
					(ArrayList<CgGoodsId>) goodsAdapter.getSelectedDatas());
			startActivity(intent);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.setClass(this, GoodsInfoActivity.class);
		intent.putExtra("goodsId", goodsAdapter.getItem(position).getId());
		startActivity(intent);
	}

}
