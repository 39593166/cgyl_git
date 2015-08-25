package com.ccy.photoalbum;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.ccy.photoalbum.RecentPhotosAdapter.UserContorl;

public class RecentPhotosActivity extends Activity implements UserContorl,
		OnItemClickListener {

	private List<String> list;
	private RecentPhotosAdapter adapter;
	private SparseArray<String> array = new SparseArray<String>();
	private ArrayList<String> arrayList = new ArrayList<String>();
	private Intent intent = new Intent();

	private GridView gridView;

	private Button preview;

	private TextView titleTextView;

	private Button confirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recent_photos);
		findViews();
		Intent intent = getIntent();
		list = intent.getStringArrayListExtra("paths");
		titleTextView.setText(getIntent().getStringExtra("title"));

		adapter = new RecentPhotosAdapter(this, list, this);
		gridView.setAdapter(adapter);

	}

	private void findViews() {
		gridView = (GridView) this.findViewById(R.id.gridView);
		titleTextView = (TextView) this.findViewById(R.id.title);
		confirm = (Button) this.findViewById(R.id.confirm);
		preview = (Button) this.findViewById(R.id.preview);
		gridView.setOnItemClickListener(this);
	}

	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.preview) {
		} else if (id == R.id.confirm) {
			for (int i = 0; i < array.size(); i++) {
				arrayList.add(array.get(array.keyAt(i)));
			}
			Intent data = new Intent();
			data.putStringArrayListExtra("paths", arrayList);
			setResult(Activity.RESULT_OK, data);
			finish();
		} else if (id == R.id.photoAlbum) {
			finish();
		} else {
		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = (String) adapter.getItem(position);
		// intent.setClass(this, BigImgActivity.class);
		// intent.putExtra("path", path);
		// startActivity(intent);
		Intent data = new Intent();
		// data.putStringArrayListExtra("paths", arrayList);
		data.putExtra("path", path);
		setResult(Activity.RESULT_OK, data);
		finish();
	}

	@Override
	public void onChecked(int position, boolean isChecked) {
		if (isChecked) {
			array.put(position, list.get(position));
		} else {
			array.remove(position);
		}

		int count = array.size();
		confirm.setText("确定（" + count + "）");
		if (count > 0) {
			confirm.setEnabled(true);
			preview.setEnabled(true);
		} else {
			confirm.setEnabled(false);
			preview.setEnabled(false);
		}
	}

}
