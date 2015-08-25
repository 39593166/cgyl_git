package com.utoo.chunguanyouli.ui.main;

import java.io.File;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.MyApplication;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgOrderId;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.ui.account.LoginActivity;
import com.utoo.chunguanyouli.ui.main.mainpage.MainActivity;
import com.utoo.chunguanyouli.ui.personal.cg.collage.VillageManagerActivity;
import com.utoo.chunguanyouli.ui.personal.cg.dianpu.MyShopActivity;
import com.utoo.chunguanyouli.ui.personal.collection.MyCollectionContryActivity;
import com.utoo.chunguanyouli.ui.personal.collection.MyCollectionGoodsActivity;
import com.utoo.chunguanyouli.ui.personal.order.OrderListActivity;
import com.utoo.chunguanyouli.ui.shopping.GoodsClassifyActivity;
import com.utoo.chunguanyouli.ui.shopping.shoppingcar.ShoppingcarActivitySingle;

public abstract class PersonalFg implements OnClickListener {
	MainActivity ctx;
	MyApplication app;
	UUserInfoId user;

	public PersonalFg(MainActivity ctx) {
		this.ctx = ctx;
		this.app = (MyApplication) ctx.getApplication();
		user = ctx.getUser1();
		findViews();
		bu = new BitmapUtils(ctx, ctx.getExternalCacheDir().getPath() + "/cgyl");
		if (user != null) {
			setUserInfo();
		}

	}

	String nameTemp = null;

	public void setNewUserName() {
		this.personalNickname.setText(nameTemp == null ? user.getRealname()
				: nameTemp);
	}

	public com.views.CircleImageView personalHead;
	public TextView personalNickname;
	public LinearLayout myorder;
	public RadioButton myorderNoPay;
	public RadioButton myorderNoAppend;
	public RadioButton myorderWaitCollect;
	public RadioButton myorderFinish;
	public RadioButton myorderReturn;
	public TextView openShop;
	public TextView leaderRegist;
	public TextView myContry;
	public TextView shopping;
	public TextView mycollection;
	public TextView shoppingcar;
	public TextView setting;
	public TextView myNews;

	public TextView what;// 管理村子
	public Button switchUser;
	/**
	 * Find the Views in the layout<br />
	 * <br />
	 * Auto-created on 2015-06-02 18:11:42 by Android Layout Finder
	 * (http://www.buzzingandroid.com/tools/android-layout-finder)
	 */
	BitmapUtils bu;

	public void setUserInfo() {
		user = ctx.getUser1();
		if (user != null) {
			personalNickname.setText(user.getRealname() == null
					|| user.getRealname().equals("") ? user.getUsername()
					: user.getRealname());
			Picasso.with(ctx).load(user.getPhoto()).error(R.drawable.index)
					.into(personalHead);
			if (user.getTid() == 1) {
				// openShop.setVisibility(View.VISIBLE);
				// myNews.setVisibility(View.VISIBLE);
				what.setVisibility(View.VISIBLE);
			} else {
				openShop.setVisibility(View.GONE);
				myNews.setVisibility(View.GONE);
				what.setVisibility(View.GONE);
			}
		} else {
			// Toast.makeText(ctx, "登陆已失效", Toast.LENGTH_SHORT).show();
		}
		
	}

	public void findViews() {
		personalHead = (com.views.CircleImageView) ctx
				.findViewById(R.id.personal_head);
		personalNickname = (TextView) ctx.findViewById(R.id.personal_nickname);
		myorder = (LinearLayout) ctx.findViewById(R.id.myorder);
		myorderNoPay = (RadioButton) ctx.findViewById(R.id.myorder_noPay);
		myorderNoAppend = (RadioButton) ctx.findViewById(R.id.myorder_noAppend);
		myorderWaitCollect = (RadioButton) ctx
				.findViewById(R.id.myorder_waitCollect);
		myorderFinish = (RadioButton) ctx.findViewById(R.id.myorder_finish);
		myorderReturn = (RadioButton) ctx.findViewById(R.id.myorder_return);
		openShop = (TextView) ctx.findViewById(R.id.openShop);
		leaderRegist = (TextView) ctx.findViewById(R.id.leaderRegist);
		myContry = (TextView) ctx.findViewById(R.id.myContry);
		shopping = (TextView) ctx.findViewById(R.id.shopping);
		mycollection = (TextView) ctx.findViewById(R.id.mycollection);
		shoppingcar = (TextView) ctx.findViewById(R.id.shoppingcar);
		setting = (TextView) ctx.findViewById(R.id.setting);
		myNews = (TextView) ctx.findViewById(R.id.myNews);
		what = (TextView) ctx.findViewById(R.id.what);
		switchUser = (Button) ctx.findViewById(R.id.switchPd);
		personalNickname.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder;
				builder = new AlertDialog.Builder(ctx);
				View view = ctx.getLayoutInflater().inflate(
						R.layout.dialog_update_name, null);
				builder.setView(view);
				final EditText editText = (EditText) view
						.findViewById(R.id.content);
				builder.setTitle("修改用户名");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								nameTemp = editText.getText().toString().trim();
								user.setRealname(editText.getText().toString()
										.trim());
								ctx.apply(API_F.updatePersonal(user, 1));
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				builder.show();
			}
		});

		personalHead.setOnClickListener(this);
		myorder.setOnClickListener(this);
		openShop.setOnClickListener(this);
		leaderRegist.setOnClickListener(this);
		myContry.setOnClickListener(this);
		shopping.setOnClickListener(this);
		mycollection.setOnClickListener(this);
		shoppingcar.setOnClickListener(this);
		setting.setOnClickListener(this);
		myNews.setOnClickListener(this);
		myorderNoPay.setOnClickListener(this);
		myorderNoAppend.setOnClickListener(this);
		myorderWaitCollect.setOnClickListener(this);
		myorderFinish.setOnClickListener(this);
		what.setOnClickListener(this);
		myorderReturn.setOnClickListener(this);
		switchUser.setOnClickListener(this);
		if (user.getTid() == 1) {
			// openShop.setVisibility(View.VISIBLE);
			// myNews.setVisibility(View.VISIBLE);
			what.setVisibility(View.VISIBLE);
		} else {
			openShop.setVisibility(View.GONE);
			myNews.setVisibility(View.GONE);
			what.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		if (v == myorderNoPay) {
			Intent intent = new Intent();
			intent.setClass(ctx, OrderListActivity.class);
			intent.putExtra("type", CgOrderId.TYPE_NO_PAY);
			ctx.startActivity(intent);
		} else if (v == myorderNoAppend) {
			Intent intent = new Intent();
			intent.setClass(ctx, OrderListActivity.class);
			intent.putExtra("type", CgOrderId.TYPE_WAIT_APPEND);
			ctx.startActivity(intent);
		} else if (v == myorderWaitCollect) {
			Intent intent = new Intent();
			intent.setClass(ctx, OrderListActivity.class);
			intent.putExtra("type", CgOrderId.TYPE_WAIT_COLLECT);
			ctx.startActivity(intent);
		} else if (v == myorderFinish) {
			Intent intent = new Intent();
			intent.setClass(ctx, OrderListActivity.class);
			intent.putExtra("type", CgOrderId.TYPE_WAIT_COMMEND);
			ctx.startActivity(intent);
		} else if (v == myorderReturn) {
			// Handle clicks for myorderReturn
		} else if (v == mycollection) {
			Intent intent = new Intent();
			intent.setClass(ctx, MyCollectionGoodsActivity.class);
			ctx.startActivity(intent);
		} else if (v == myContry) {
			// Handle clicks for myorderReturn
			Intent intent = new Intent();
			// intent.setClass(ctx, VillagesIntroduceActivity.class);
			intent.setClass(ctx, MyCollectionContryActivity.class);
			ctx.startActivity(intent);
		} else if (v == leaderRegist) {
			// Intent intent = new Intent();
			// intent.setClass(ctx, OfficerUpgradeActivity.class);
			// ctx.startActivity(intent);
		} else if (v == openShop) {
			Intent intent = new Intent();
			intent.setClass(ctx, MyShopActivity.class);
			ctx.startActivity(intent);
		} else if (v == myorder) {
			Intent intent = new Intent();
			intent.setClass(ctx, OrderListActivity.class);
			intent.putExtra("type", CgOrderId.TYPE_ALL);
			ctx.startActivity(intent);
		} else if (v == personalHead) {
			// Intent intent = new Intent();
			// intent.setClass(ctx, UpdatepersonalInfoActivity.class);
			// ctx.startActivity(intent);
			headClick();
		} else if (v == setting) {
			// Intent intent = new Intent();
			// intent.setClass(ctx, SettingActivity.class);
			// ctx.startActivity(intent);
		} else if (v == shopping) {
			Intent intent = new Intent();
			intent.setClass(ctx, GoodsClassifyActivity.class);
			ctx.startActivity(intent);
		} else if (v == shoppingcar) {
			Intent intent = new Intent();
			intent.setClass(ctx, ShoppingcarActivitySingle.class);
			ctx.startActivity(intent);
		} else if (v == myNews) {
			Intent intent = new Intent();
			intent.setClass(ctx, VillageDropsActivity.class);
			intent.putExtra("action", 1);
			ctx.startActivity(intent);
		} else if (v == what) {
			Intent intent = new Intent();
			intent.setClass(ctx, VillageManagerActivity.class);
			ctx.startActivity(intent);
		} else if (v == switchUser) {
			Intent intent = new Intent();
			intent.setClass(ctx, LoginActivity.class);
			ctx.startActivity(intent);
			// ctx.finish();
			ctx.app.user = null;
		}
	}

	public void setHead(String path) {
		if (path.startsWith("http")) {
			Picasso.with(ctx).load(path).into(personalHead);
		} else {
			Picasso.with(ctx).load(new File(path)).into(personalHead);
		}

	}

	public abstract void headClick();
}
