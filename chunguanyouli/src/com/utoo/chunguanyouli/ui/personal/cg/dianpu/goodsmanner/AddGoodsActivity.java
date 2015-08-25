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
import com.squareup.picasso.Picasso;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.db.CgDbHelper;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.CgTypeId;
import com.utoo.chunguanyouli.dbentity.Pics;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.tool.DateTool;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.comm.AddressChooseActivity;
import com.utoo.chunguanyouli.ui.shopping.GoodsClassifyActivity;
import com.utoo.chunguanyouli.views.APPManager;
import com.utoo.chunguanyouli.views.ActionSheet;
import com.utoo.chunguanyouli.views.ActionSheet.ActionSheetListener;
import com.views.NoScrollGridView;

/**
 * 添加商品
 * 
 * @author fsm
 * 
 */
public class AddGoodsActivity extends NetDataBaseActionBarActivity implements
		OnClickListener, ActionSheetListener, OnItemLongClickListener {
	private static final int TAG_ADD = 4;

	private static final int TAG_UPLOADPIC1 = 1;
	private static final int TAG_UPLOADPIC2 = 2;
	private static final int TAG_UPLOADPIC3 = 3;

	private static final int TAG_ADD_PIC = 6;

	// private int Action = 1;// 1修改，2添加
	private Picasso picasso;

	final int RESULTCODE_DES = 9201;
	final int RESULTCODE_APPENDSPACE = 3824;
	final int RESULTCODE_GOODSTYPE = 3254;
	String dirPath;
	String fileName;

	// 商品信息组合
	// CgTypeId topType = null;
	CgTypeId childType = null;

	int filetag = 1;// 1:pic1,2:pic2,3:pic3;
	List<String> goodsFiles;
	List<String> personalFiles;
	List<String> areaFiles;

	int picsum = 0;

	boolean isfinishinfoupdate = false;
	boolean isfinishpicupload = false;
	boolean haveFailed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_goods);

		goodsFiles = new ArrayList<String>();
		personalFiles = new ArrayList<String>();
		areaFiles = new ArrayList<String>();

		adapter1 = new MyAdapter(goodsFiles);
		adapter2 = new MyAdapter(personalFiles);
		adapter3 = new MyAdapter(areaFiles);

		goods = new CgGoodsId();
		picasso = Picasso.with(this);
		dirPath = getExternalCacheDir() + "/cgyl/";
		File dir = new File(dirPath);
		if (dir.exists()) {
			dir.mkdirs();
		}
		findViews();
		initToolbar("发布商品");
	}

	private EditText addGoodsTitle;
	private TextView addGoodsType;
	private EditText addGoodsPrice;
	private EditText addGoodsDW;
	private EditText addGoodsStore;
	private EditText addGoodsAppend;
	private EditText addGoodsDes;
	private EditText addGoodsFarmer;
	private Button addGoodsAppendSpace;
	private Button addGoodsAddStore;
	private Button addGoodsPostNow;

	private Button addGoodsImage;
	private NoScrollGridView addGoodsPicList;

	private Button addPersonImage;
	private NoScrollGridView addPersonPicList;

	private Button addAreaImage;
	private NoScrollGridView addAreaPicList;

	CgGoodsId goods = null;
	boolean isLock;

	private int initPicSum() {
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
		return picsum;
	}

	/**
	 * 上传图片
	 * 
	 * @param tag
	 */
	private void upload() {

		for (String filePath : goodsFiles) {
			uploadImage(userNeed.getId(), filePath, TAG_UPLOADPIC1);
		}
		for (String filePath : personalFiles) {
			uploadImage(userNeed.getId(), filePath, TAG_UPLOADPIC2);
		}
		for (String filePath : areaFiles) {
			uploadImage(userNeed.getId(), filePath, TAG_UPLOADPIC3);
		}
	}

	/**
	 * 添加商品信息
	 */
	private void addGoodsInfo() {
		initPicSum();
		String name = addGoodsTitle.getText().toString().trim();
		int typeId = 0;
		// if (childType == null && topType != null) {
		// typeId = topType.getId();
		// } else if (childType != null && topType != null) {
		if (childType == null) {
			Toast.makeText(this, "请完善商品信息", 0).show();
			return;
		}
		typeId = childType.getId();
		// }
		String priceStr = addGoodsPrice.getText().toString().trim();

		String storeStr = addGoodsStore.getText().toString().trim();

		// String priceAppendStr = addGoodsPrice.getText().toString().trim();
		String danwei = addGoodsDW.getText().toString();
		String des = addGoodsDes.getText().toString().trim();
		String area = addGoodsAppendSpace.getText().toString().trim();
		if (TextUtils.isEmpty(name) || typeId == 0
				|| TextUtils.isEmpty(priceStr) || TextUtils.isEmpty(storeStr)) {
			// || TextUtils.isEmpty(priceAppendStr)) {
			Toast.makeText(this, "请完善商品信息", 0).show();
			return;
		}
		if (goodsFiles.size() == 0 || personalFiles.size() == 0
				|| areaFiles.size() == 0) {
			Toast.makeText(this, "请添加图片", 0).show();
			return;
		}
		double price = Double.parseDouble(priceStr);
		int store = Integer.parseInt(storeStr);
		// double priceAppend = Double.parseDouble(priceAppendStr);
		String addGoodsFarmerStr = addGoodsFarmer.getText().toString().trim();
		goods.setTid(typeId);
		goods.setName(name);
		goods.setPrice(price);
		goods.setPricenot(0.0);
		goods.setPack(danwei);
		goods.setNumhave(store);
		goods.setIslock(isLock);
		goods.setContent(des);
		goods.setFromuser(addGoodsFarmerStr);
		goods.setDatesend(DateTool.getTimeStr(System.currentTimeMillis(),
				"yyyy-MM-dd HH:mm:ss"));
		goods.setUid(userNeed.getId());
		goods.setCid(userNeed.getCid());
		showProgressDialog("正在上传");
		apply(API_F.AadGoods(goods, userNeed.getId(), userNeed.getSid(),
				TAG_ADD), false);
		enableSubmit(false);
	}

	/**
	 * 添加到缓存
	 */
	private void addGoodsCatch() {
		String name = addGoodsTitle.getText().toString().trim();
		int typeId = 0;
		// if (childType == null && topType != null) {
		// typeId = topType.getId();
		// } else if (childType != null && topType != null) {

		// }
		if (childType == null) {
			Toast.makeText(this, "请完善商品信息", 0).show();
			return;
		}
		typeId = childType.getId();
		String priceStr = addGoodsPrice.getText().toString().trim();

		String storeStr = addGoodsStore.getText().toString().trim();

		String priceAppendStr = addGoodsPrice.getText().toString().trim();
		String danwei = addGoodsDW.getText().toString();
		String des = addGoodsDes.getText().toString().trim();
		String area = addGoodsAppendSpace.getText().toString().trim();
		if (TextUtils.isEmpty(name) || typeId == 0
				|| TextUtils.isEmpty(priceStr) || TextUtils.isEmpty(storeStr)
				|| TextUtils.isEmpty(priceAppendStr)) {
			Toast.makeText(this, "请完善商品信息", 0).show();
			return;
		}
		if (goodsFiles.size() == 0 || personalFiles.size() == 0
				|| areaFiles.size() == 0) {
			Toast.makeText(this, "请添加图片", 0).show();
			return;
		}
		double price = Double.parseDouble(priceStr);
		int store = Integer.parseInt(storeStr);
		double priceAppend = Double.parseDouble(priceAppendStr);
		String addGoodsFarmerStr = addGoodsFarmer.getText().toString().trim();
		goods.setTid(typeId);
		goods.setName(name);
		goods.setPrice(price);
		goods.setPack(danwei);
		goods.setPricenot(price);
		goods.setNumhave(store);
		goods.setIslock(isLock);
		goods.setContent(des);
		goods.setFromuser(addGoodsFarmerStr);
		goods.setDatesend(DateTool.getTimeStr(System.currentTimeMillis(),
				"yyyy-MM-dd HH:mm:ss"));
		List<Pics> pic1 = new ArrayList<Pics>();
		List<Pics> pic2 = new ArrayList<Pics>();
		List<Pics> pic3 = new ArrayList<Pics>();
		for (String path : goodsFiles) {
			pic1.add(new Pics(0, 1, path));
		}
		for (String path : personalFiles) {
			pic2.add(new Pics(0, 2, path));
		}
		for (String path : areaFiles) {
			pic3.add(new Pics(0, 3, path));
		}
		goods.setPic1(pic1);
		goods.setPic2(pic2);
		goods.setPic3(pic3);
		goods.setUid(userNeed.getId());
		goods.setCid(userNeed.getCid());
		CgDbHelper.getInstance(this).addSoftCatch(goods);
		// enableSubmit(false);
		this.finish();
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

	private void findViews() {
		addGoodsDW = (EditText) findViewById(R.id.addGoodsDW);
		addGoodsTitle = (EditText) findViewById(R.id.addGoodsTitle);
		addGoodsType = (TextView) findViewById(R.id.addGoodsType);
		addGoodsPrice = (EditText) findViewById(R.id.addGoodsPrice);
		addGoodsStore = (EditText) findViewById(R.id.addGoodsStore);
		addGoodsAppend = (EditText) findViewById(R.id.addGoodsAppend);
		addGoodsDes = (EditText) findViewById(R.id.addGoodsDes);
		addGoodsAppendSpace = (Button) findViewById(R.id.addGoodsAppendSpace);
		addGoodsImage = (Button) findViewById(R.id.addGoodsImage);
		addGoodsAddStore = (Button) findViewById(R.id.addGoodsAddStore);
		addGoodsPostNow = (Button) findViewById(R.id.addGoodsPostNow);
		addGoodsPicList = (NoScrollGridView) findViewById(R.id.addGoodsImageList);
		addGoodsAppendSpace.setOnClickListener(this);
		addGoodsImage.setOnClickListener(this);
		addGoodsAddStore.setOnClickListener(this);
		addGoodsPostNow.setOnClickListener(this);
		addGoodsType.setOnClickListener(this);
		addGoodsPicList = (NoScrollGridView) findViewById(R.id.addGoodsImageList);
		addPersonImage = (Button) findViewById(R.id.addPersonImage);
		addPersonPicList = (NoScrollGridView) findViewById(R.id.addPersonImageList);
		addAreaImage = (Button) findViewById(R.id.addAreaImage);
		addAreaPicList = (NoScrollGridView) findViewById(R.id.addAreaImageList);
		addGoodsFarmer = (EditText) findViewById(R.id.addGoodsFarmer);
		addAreaImage.setOnClickListener(this);
		addPersonImage.setOnClickListener(this);

		addGoodsPicList.setAdapter(adapter1);
		addPersonPicList.setAdapter(adapter2);
		addAreaPicList.setAdapter(adapter3);

		// 初始化商品视图信息
		// if (Action == 1) {
		// addGoodsTitle.setText(goods.getName());
		// addGoodsPrice.setText("" + goods.getPrice());
		// addGoodsStore.setText(goods.getNumhave() + "");
		// addGoodsDes.setText(goods.getContent());
		// addGoodsFarmer.setText(goods.getFromuserNeed());
		// }
		addAreaPicList.setOnItemLongClickListener(this);
		addPersonPicList.setOnItemLongClickListener(this);
		addGoodsPicList.setOnItemLongClickListener(this);

		setTheme(R.style.ActionSheetStyleIOS7);
	}

	@Override
	public void onClick(View v) {
		if (v == addGoodsAppendSpace) {
			Intent intent = new Intent();
			intent.setClass(this, AddressChooseActivity.class);
			startActivityForResult(intent, RESULTCODE_APPENDSPACE);
		} else if (v == addGoodsImage) {
			filetag = 1;
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
		} else if (v == addPersonImage) {
			filetag = 2;
			fileName = System.currentTimeMillis() + ".jpg";
			ActionSheet.createBuilder(this, getSupportFragmentManager())
					.setOtherButtonTitles("拍照", "从相册选择")
					.setCancelButtonTitle("取消")
					.setCancelableOnTouchOutside(true).setListener(this).show();
		} else if (v == addGoodsAddStore) {
			addGoodsCatch();
		} else if (v == addGoodsPostNow) {

			picsum = goodsFiles.size() + personalFiles.size()
					+ areaFiles.size();
			Log.e("picsumInit", picsum + "");
			// if (Action == 1) {
			// updateGoodsInfo();
			// } else if (Action == 2) {
			addGoodsInfo();
			// }
		} else if (v == addGoodsType) {
			Intent intent = new Intent();
			intent.setClass(this, GoodsClassifyActivity.class);
			intent.putExtra("where", 1);
			startActivityForResult(intent, 1000);
		}
	}

	private void enableSubmit(boolean isEnable) {
		addGoodsAddStore.setEnabled(isEnable);
		addGoodsPostNow.setEnabled(isEnable);
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
				this.addGoodsAppendSpace.setText(area);
				break;
			case 1000:
				// topType = null;
				childType = null;
				Bundle typeData = data.getExtras(); // data为B中回传的Intent
				// topType = (CgTypeId) typeData.getSerializable("topType");
				childType = (CgTypeId) typeData.getSerializable("type");
				// if (topType != null && childType != null) {
				// this.addGoodsType.setText(topType.getName() + "/"
				// + childType.getName());
				// }
				// if (topType != null && childType == null) {
				this.addGoodsType.setText(childType.getName());
				// }
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub
		if (TAG == TAG_UPLOADPIC1 || TAG == TAG_UPLOADPIC2
				|| TAG == TAG_UPLOADPIC3) {
			haveFailed = true;
			picsum--;
			if (picsum == 0) {
				isfinishpicupload = true;
			}

			if (!haveFailed & isfinishpicupload) {
				enableSubmit(true);
				Toast.makeText(this, "图片信息失败，可到商品管理中继续添加", 0).show();
				this.finish();
			}
		}
	}

	String netFilePath;

	@Override
	protected void onReturned(String response, int TAG) {

		if (TAG == TAG_ADD) {
			try {
				JSONObject jo = new JSONObject(response);
				int state = jo.optInt("state");
				if (state == 0) {
					Toast.makeText(this, "添加超时", Toast.LENGTH_SHORT).show();
					return;
				} else {
					int id = jo.optInt("id");
					goods.setId(id);
					upload();
				}
				// enableSubmit(true);
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
					picsum--;
					Log.e("picsumReturnSuc", picsum + "");
					String path = jo.optString("path");
					addGoodsPics(path, TAG);
					if (picsum == 0) {
						closeProgressDialog();
						enableSubmit(true);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if (TAG == TAG_ADD_PIC) {
			picsum--;
			if (picsum == 0) {
				isfinishpicupload = true;
				if (!haveFailed & isfinishpicupload) {
					enableSubmit(true);
					Toast.makeText(this, "上传成功", 0).show();
					this.finish();

				} else if (haveFailed) {
					Toast.makeText(this, "图片信息失败，可到商品管理中继续添加", 0).show();
					this.finish();
				}
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
//			APPManager.openGallery(this);
			Intent intent = new Intent(this, PhotoAlbumActivity.class);
			startActivityForResult(intent, 500);
			break;

		default:
			break;
		}
	}

	MyAdapter adapter1;
	MyAdapter adapter2;
	MyAdapter adapter3;

	class MyAdapter extends BaseAdapter {
		class ViewHolder {
			ImageView image;
		}

		List<String> files;

		public MyAdapter(List<String> files) {
			this.files = files;
		}

		public void remove(int position) {
			files.remove(position);
			this.notifyDataSetChanged();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(AddGoodsActivity.this)
						.inflate(R.layout.item_add_goods_image, null);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_add_goods_imageview);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			picasso.load(new File(files.get(position)))
					.resizeDimen(R.dimen.image_size_list,
							R.dimen.image_size_list).into(holder.image);
			return convertView;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return files.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return files.size();
		}
	}

	public AlertDialog.Builder builder;

	@Override
	public boolean onItemLongClick(final AdapterView<?> parent, View view,
			final int position, long id) {
		builder = new AlertDialog.Builder(this);
		builder.setTitle("确认删除此图片吗");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (parent == addAreaPicList) {
					adapter3.remove(position);
				} else if (parent == addPersonPicList) {
					adapter2.remove(position);
				} else if (parent == addGoodsPicList) {
					adapter1.remove(position);
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
