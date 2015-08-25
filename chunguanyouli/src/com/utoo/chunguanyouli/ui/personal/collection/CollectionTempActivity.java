package com.utoo.chunguanyouli.ui.personal.collection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;

/**
 * 我的收藏中转
 * 
 * @author fsm
 * 
 */
public class CollectionTempActivity extends BaseActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection_temp);
	}

	public void collectionGoods(View v) {
		Intent intent = new Intent();
		intent.setClass(this, MyCollectionGoodsActivity.class);
		startActivity(intent);
	}

	public void collectionContry(View v) {
		Intent intent = new Intent();
		intent.setClass(this, MyCollectionContryActivity.class);
		startActivity(intent);
	}
}
