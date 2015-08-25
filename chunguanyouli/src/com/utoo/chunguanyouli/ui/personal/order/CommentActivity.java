package com.utoo.chunguanyouli.ui.personal.order;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.google.gson.JsonObject;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.R.id;
import com.utoo.chunguanyouli.R.layout;
import com.utoo.chunguanyouli.R.menu;
import com.utoo.chunguanyouli.dbentity.CgReplyId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.tool.DateTool;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;
import com.utoo.chunguanyouli.ui.base.NetDataBaseActionBarActivity;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsCommendActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CommentActivity extends NetDataBaseActionBarActivity implements OnClickListener {
	private static final int TAG_ADDREP = 1;
	int goodsId;
	EditText text;
	Button submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNeedLogin(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		goodsId = getIntent().getIntExtra("goodsid", 0);
		initToolbar(getTitle().toString());
		init();
	}
	private void init(){
		this.text = (EditText) this.findViewById(R.id.replyContent);
		this.submit = (Button) this.findViewById(R.id.replySubmit);
		submit.setOnClickListener(this);
	}
	private void addReply(String content){
		CgReplyId rep = new CgReplyId();
		rep.setReuid(goodsId);
		rep.setContent(content);
		rep.setUid(userNeed.getId());
		rep.setUname(userNeed.getRealname());
		rep.setDatesend(DateTool.getTimeStr(System.currentTimeMillis(),
				"yyyy-MM-dd HH:mm:ss"));
		apply(API_F.addReply( TAG_ADDREP,userNeed.getId(),userNeed.getSid(),rep));
	}
	@Override
	protected void NetFailed(VolleyError error, int TAG) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onReturned(String response, int TAG) {
		// TODO Auto-generated method stub
		try {
			JSONObject jo = new JSONObject(response);
			int state = jo.optInt("state");
			if(state == 1){
//				Intent intent = new Intent();
//				intent.setClass(this, GoodsCommendActivity.class);
//				intent.putExtra("goodsId", goodsId);
//				startActivity(intent);
				Toast.makeText(this, "您的评论已提交审核", 0).show()	;
				this.finish();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == submit){
			String content = text.getText().toString();
			addReply(content);
		}
	}
}
