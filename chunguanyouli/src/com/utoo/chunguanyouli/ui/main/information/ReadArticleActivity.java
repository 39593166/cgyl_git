package com.utoo.chunguanyouli.ui.main.information;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

import com.android.volley.VolleyError;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
/**
 * 阅读文章
 * @author fsm
 *
 */
public class ReadArticleActivity extends NetDataBaseActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_article);
	}


	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onReturned(String response, int TAG) {
		JSONObject responseJson = null;
		try {
			responseJson = new JSONObject(response);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
