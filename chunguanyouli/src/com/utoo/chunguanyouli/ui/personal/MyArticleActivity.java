package com.utoo.chunguanyouli.ui.personal;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.R.id;
import com.utoo.chunguanyouli.R.layout;
import com.utoo.chunguanyouli.R.menu;
import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.chunguanyouli.tool.CommonAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

public class MyArticleActivity extends NetDataBaseActionBarActivity {
	CommonAdapter<CgNewsId> myNewsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_article);
		init();
	}
//	PUll
	private void init() {
		
		myNewsAdapter = new CommonAdapter<CgNewsId>(this,
				R.layout.adapter_villagedrops) {

			@Override
			public void convert(ViewHolderHelper helper, CgNewsId item) {
				// TODO Auto-generated method stub
				helper.setText(R.id.title, item.getTitle());
				helper.setText(R.id.content, item.getContent());
				helper.setImageByUrl(R.id.icon, item.getPic(),
						R.drawable.index, R.drawable.index);
			}
		};
	}


	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub

	}
}
