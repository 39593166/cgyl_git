package com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.db.CgDbHelper;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.Pics;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.tool.BuilderHelper;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;

public class GoodsMannerStoreListFragment extends NetDataBaseFragment {
	CommonAdapter<CgGoodsId> fileCatchAdapter;
	Activity context;
	View pView;
	int pageIndex = 1;
	boolean isLock = false;
	private static final int TAG_ADD = 4;
	private static final int TAG_UPLOADPIC1 = 1;
	private static final int TAG_UPLOADPIC2 = 2;
	private static final int TAG_UPLOADPIC3 = 3;
	private static final int TAG_ADD_PIC = 6;

	PullToRefreshListView list;
	List<String> goodsFiles;
	List<String> personalFiles;
	List<String> areaFiles;
	CgGoodsId uploadingGoods;
	CgGoodsId uploadingGoodsCopy;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		goodsFiles = new ArrayList<String>();
		personalFiles = new ArrayList<String>();
		areaFiles = new ArrayList<String>();
		context = getActivity();
	}

	@Override
	public void onResume() {
		initGoods();
		super.onResume();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		pView = inflater.inflate(R.layout.fragment_goods_manager, container,
				false);

		initViews();
		initAdapter();
		initGoods();
		return pView;
	}

	private void initGoods() {
		List<CgGoodsId> softCatch = CgDbHelper.getInstance(context)
				.getGoodsCatch();
		list.setAdapter(fileCatchAdapter);
		fileCatchAdapter.addData(softCatch);
	}

	private void initViews() {
		list = (PullToRefreshListView) this.pView
				.findViewById(R.id.goodsManagerList);
		list.setMode(Mode.DISABLED);
		initAdapter();
	}

	private void initAdapter() {

		fileCatchAdapter = new CommonAdapter<CgGoodsId>(getActivity(),
				R.layout.item_goodsmanner_store) {

			@Override
			public void convert(ViewHolderHelper helper, final CgGoodsId item) {

				helper.setText(R.id.goodsMannerName, item.getName());
				helper.setText(R.id.goodsMannerPrice, "" + item.getPrice());
				helper.setText(R.id.goodsMannerDW, item.getPack() == null
						|| item.getPack().equals("") ? "未设置单位" : item.getPack());
				helper.setText(R.id.goodsMannerStoreSum,
						"库存:" + item.getNumhave());
				helper.setImageByUrl(R.id.goodsMannerImage, item.getPic(),
						R.drawable.index_goods_list,
						R.drawable.index_goods_list);

				OnClickListener lis = new OnClickListener() {

					@Override
					public void onClick(View v) {
						int viewId = v.getId();
						if (viewId == R.id.goodsCatchDelete) {
							// 删除
							BuilderHelper.showBuilder(context, "确认删除吗",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											uploadingGoods = item;
											deleteGoods(uploadingGoods);
										}
									});
						} else if (viewId == R.id.goodsCatchUpload) {
							// 上架
							uploadingGoodsCopy = item.copy();
							uploadingGoods = item;
							addGoodsInfo();
						} else if (viewId == R.id.goodsCatchEdit) {
							// 编辑

						}
					}
				};

				helper.setClickListener(R.id.goodsCatchDelete, lis);
				helper.setClickListener(R.id.goodsCatchUpload, lis);
				helper.setClickListener(R.id.goodsCatchEdit, lis);

			}
		};
		list.setAdapter(fileCatchAdapter);
	}

	private void deleteGoods(CgGoodsId goods) {
		CgDbHelper.getInstance(context).deleteCatch(goods);
		fileCatchAdapter.delete(goods);
	}

	int picsum = 0;

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		list.onRefreshComplete();
		if (TAG == TAG_UPLOADPIC1 || TAG == TAG_UPLOADPIC2
				|| TAG == TAG_UPLOADPIC3) {
			picsum--;
			if (picsum == 0) {
				((GoodsMannergerActivity) getActivity()).closeProgressDialog();
			}
		}
	}

	@Override
	protected void onReturned(String response, int TAG) {
		if (TAG == TAG_ADD) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0) {
					Toast.makeText(context, "添加超时", Toast.LENGTH_SHORT).show();
					return;
				} else {
					int id = jo.optInt("id");
					uploadingGoods.setId(id);
					upload();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if (TAG == TAG_UPLOADPIC1 || TAG == TAG_UPLOADPIC2
				|| TAG == TAG_UPLOADPIC3) {

			Log.e("上传图片", "" + TAG);
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0)
					return;
				else {
					String path = jo.optString("path");
					addGoodsPics(path, TAG);
				}
				picsum--;
				if (picsum == 0) {
					((GoodsMannergerActivity) getActivity())
							.closeProgressDialog();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 上传图片
	 * 
	 * @param tag
	 */
	private void upload() {
		goodsFiles.clear();
		personalFiles.clear();
		areaFiles.clear();
		if (uploadingGoods.getPic1() != null) {
			for (Pics pic : uploadingGoods.getPic1()) {
				goodsFiles.add(pic.getUrl());
			}
		}
		if (uploadingGoods.getPic2() != null) {
			for (Pics pic : uploadingGoods.getPic2()) {
				personalFiles.add(pic.getUrl());
			}
		}
		if (uploadingGoods.getPic3() != null) {
			for (Pics pic : uploadingGoods.getPic3()) {
				areaFiles.add(pic.getUrl());
			}
		}
		picsum = goodsFiles.size() + personalFiles.size() + areaFiles.size();
		for (String filePath : goodsFiles) {
			uploadImage(act.userNeed.getId(), filePath, TAG_UPLOADPIC1);
		}
		for (String filePath : personalFiles) {
			uploadImage(act.userNeed.getId(), filePath, TAG_UPLOADPIC2);
		}
		for (String filePath : areaFiles) {
			uploadImage(act.userNeed.getId(), filePath, TAG_UPLOADPIC3);
		}
	}

	/**
	 * 添加商品信息
	 */
	private void addGoodsInfo() {
		((GoodsMannergerActivity) getActivity())
				.showProgressDialog("正在上传，请勿关闭");
		apply(API_F.AadGoods(uploadingGoodsCopy, act.userNeed.getId(),
				act.userNeed.getSid(), TAG_ADD), false);
	}

	/**
	 * 添加商品图片信息
	 */
	private void addGoodsPics(String path, int tid) {
		Pics pics = new Pics();
		pics.setGid(uploadingGoods.getId());
		pics.setTid(tid);
		pics.setUrl(path);
		apply(API_F.addGoodsPic(TAG_ADD_PIC, pics), false);
	}

}
