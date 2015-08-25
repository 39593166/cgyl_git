package com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.BuilderHelper;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;

public class GoodsMannerSallingListFragment extends NetDataBaseFragment
		implements OnRefreshListener2<ListView> {
	private static final int TAG_MORE = 1;
	private static final int TAG_REFLESH = 2;
	private static final int TAG_DELETE = 3;
	private static final int TAG_UPDATE_RECOMM = 0;
	private static final int TAG_UPDATE_PRICENOT = 4;
	CommonAdapter<CgGoodsId> sallingAdapter;
	Activity context;
	View pView;
	int pageIndex = 1;

	PullToRefreshListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		pView = inflater.inflate(R.layout.fragment_goods_manager, container,
				false);

		initViews();

		return pView;
	}

	@Override
	public void onResume() {
		getGoods(TAG_MORE, pageIndex);
		super.onResume();
	}

	private void getGoods(int Tag, int pageIndex) {
		apply(API_F.getMyStoreGoods(// act.userNeed.getId(),
									// act.userNeed.getSid(),
				act.userNeed.getCid(), 1, pageIndex, Tag));
	}

	private void initViews() {
		list = (PullToRefreshListView) this.pView
				.findViewById(R.id.goodsManagerList);
		list.setMode(Mode.BOTH);
		list.setOnRefreshListener(this);
		initAdapter();
	}

	CgGoodsId delettingGoods;
	AlertDialog.Builder builder;

	private void initAdapter() {
		// final String PriceNot = "打折销售";
		// final String PriceNoted = "取消打折";
		sallingAdapter = new CommonAdapter<CgGoodsId>(getActivity(),
				R.layout.item_goodsmanner_saling) {
			@Override
			public void convert(final ViewHolderHelper helper,
					final CgGoodsId item) {
				helper.setText(R.id.goodsMannerName, "商品名：\t" + item.getName())
						.setText(R.id.goodsMannerPrice,
								"价格：￥" + item.getPrice() + "元")
						.setText(R.id.goodsMannerSoldSum,
								"已售: " + item.getNumsell())
						.setText(R.id.goodsMannerStoreSum,
								"库存:" + item.getNumhave())
						.setImageByUrl(R.id.goodsMannerImage, item.getPic(),
								R.drawable.index_goods_list,
								R.drawable.index_goods_list)
						.setText(R.id.goodsSalingMannerTj,
								item.isUtj() ? "取消推荐" : "推荐")
						.setText(R.id.goodsSalingMannerPriceNot,
								item.getPricenot() == 0 ? "打折销售" : "取消打折")
						.setText(R.id.goodsMannerPriceNot,
								"折扣优惠：\t￥" + item.getPricenot())
						.setText(
								R.id.goodsMannerDW,
								item.getPack() == null
										|| item.getPack().equals("") ? "未设置单位"
										: item.getPack())
						.VisableView(R.id.goodsMannerPriceNot,
								item.getPrice() > item.getPricenot());
				;
				android.view.View.OnClickListener lis = new android.view.View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int viewId = v.getId();
						if (viewId == R.id.goodsSalingMannerDelete) {
							BuilderHelper.showBuilder(context, "确认删除吗",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											delettingGoods = item;
											deleteGoods(delettingGoods);
										}
									});

						} else if (viewId == R.id.goodsSalingMannerEdit) {
							// 编辑
							Intent intent = new Intent();
							intent.setClass(context, UpdateGoodsActivity.class);
							intent.putExtra("goods", item);
							startActivity(intent);
						} else if (viewId == R.id.goodsSalingMannerTj) {
							delettingGoods = item;
							updateGoods(item, TAG_UPDATE_RECOMM);
						} else if (viewId == R.id.goodsSalingMannerPriceNot) {
							if (item.getPricenot() > 0) {// 取消打折，恢复原价
								item.setPricenot(0.00);
								updateGoods(item, TAG_UPDATE_PRICENOT);
							} else {// 价格相同，没有打折，转入设置打折价格
								builder = new AlertDialog.Builder(context);
								View view = context
										.getLayoutInflater()
										.inflate(
												R.layout.dialog_set_goods_pricenot,
												null);
								builder.setView(view);
								final EditText editText = (EditText) view
										.findViewById(R.id.dia_pricenot);
								Button reset = (Button) view
										.findViewById(R.id.dia_resetprice);
								reset.setOnClickListener(new android.view.View.OnClickListener() {

									@Override
									public void onClick(View v) {
										// 点击重设价格恢复到原价
										editText.setText("" + item.getPrice());
									}
								});
								builder.setTitle("设置折扣价格");
								builder.setPositiveButton("确定",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												String priceNotTemp = editText
														.getText().toString()
														.trim();
												if (TextUtils
														.isEmpty(priceNotTemp)) {
													return;
												}
												double priceNot = Double
														.parseDouble(priceNotTemp);
												item.setPricenot(priceNot);
												// if (item.getPrice() == item
												// .getPricenot()) {
												// helper.setText(
												// R.id.goodsSalingMannerPriceNot,
												// "打折销售");
												// } else {
												// helper.setText(
												// R.id.goodsSalingMannerPriceNot,
												// "取消打折");
												// }
												if (priceNot > item.getPrice()) {
													Toast.makeText(context,
															"价格设置不合理，请重新定价",
															Toast.LENGTH_SHORT)
															.show();
													return;
												}
												updateGoods(item,
														TAG_UPDATE_PRICENOT);
											}
										});
								builder.setNegativeButton("取消",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
											}
										});
								builder.show();
							}

						}
					}
				};
				// if (item.getPrice() == item.getPricenot()) {
				// helper.setText(R.id.goodsSalingMannerPriceNot, PriceNot);
				// } else {
				// helper.setText(R.id.goodsSalingMannerPriceNot, PriceNoted);
				// helper.setText(R.id.goodsMannerPriceNot,
				// "折扣价格：\t￥" + item.getPricenot());
				// }
				helper.setClickListener(R.id.goodsSalingMannerEdit, lis);
				helper.setClickListener(R.id.goodsSalingMannerDelete, lis);
				helper.setClickListener(R.id.goodsSalingMannerTj, lis);
				helper.setClickListener(R.id.goodsSalingMannerPriceNot, lis);
			}
		};
		list.setAdapter(sallingAdapter);
	}

	private void updateGoods(CgGoodsId goods, int tag) {
		goods.setUtj(!goods.isUtj());
		goods.setPic1(null);
		goods.setPic2(null);
		goods.setPic3(null);
		apply(API_F.updateGoods(goods, act.userNeed.getId(),
				act.userNeed.getSid(), goods.getId(), tag));
	}

	private void deleteGoods(CgGoodsId goods) {
		apply(API_F.deleteGoods(goods.getId(), TAG_DELETE));
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		list.onRefreshComplete();
	}

	@Override
	protected void onReturned(String response, int TAG) {
		list.onRefreshComplete();
		if (TAG == TAG_MORE) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			List<CgGoodsId> goodsList = null;
			Gson gson = new Gson();

			goodsList = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgGoodsId>>() {
					}.getType());
			sallingAdapter.addData(goodsList);
			pageIndex++;
		} else if (TAG == TAG_REFLESH) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			List<CgGoodsId> goodsList = null;
			Gson gson = new Gson();

			goodsList = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgGoodsId>>() {
					}.getType());
			sallingAdapter.resetData(goodsList);
			pageIndex = 2;
		} else if (TAG == TAG_DELETE) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 1) {
					// 删除成功，清除缓存
					deleteSoftCatch();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (TAG == TAG_UPDATE_RECOMM) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 1) {
					// 修改成功，修改显示
					// deleteSoftCatch();
					// delettingGoods.setUtj(!delettingGoods.isUtj());
					sallingAdapter.notifyDataSetChanged();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (TAG == TAG_UPDATE_PRICENOT) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 1) {
					// 修改成功，修改显示
					// deleteSoftCatch();
					// delettingGoods.setUtj(!delettingGoods.isUtj());
					sallingAdapter.notifyDataSetChanged();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void deleteSoftCatch() {
		// TODO Auto-generated method stub
		sallingAdapter.delete(delettingGoods);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		getGoods(TAG_REFLESH, 1);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		getGoods(TAG_MORE, pageIndex);
	}

}
