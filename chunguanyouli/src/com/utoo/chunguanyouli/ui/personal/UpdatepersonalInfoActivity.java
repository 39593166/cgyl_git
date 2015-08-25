package com.utoo.chunguanyouli.ui.personal;

import android.os.Bundle;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;

public class UpdatepersonalInfoActivity extends NetDataBaseActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updatepersonal_info);
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
