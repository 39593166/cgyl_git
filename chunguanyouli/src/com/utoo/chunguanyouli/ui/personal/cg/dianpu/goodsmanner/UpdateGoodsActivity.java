package com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.ccy.photoalbum.PhotoAlbumActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.CgTypeId;
import com.utoo.chunguanyouli.dbentity.Pics;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.DateTool;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.comm.AddressChooseActivity;
import com.utoo.chunguanyouli.ui.shopping.GoodsClassifyActivity;
import com.utoo.chunguanyouli.views.APPManager;
import com.utoo.chunguanyouli.views.ActionSheet;
import com.utoo.chunguanyouli.views.ActionSheet.ActionSheetListener;
import com.views.NoScrollGridView;

/**
 * 修改商品信息
 * 
 * @author fsm
 */
public class UpdateGoodsActivity extends NetDataBaseActionBarActivity implements
		OnClickListener, ActionSheetListener, OnItemLongClickListener {
	CgGoodsId goods;

	private static final int TAG_UPDATE = 5;
	private static final int TAG_TYPEINFO = 10;
	private static final int TAG_UPLOADPIC1 = 1;
	private static final int TAG_UPLOADPIC2 = 2;
	private static final int TAG_UPLOADPIC3 = 3;

	private static final int TAG_ADD_PIC = 6;
	private static final int TAG_DELETE_PIC = 8;

	private static final int TAG_GETINFO = 9;

	final int RESULTCODE_APPENDSPACE = 3824;
	final int RESULTCODE_GOODSTYPE = 3254;
	String dirPath;
	String fileName;

	// 商品信息组合
	// CgTypeId topType = null;
	CgTypeId childType = null;

	int filetag = 1;// 1:pic1,2:pic2,3:pic3;
	List<Object> goodsFiles;
	List<Object> personalFiles;
	List<Object> areaFiles;

	int picsum = 0;

	int delettingPicPos = 0;
	boolean isfinishinfoupdate = false;
	boolean isfinishpicupload = false;
	boolean haveFailed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_goods);
		goods = (CgGoodsId) getIntent().getSerializableExtra("goods");

		goodsFiles = new ArrayList<Object>();
		personalFiles = new ArrayList<Object>();
		areaFiles = new ArrayList<Object>();

		adapter1 = new MyAdapter(goodsFiles, 1);
		adapter2 = new MyAdapter(personalFiles, 2);
		adapter3 = new MyAdapter(areaFiles, 3);

		dirPath = getExternalCacheDir() + "/cgyl/";
		File dir = new File(dirPath);
		if (dir.exists()) {
			dir.mkdirs();
		}
		findViews();
		initToolbar("商品修改");
		getGoodsInfo();
		getTypeInfo();
	}

	private void getTypeInfo() {
		apply(API_F.getTypeInfo(TAG_TYPEINFO, goods.getTid()));
	}

	/**
	 * 获取商品基本信息
	 */
	private void getGoodsInfo() {
		apply(API_F.getGoodsInfo(goods.getId(), TAG_GETINFO));
	}

	/**
	 * 上传图片
	 * 
	 * @param tag
	 */
	private void uploadFiles() {
		picsum = 0;
		for (Object filePath : goodsFiles) {
			if (filePath instanceof String) {
				picsum++;
			}
		}
		for (Object filePath : personalFiles) {
			if (filePath instanceof String) {
				picsum++;
			}
		}
		for (Object filePath : areaFiles) {
			if (filePath instanceof String) {
				picsum++;
			}
		}
		if (picsum == 0) {
			isfinishpicupload = true;
			return;
		}
		for (Object filePath : goodsFiles) {
			if (filePath instanceof String) {
				uploadImage(userNeed.getId(), (String) filePath, TAG_UPLOADPIC1);
			}
		}
		for (Object filePath : personalFiles) {
			if (filePath instanceof String) {
				uploadImage(userNeed.getId(), (String) filePath, TAG_UPLOADPIC2);
			}
		}
		for (Object filePath : areaFiles) {
			if (filePath instanceof String) {
				uploadImage(userNeed.getId(), (String) filePath, TAG_UPLOADPIC3);
			}
		}
	}

	/**
	 * 添加商品图片信息
	 */
	private void addGoodsPics(String path, int tid) {
		Pics pics = new Pics();
		pics.setGid(goods.getId());
		pics.setTid(tid);
		pics.setUrl(path);
		apply(API_F.addGoodsPic(TAG_ADD_PIC, pics), false);
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		if (TAG == TAG_UPLOADPIC1 || TAG == TAG_UPLOADPIC2
				|| TAG == TAG_UPLOADPIC3) {
			haveFailed = true;
			picsum--;
			Log.e("picsumReturnFail", picsum + "");
			if (picsum == 0) {
				isfinishpicupload = true;
				Toast.makeText(this, "图片添加超时", Toast.LENGTH_SHORT).show();
			}
		}
		if (isfinishinfoupdate & isfinishpicupload)
			enableSubmit(true);
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
					isfinishinfoupdate = true;
					if (!haveFailed & isfinishinfoupdate & isfinishpicupload) {
						enableSubmit(true);
						Toast.makeText(this, "上传成功", 0).show();
						this.finish();
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if (TAG == TAG_DELETE_PIC) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0) {
					Toast.makeText(this, "删除超时", Toast.LENGTH_SHORT).show();
					return;
				} else {
					if (delettingType == 1) {
						goodsFiles.remove(delettingPicPos);
						adapter1.notifyDataSetChanged();
						// adapter1.remove(delettingPicPos);
					} else if (delettingType == 2) {
						personalFiles.remove(delettingPicPos);
						adapter2.notifyDataSetChanged();
						// adapter2.remove(delettingPicPos);
					} else if (delettingType == 3) {
						areaFiles.remove(delettingPicPos);
						adapter3.notifyDataSetChanged();
						// adapter3.remove(delettingPicPos);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if (TAG == TAG_UPLOADPIC1 || TAG == TAG_UPLOADPIC2
				|| TAG == TAG_UPLOADPIC3) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0)
					return;
				else {

					Log.e("picsumReturnSuc", picsum + "");
					String path = jo.optString("path");
					addGoodsPics(path, TAG);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if (TAG == TAG_ADD_PIC) {
			picsum--;
			if (picsum == 0) {
				isfinishpicupload = true;
			}

			if (!haveFailed & isfinishinfoupdate & isfinishpicupload) {
				enableSubmit(true);
				Toast.makeText(this, "上传成功", 0).show();
				this.finish();
			}
		} else if (TAG == TAG_GETINFO) {
			Gson gson = new Gson();
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			if (cr.getValNum() == 0) {
				return;
			}
			List<CgGoodsId> goodsList = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgGoodsId>>() {
					}.getType());
			if (goodsList != null && goodsList.size() > 0) {
				this.goods = goodsList.get(0);
				initViewsShow();
			}
		} else if (TAG == TAG_TYPEINFO) {
			Gson gson = new Gson();
			CgResponse cr = new CgResponse(response);
			if (cr.getState() == 0) {
				return;
			}
			if (cr.getValNum() == 0) {
				return;
			}
			List<CgTypeId> typeliset = gson.fromJson(cr.getValStr(),
					new TypeToken<List<CgTypeId>>() {
					}.getType());
			if (typeliset != null && typeliset.size() > 0) {
				this.childType = typeliset.get(0);
				this.updateGoodsType.setText(childType.getName());
				initViewsShow();
			}
		}
	}

	private void enableSubmit(boolean isEnable) {
		closeProgressDialog();
		updateGoodsSubmit.setEnabled(isEnable);
	}

	private void initViewsShow() {
		updateGoodsTitle.setText(goods.getName());
		updateGoodsDW.setText(goods.getPack() == null ? "" : goods.getPack());
		updateGoodsPrice.setText("" + goods.getPrice());
		updateGoodsFarmer.setText(goods.getFromuser());
		updateGoodsStore.setText("" + goods.getNumhave());
		updateGoodsDes.setText(goods.getContent());
		Picasso.with(this).load(goods.getPic())
				.error(R.drawable.index_goods_list).into(updateGoodsMainImage);
		if (goods.getPic1() != null) {
			Log.e("pic1size", goods.getPic1().size() + "");
			adapter1.addPicDatas(goods.getPic1());
			Log.e("pic1size", adapter1.getCount() + "");
		}
		if (goods.getPic2() != null) {
			Log.e("pic2size", goods.getPic2().size() + "");
			adapter2.addPicDatas(goods.getPic2());
			Log.e("pic2size", adapter2.getCount() + "");
		}
		if (goods.getPic3() != null) {
			Log.e("pic3size", goods.getPic3().size() + "");
			adapter3.addPicDatas(goods.getPic3());
			Log.e("pic3size", adapter3.getCount() + "");
		}
	}

	private Button updateGoodsImage;
	private NoScrollGridView updateGoodsImageList;
	private Button addPersonImage;
	private NoScrollGridView addPersonImageList;
	private Button addAreaImage;
	private NoScrollGridView addAreaImageList;
	private EditText updateGoodsTitle;
	private TextView updateGoodsType;
	private EditText updateGoodsFarmer;
	private EditText updateGoodsDW;
	private EditText updateGoodsPrice;
	private EditText updateGoodsStore;
	private EditText updateGoodsAppend;
	private EditText updateGoodsDes;
	private Button updateGoodsAppendSpace;
	private Button updateGoodsSubmit;
	private ImageView updateGoodsMainImage;

	private void findViews() {
		updateGoodsMainImage = (ImageView) findViewById(R.id.updateGoodsMainImage);
		updateGoodsDW = (EditText) findViewById(R.id.updateGoodsDW);
		updateGoodsImage = (Button) findViewById(R.id.updateGoodsImage);
		updateGoodsImageList = (NoScrollGridView) findViewById(R.id.updateGoodsImageList);
		addPersonImage = (Button) findViewById(R.id.addPersonImage);
		addPersonImageList = (NoScrollGridView) findViewById(R.id.addPersonImageList);
		addAreaImage = (Button) findViewById(R.id.addAreaImage);
		addAreaImageList = (NoScrollGridView) findViewById(R.id.addAreaImageList);
		updateGoodsTitle = (EditText) findViewById(R.id.updateGoodsTitle);
		updateGoodsType = (TextView) findViewById(R.id.updateGoodsType);
		updateGoodsFarmer = (EditText) findViewById(R.id.updateGoodsFarmer);
		updateGoodsPrice = (EditText) findViewById(R.id.updateGoodsPrice);
		updateGoodsStore = (EditText) findViewById(R.id.updateGoodsStore);
		updateGoodsAppend = (EditText) findViewById(R.id.updateGoodsAppend);
		updateGoodsDes = (EditText) findViewById(R.id.updateGoodsDes);
		updateGoodsAppendSpace = (Button) findViewById(R.id.updateGoodsAppendSpace);
		updateGoodsSubmit = (Button) findViewById(R.id.updateGoodsSubmit);

		updateGoodsImage.setOnClickListener(this);
		addPersonImage.setOnClickListener(this);
		addAreaImage.setOnClickListener(this);
		updateGoodsAppendSpace.setOnClickListener(this);
		updateGoodsSubmit.setOnClickListener(this);
		updateGoodsType.setOnClickListener(this);
		updateGoodsMainImage.setOnClickListener(this);
		addAreaImageList.setOnItemLongClickListener(this);
		addPersonImageList.setOnItemLongClickListener(this);
		updateGoodsImageList.setOnItemLongClickListener(this);
		addAreaImageList.setAdapter(adapter3);
		addPersonImageList.setAdapter(adapter2);
		updateGoodsImageList.setAdapter(adapter1);
	}

	@Override
	public void onClick(View v) {
		if (v == updateGoodsImage) {
			// Handle clicks for updateGoodsImage
			filetag = 1;
			fileName = System.currentTimeMillis() + ".jpg";
			ActionSheet.createBuilder(this, getSupportFragmentManager())
					.setOtherButtonTitles("拍照", "从相册选择")
					.setCancelButtonTitle("取消")
					.setCancelableOnTouchOutside(true).setListener(this).show();
		} else if (v == addPersonImage) {
			// Handle clicks for addPersonImage
			filetag = 2;
			fileName = System.currentTimeMillis() + ".jpg";
			ActionSheet.createBuilder(this, getSupportFragmentManager())
					.setOtherButtonTitles("拍照", "从相册选择")
					.setCancelButtonTitle("取消")
					.setCancelableOnTouchOutside(true).setListener(this).show();
		} else if (v == addAreaImage) {
			filetag = 3;
			fileName = System.currentTimeMillis() + ".jpg";
			ActionSheet.createBuilder(this, getSupportFragmentManager())
					.setOtherButtonTitles("拍照", "从相册选择")
					.setCancelButtonTitle("取消")
					.setCancelableOnTouchOutside(true).setListener(this).show();
		} else if (v == updateGoodsAppendSpace) {
			Intent intent = new Intent();
			intent.setClass(this, AddressChooseActivity.class);
			startActivityForResult(intent, RESULTCODE_APPENDSPACE);
		} else if (v == updateGoodsSubmit) {
			showProgressDialog("正在上传，请稍等");
			enableSubmit(false);

			updateGoodsInfo();
		} else if (v == updateGoodsType) {
			Intent intent = new Intent();
			intent.setClass(this, GoodsClassifyActivity.class);
			intent.putExtra("where", 1);
			startActivityForResult(intent, 1000);
		} else if (v == updateGoodsMainImage) {
			if (goods == null) {
				Toast.makeText(this, "未加载商品信息", 0).show();
				return;
			}
			Intent intent = new Intent();
			intent.setClass(this, UpdateGoodsMainImgActivity.class);
			intent.putExtra("goods", goods);
			startActivityForResult(intent, 1001);
		}
	}

	MyAdapter adapter1;
	MyAdapter adapter2;
	MyAdapter adapter3;

	int delettingType = 0;

	class MyAdapter extends BaseAdapter {
		class ViewHolder {
			ImageView image;
		}

		int type;
		List<Object> files;

		public MyAdapter(List<Object> files, int type) {
			this.files = files;
			this.type = type;
		}

		public void addPicDatas(List<Pics> pic1) {
			files.addAll(pic1);
			this.notifyDataSetChanged();
		}

		public void addStringDatas(List<String> os) {
			files.addAll(os);
			this.notifyDataSetChanged();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			Object item = files.get(position);
			if (convertView == null) {
				convertView = LayoutInflater.from(UpdateGoodsActivity.this)
						.inflate(R.layout.item_add_goods_image, null);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_add_goods_imageview);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (item instanceof Pics) {
				Picasso.with(UpdateGoodsActivity.this)
						.load(((Pics) item).getUrl())
						.resizeDimen(R.dimen.image_size_list,
								R.dimen.image_size_list)
						.error(R.drawable.index_goods_list)
						.placeholder(R.drawable.index_goods_list)
						.into(holder.image);
			} else if (item instanceof String) {
				Picasso.with(UpdateGoodsActivity.this)
						.load(new File((String) item))
						.resizeDimen(R.dimen.image_size_list,
								R.dimen.image_size_list).into(holder.image);
			}

			return convertView;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public Object getItem(int position) {
			return files.get(position);
		}

		@Override
		public int getCount() {
			return files.size();
		}
	}

	/**
	 * 修改商品信息
	 */
	private void updateGoodsInfo() {

		String name = updateGoodsTitle.getText().toString().trim();

		int typeId = 0;
		// if (childType == null && topType != null) {
		// typeId = topType.getId();
		// } else if (childType != null && topType != null) {
		typeId = childType.getId();
		// }
		String priceStr = updateGoodsPrice.getText().toString().trim();
		String danwei = updateGoodsDW.getText().toString();
		String storeStr = updateGoodsStore.getText().toString().trim();
		String priceAppendStr = updateGoodsAppend.getText().toString().trim();
		String updateGoodsFarmerStr = updateGoodsFarmer.getText().toString()
				.trim();
		String des = updateGoodsDes.getText().toString().trim();
		String area = updateGoodsAppendSpace.getText().toString().trim();
		if (TextUtils.isEmpty(name) || typeId == 0
				|| TextUtils.isEmpty(priceStr) || TextUtils.isEmpty(storeStr)) {
			Toast.makeText(this, "请完善商品信息", 0).show();
			enableSubmit(true);
			return;
		}

		double price = Double.parseDouble(priceStr);
		int store = Integer.parseInt(storeStr);
		// double priceAppend = Double.parseDouble(priceAppendStr);

		goods.setTid(typeId);
		goods.setName(name);
		goods.setPrice(price);
		goods.setPack(danwei);
		goods.setNumhave(store);
		goods.setIslock(false);
		goods.setUid(userNeed.getId());
		goods.setCid(userNeed.getCid());
		goods.setContent(des);
		goods.setFromuser(updateGoodsFarmerStr);
		goods.setDatesend(DateTool.getTimeStr(System.currentTimeMillis(),
				"yyyy-MM-dd HH:mm:ss"));
		showProgressDialog("正在上传");
		uploadFiles();
		apply(API_F.updateGoods(goods.copy(), userNeed.getId(),
				userNeed.getSid(), goods.getId(), TAG_UPDATE), false);
	}

	/**
	 * 删除商品图片信息
	 */
	private void deletePic(Pics pic) {
		apply(API_F.deleteGoodsPic(TAG_DELETE_PIC, pic), true);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case APPManager.CAMERA:// 拍照返回
				Log.e("相机", "");
				File file = new File(dirPath + fileName);
				if (file.exists()) {
					if (filetag == 1) {
						goodsFiles.add(file.getPath());
						adapter1.notifyDataSetChanged();
					} else if (filetag == 2) {
						personalFiles.add(file.getPath());
						adapter2.notifyDataSetChanged();
					} else if (filetag == 3) {
						areaFiles.add(file.getPath());
						adapter3.notifyDataSetChanged();
					}

				} else {
					Toast.makeText(this, "拍照失败", 0).show();
				}
				break;

			case 500:// 相册返回
				// String path = APPManager.getGalleryPath(this, data);
				String path = data.getStringExtra("path");
				Log.e("相册", path);
				if (path != null) {
					if (filetag == 1) {
						goodsFiles.add(path);
						adapter1.notifyDataSetChanged();
					} else if (filetag == 2) {
						personalFiles.add(path);
						adapter2.notifyDataSetChanged();
					} else if (filetag == 3) {
						areaFiles.add(path);
						adapter3.notifyDataSetChanged();
					}

				}
				break;
			case RESULTCODE_APPENDSPACE:
				Bundle b = data.getExtras(); // data为B中回传的Intent
				String area = b.getString("area");
				this.updateGoodsAppendSpace.setText(area);
				break;
			case 1000:
				Bundle typeData = data.getExtras(); // data为B中回传的Intent
				// topType = null;
				childType = null;
				// topType = (CgTypeId) typeData.getSerializable("topType");
				childType = (CgTypeId) typeData.getSerializable("type");
				// if (topType != null && childType != null) {
				// this.updateGoodsType.setText(topType.getName() + "/"
				// + childType.getName());
				// }
				// if (topType != null && childType == null) {
				this.updateGoodsType.setText(childType.getName());
				// }
				break;
			case 1001:
				Bundle picData = data.getExtras(); // data为B中回传的Intent
				String newUrl = (String) picData.getString("pic");
				this.goods.setPic(newUrl);
				Picasso.with(this)
						.load(goods.getPic())
						.resizeDimen(R.dimen.image_size_list,
								R.dimen.image_size_list)
						.error(R.drawable.index_goods_list)
						.into(updateGoodsMainImage);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOtherButtonClick(ActionSheet actionSheet, int index) {
		switch (index) {
		case 0:// 拍照
			APPManager.openCamera(this, dirPath + fileName);
			break;

		case 1:// 从相册选择
			// APPManager.openGallery(this);
			Intent intent = new Intent(this, PhotoAlbumActivity.class);
			startActivityForResult(intent, 500);
			break;

		default:
			break;
		}
	}

	public AlertDialog.Builder builder;

	@Override
	public boolean onItemLongClick(final AdapterView<?> parent, View view,
			final int position, long id) {
		delettingPicPos = position;
		builder = new AlertDialog.Builder(this);
		builder.setTitle("确认删除此图片吗");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (parent == addAreaImageList) {
					delettingType = 3;
					Object o = adapter3.getItem(position);
					if (o instanceof Pics) {
						deletePic((Pics) o);
					} else {
						areaFiles.remove(position);
						adapter3.notifyDataSetChanged();
					}
					// adapter3.remove(position);
				} else if (parent == addPersonImageList) {
					delettingType = 2;
					// adapter2.remove(position);
					Object o = adapter2.getItem(position);
					if (o instanceof Pics) {
						deletePic((Pics) o);
					} else {
						personalFiles.remove(position);
						adapter2.notifyDataSetChanged();
					}
				} else if (parent == updateGoodsImageList) {
					delettingType = 1;
					// adapter1.remove(position);
					Object o = adapter1.getItem(position);
					if (o instanceof Pics) {
						deletePic((Pics) o);
					} else {
						goodsFiles.remove(position);
						adapter1.notifyDataSetChanged();
					}
				}
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				builder = null;
			}
		});
		builder.show();
		return false;
	};
}
