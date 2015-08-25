package com.ccy.photoalbum;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class PhotoAlbumActivity extends Activity implements OnItemClickListener {

	private Uri uri = Media.EXTERNAL_CONTENT_URI;// 外部存储器（SD）上的图片

	private List<PhotoAlbumInfo> list = new ArrayList<PhotoAlbumInfo>();
	private PhotoAlbumAdapter adapter;
	private Intent intent = new Intent();

	private GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_album);
		gridView = (GridView) this.findViewById(R.id.gridView);
		gridView.setOnItemClickListener(this);
		new Thread(new GetPaht()).start();

		adapter = new PhotoAlbumAdapter(this, list);
		gridView.setAdapter(adapter);
	}

	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.cancel) {
			finish();
		} else {

		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		PhotoAlbumInfo info = (PhotoAlbumInfo) adapter.getItem(position);
		intent.setClass(this, RecentPhotosActivity.class);
		intent.putExtra("flag", 0);
		intent.putStringArrayListExtra("paths", info.getArrayList());
		startActivityForResult(intent, 100);
	}

	private class GetPaht implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
			ArrayList<String> strings = new ArrayList<String>();

			Cursor cursor = getContentResolver().query(uri, null, null, null,
					null);
			while (cursor.moveToNext()) {
				String path = cursor.getString(cursor
						.getColumnIndex(MediaStore.Images.Media.DATA));// 图片路径
				String parentName = new File(path).getParentFile().getName();// 图片父类

				if (map.containsKey(parentName)) {
					map.get(parentName).add(path);
				} else {
					ArrayList<String> list = new ArrayList<String>();
					list.add(path);
					map.put(parentName, list);
				}
				strings.add(path);
			}
			cursor.close();

			map.put("最近照片", strings);

			for (Entry<String, ArrayList<String>> entry : map.entrySet()) {
				PhotoAlbumInfo info = new PhotoAlbumInfo();
				ArrayList<String> arrayList = entry.getValue();
				info.setName(entry.getKey());
				info.setCount(arrayList.size());
				info.setPath(arrayList.get(arrayList.size() - 1));
				info.setArrayList(arrayList);
				list.add(info);
			}

			Collections.sort(list);
			handler.sendEmptyMessage(0);
		}
	}

	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				adapter.notifyDataSetChanged();

				PhotoAlbumInfo info = (PhotoAlbumInfo) adapter.getItem(0);
				intent.setClass(PhotoAlbumActivity.this,
						RecentPhotosActivity.class);
				intent.putExtra("title", info.getName());
				intent.putStringArrayListExtra("paths", info.getArrayList());
				startActivityForResult(intent, 100);
				break;

			default:
				break;
			}
			return true;
		}
	});

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == 100) {
				setResult(RESULT_OK, data);
				finish();
			}
		}
	}
}
