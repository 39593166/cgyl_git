package com.utoo.chunguanyouli.ui.personal.cg.collage;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

public class MyCollageManagerActivity extends NetDataBaseActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_collage_manager);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.my_collage_manager, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		
	}
}
