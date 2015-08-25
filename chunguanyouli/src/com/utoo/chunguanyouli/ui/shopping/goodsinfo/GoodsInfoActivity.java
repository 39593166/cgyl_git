package com.utoo.chunguanyouli.ui.shopping.goodsinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utoo.autoviewpager.AutoScrollViewPager;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.db.CgDbHelper;
import com.utoo.chunguanyouli.dbentity.CgCollectId;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.CgReplyId;
import com.utoo.chunguanyouli.dbentity.Pics;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.DateTool;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.shopping.MakeOrderActivity;
import com.utoo.chunguanyouli.ui.shopping.shoppingcar.ShoppingcarActivitySingle;
import com.utoo.chunguanyouli.ui.village.VillagesIntroduceActivity;
import com.utoo.pageradapter.MyObjectPagerAdapter;
import com.utoo.pagertranseffect.DepthPageTransformer;

public class GoodsInfoActivity extends NetDataBaseActionBarActivity implements
		OnClickListener {
	private static final int TAG_GETINFO = 0;
	private static final int TAG_GETREPLY = 2;
	private static final int TAG_COLLECT = 1;
	private static final int TAG_INFOEX = 3;
	int goodsId = 0;
	CgGoodsId goods = null;
	Toolbar toolbar;
	LinearLayout parent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_info);
		parent = (LinearLayout) this.findViewById(R.id.parent);
		goodsId = getIntent().getIntExtra("goodsId", 0);
		findViews();

		initToolbar("商品详细");
		findInfomationViews();
		findCommViews();

		getGoodsInfo();
		getGoodsExInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.goods_info, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_goods_info_collect:
			collect();
			break;
		case R.id.menu_goods_info_car:
			Intent intent = new Intent();
			intent.setClass(GoodsInfoActivity.this,
					ShoppingcarActivitySingle.class);
			GoodsInfoActivity.this.startActivity(intent);
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 获取商品基本信息
	 */
	private void getGoodsInfo() {
		apply(API_F.getGoodsInfo(goodsId, TAG_GETINFO));
	}

	/**
	 * 获取商品附加信息
	 */
	private void getGoodsExInfo() {
		apply(API_F.getGoodsExInfo(goodsId, TAG_INFOEX));
	}

	/**
	 * 评论信息
	 */
	private void getReply() {
		apply(API_F.getGoodsReply(goodsId, 1, TAG_GETREPLY));
	}

	/**
	 * 收藏商品
	 */
	private void collect() {
		if (goods == null) {
			Toast.makeText(this, "请等待加载商品信息", Toast.LENGTH_SHORT).show();
			return;
		} else if (getUser1() == null) {
			goLogin();
		} else {
			CgCollectId coll = new CgCollectId(0, getUser1().getId(),
					DateTool.getTimeStr(System.currentTimeMillis(),
							"yyyy-MM-dd HH:mm:ss"), goodsId, 0);
			apply(API_F.collect(coll, getUser1().getSid(), TAG_COLLECT));
		}
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// goodsInfoReflesh.setRefreshing(false);

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// goodsInfoReflesh.setRefreshing(false);

		Gson gson = new Gson();
		if (TAG == TAG_GETINFO) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			if (cr.getValNum() == 0) {
				this.goodsInfoName.setText("该商品已下架");
				return;
			}
			List<CgGoodsId> goodsList = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgGoodsId>>() {
					}.getType());
			if (goodsList != null && goodsList.size() > 0) {
				setGoodsInfo(goodsList.get(0));
				this.goods = goodsList.get(0);
			}
		} else if (TAG == TAG_GETREPLY) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			List<CgReplyId> reply = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgReplyId>>() {
					}.getType());
			if (reply != null && reply.size() > 0) {
				setReplyInfo(reply.get(0));
			} else {
				setReplyInfo(null);
			}
		} else if (TAG == TAG_COLLECT) {
			try {
				JSONObject json = new JSONObject(response);
				int state = json.optInt("state");
				if (state == 1) {
					Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (TAG == TAG_INFOEX) {
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			List<ExInfo> reply = gson.fromJson(cr.getValStr(),
					new TypeToken<List<ExInfo>>() {
					}.getType());
			if (reply != null && reply.size() > 0) {
				this.goodsInfoPoster.setText("村官：\t" + reply.get(0).getUser());
				this.goodsInfoSpace.setText("产地：\t" + reply.get(0).getC3()
						+ reply.get(0).getC2() + reply.get(0).getC1());
			} else {
			}
		}

	}

	private void setGoodsInfo(CgGoodsId goods) {
		goodsInfoDW.setText(goods.getPack());
		goodsInfoKC.setText(goods.getNumhave() + "");
		goodsInfoDate.setText("发布日期：\t" + goods.getDatesend());
		goodsInfoName.setText("商品名：\t" + goods.getName());
		goodsInfoContent.setText("商品详细\n\t" + goods.getContent());
		goodsInfoSoldSum.setText("\t销售量：\t" + goods.getNumsell() + "");
		goodsInfoPrice.setText("价格：\t￥" + goods.getPrice() + "元");
		if (goods.getPricenot() != 0.0) {
			goodsInfoPriceNot.setVisibility(View.VISIBLE);
			goodsInfoPriceNot.setText("折扣优惠：\t￥" + goods.getPricenot() + "元");
		}
		goodsInfoFarmer.setText("养植人：\t" + goods.getFromuser());
		toolbar.setTitle(goods.getName());
		List<Pics> pics = new ArrayList<Pics>();
		if (goods.getPic1() != null)
			pics.addAll(goods.getPic1());
		if (goods.getPic2() != null)
			pics.addAll(goods.getPic2());
		if (goods.getPic3() != null)
			pics.addAll(goods.getPic3());
		if (pics.size() == 0) {
			pics.add(new Pics(goods.getId(), 1, ""));
		}
		MyObjectPagerAdapter<Pics> adapter = new MyObjectPagerAdapter<Pics>(
				pics, this, R.drawable.index_news_grid) {

			@Override
			public String getImageUrl(Pics data) {
				return data.getUrl();
			}

			@Override
			public int getImageResource(Pics data) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		goodsPicsPager.setAdapter(adapter);
		goodsPicsPager.startAutoScroll();
		goodsPicsPager.setPageTransformer(true, new DepthPageTransformer());
		getReply();
	}

	private void setReplyInfo(CgReplyId cgReplyId) {
		if (cgReplyId != null) {
			goodsInfoCommlayout.setVisibility(View.VISIBLE);
			itemCommendContent.setText(cgReplyId.getContent());
			itemCommendTime.setText(cgReplyId.getDatesend());
			itemCommendName.setText(cgReplyId.getUname());
		}
	}

	// ////////////评论///////////////////////////
	// private CircleImageView itemCommendHead;
	private TextView itemCommendName;
	private TextView itemCommendTime;
	private TextView itemCommendContent;
	private LinearLayout goodsInfoCommlayout;

	// ////////////商品信息///////////////////////////////
	private TextView goodsInfoName;
	private TextView goodsInfoContent;
	private TextView goodsInfoPrice;
	private TextView goodsInfoPriceNot;
	private TextView goodsInfoDW;
	private TextView goodsInfoKC;
	private RelativeLayout goodsInfoAppendPrice;
	private TextView goodsInfoSpace;
	private TextView goodsInfoSoldSum;
	private TextView goodsInfoDate;
	private TextView goodsInfoFarmer;
	private TextView goodsInfoPoster;
	// ///////////页面///////////////////
	// private SwipeRefreshLayout goodsInfoReflesh;
	// private RLScrollView goodsInfoScrollView;

	private Button goodsInfoGoPics;
	private Button goodsInfoGoCanshu;
	private Button goodsInfoGoDianputuijian;
	private ImageView goodsInfoImage;
	// private TextView goodsInfoCommSum;
	private Button goodsInfoGoCommends;
	private Button goodsInfoAddCar;
	private Button goodsInfoBuy;

	private AutoScrollViewPager goodsPicsPager;

	private void findViews() {
		toolbar = (Toolbar) findViewById(R.id.toolBar);
		goodsInfoGoPics = (Button) findViewById(R.id.goodsInfoGoPics);
		goodsInfoGoCanshu = (Button) findViewById(R.id.goodsInfoGoCanshu);
		goodsInfoGoDianputuijian = (Button) findViewById(R.id.goodsInfoGoDianputuijian);
		goodsInfoImage = (ImageView) findViewById(R.id.goodsInfoImage);
		// goodsInfoCommSum = (TextView) findViewById(R.id.goodsInfoCommSum);
		goodsInfoGoCommends = (Button) findViewById(R.id.goodsInfoGoCommends);
		goodsInfoAddCar = (Button) findViewById(R.id.goodsInfoAddCar);
		goodsInfoBuy = (Button) findViewById(R.id.goodsInfoBuy);
		goodsPicsPager = (AutoScrollViewPager) findViewById(R.id.goodsInfoImages);
		goodsInfoGoPics.setOnClickListener(this);
		goodsInfoGoCanshu.setOnClickListener(this);
		goodsInfoGoDianputuijian.setOnClickListener(this);
		goodsInfoGoCommends.setOnClickListener(this);
		goodsInfoAddCar.setOnClickListener(this);
		goodsInfoBuy.setOnClickListener(this);
		// pager.setEnabled(false);
	}

	private void findCommViews() {
		// itemCommendHead = (CircleImageView)
		// findViewById(R.id.item_commend_head);
		itemCommendName = (TextView) findViewById(R.id.item_commend_name);
		itemCommendTime = (TextView) findViewById(R.id.item_commend_time);
		itemCommendContent = (TextView) findViewById(R.id.item_commend_context);
		goodsInfoCommlayout = (LinearLayout) findViewById(R.id.goodsInfoCommlayout);
	}

	private void findInfomationViews() {
		goodsInfoDW = (TextView) findViewById(R.id.goodsInfoDW);
		goodsInfoKC = (TextView) findViewById(R.id.goodsInfoKC);
		goodsInfoName = (TextView) findViewById(R.id.goodsInfoName);
		goodsInfoContent = (TextView) findViewById(R.id.goodsInfoContent);
		goodsInfoPrice = (TextView) findViewById(R.id.goodsInfoPrice);
		goodsInfoPriceNot = (TextView) findViewById(R.id.goodsInfoPriceNot);
		goodsInfoAppendPrice = (RelativeLayout) findViewById(R.id.goodsInfoAppendPrice);
		goodsInfoSpace = (TextView) findViewById(R.id.goodsInfoSpace);
		goodsInfoSoldSum = (TextView) findViewById(R.id.goodsInfoSoldSum);
		goodsInfoDate = (TextView) findViewById(R.id.goodsInfoDate);
		goodsInfoFarmer = (TextView) findViewById(R.id.goodsInfoFarmer);
		goodsInfoPoster = (TextView) findViewById(R.id.goodsInfoPoster);
		goodsInfoSpace.setOnClickListener(this);
	}

	// @Override
	// protected void reflesh() {
	// apply(API_F.getGoodsInfo(goodsId, TAG_GETINFO));
	// }
	PopupWindow popupWindow;

	private void initPopWindow() {
		View view = this.getLayoutInflater().inflate(
				R.layout.inc_goods_info_pop_spechoose, null);

		Log.e("isPopViewNull?", "" + view);
		popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		popupWindow.setOutsideTouchable(true);

		popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		popupWindow.update();
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// TextView camera = (TextView) view.findViewById(R.id.);
		initPopWindowViews(view);
	}

	private Button goodsInfoSubBtn;
	private TextView goodsInfoBuySum;
	private Button goodsInfoAddBtn;
	private Button goodsInfoBack;
	private Button goodsInfoPopBuy;
	private int buySum = 1;

	private void initPopWindowViews(View pView) {
		/**
		 * Find the Views in the layout<br />
		 * <br />
		 * Auto-created on 2015-08-06 13:38:52 by Android Layout Finder
		 * (http://www.buzzingandroid.com/tools/android-layout-finder)
		 */
		goodsInfoSubBtn = (Button) pView.findViewById(R.id.goodsInfoSubBtn);
		goodsInfoBuySum = (TextView) pView.findViewById(R.id.goodsInfoBuySum);
		goodsInfoAddBtn = (Button) pView.findViewById(R.id.goodsInfoAddBtn);
		goodsInfoBack = (Button) pView.findViewById(R.id.goodsInfoBack);
		goodsInfoPopBuy = (Button) pView.findViewById(R.id.goodsInfoPopBuy);

		goodsInfoSubBtn.setOnClickListener(this);
		goodsInfoAddBtn.setOnClickListener(this);
		goodsInfoBack.setOnClickListener(this);
		goodsInfoPopBuy.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (goods == null) {
			Toast.makeText(this, "请等待加载商品信息", Toast.LENGTH_SHORT).show();
			return;
		}
		if (v == goodsInfoGoPics) {
			// Intent intent = new Intent();
			// intent.setClass(this, GoodsPicsActivity.class);
			// this.startActivity(intent);
		} else if (v == goodsInfoGoCanshu) {
			Intent intent = new Intent();
			intent.putExtra("goodsId", goodsId);
			intent.setClass(this, GoodsCommendActivity.class);
			startActivity(intent);

		} else if (v == goodsInfoGoDianputuijian) {
			Intent intent = new Intent();
			intent.putExtra("cid", goods.getCid());
			intent.setClass(this, TheShopRecommendActivity.class);
			startActivity(intent);
		} else if (v == goodsInfoGoCommends) {
			Intent intent = new Intent();
			intent.putExtra("goodsId", goodsId);
			intent.setClass(this, GoodsCommendActivity.class);
			startActivity(intent);
		} else if (v == goodsInfoAddCar) {
			long carsum = CgDbHelper.getInstance(this).addCar(goods);
			Log.e("加入购物车", carsum + "");
			Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();

		} else if (v == goodsInfoBuy) {
			if (goods.getNumhave() <= 0) {
				Toast.makeText(this, "没有库存", 0).show();
				return;
			} else {
				initPopWindow();
				goodsInfoBuySum.setText(buySum + "");
				popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			}

		} else if (v == goodsInfoSpace) {
			Intent intent = new Intent();
			intent.setClass(this, VillagesIntroduceActivity.class);
			intent.putExtra("id_a", goods.getCid());
			startActivity(intent);
		} else if (v == goodsInfoSubBtn) {
			if (buySum <= 1) {
				return;
			} else {
				buySum--;
				goodsInfoBuySum.setText(buySum + "");

			}
		} else if (v == goodsInfoAddBtn) {
			if (buySum >= goods.getNumhave()) {
				return;
			} else {
				buySum++;
				goodsInfoBuySum.setText(buySum + "");

			}
		} else if (v == goodsInfoBack) {
			popupWindow.dismiss();
		} else if (v == goodsInfoPopBuy) {
			goods.setBuyNum(buySum);
			ArrayList<CgGoodsId> goodsList = new ArrayList<CgGoodsId>();
			goodsList.add(goods);
			Intent intent = new Intent();
			intent.setClass(this, MakeOrderActivity.class);
			intent.putExtra("goods", goodsList);
			// intent.putExtra("buySum", buySum);
			startActivity(intent);
		}
	}

	class ExInfo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3360818924276604414L;

		private String user;

		private String c1;

		private String c2;

		private String c3;

		public void setUser(String user) {
			this.user = user;
		}

		public String getUser() {
			return this.user;
		}

		public void setC1(String c1) {
			this.c1 = c1;
		}

		public String getC1() {
			return this.c1;
		}

		public void setC2(String c2) {
			this.c2 = c2;
		}

		public String getC2() {
			return this.c2;
		}

		public void setC3(String c3) {
			this.c3 = c3;
		}

		public String getC3() {
			return this.c3;
		}

	}
}
