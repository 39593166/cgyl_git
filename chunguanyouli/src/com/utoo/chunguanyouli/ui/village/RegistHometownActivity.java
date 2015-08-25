package com.utoo.chunguanyouli.ui.village;

/**
 * 登记我的家乡
 */
import android.os.Bundle;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;

@ContentView(R.layout.activity_registhometown)
public class RegistHometownActivity extends BaseActionBarActivity {

	// 区域
	@ViewInject(R.id.area)
	private TextView areaTextView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		String str;
		Class<String> cls = String.class;
	}

}
