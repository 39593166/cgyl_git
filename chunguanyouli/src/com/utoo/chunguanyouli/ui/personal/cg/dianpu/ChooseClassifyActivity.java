package com.utoo.chunguanyouli.ui.personal.cg.dianpu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;

/**
 * 为商品选择类型
 * 
 * @author fsm
 * 
 */
public class ChooseClassifyActivity extends BaseActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_classify);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_classify, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
