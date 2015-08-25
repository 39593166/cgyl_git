package com.utoo.chunguanyouli.ui.comm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.ui.base.BaseActionBarActivity;
import com.utoo.chunguanyouli.ui.comm.AddressChoose.ChoosedCallback;

public class AddressChooseActivity extends AppCompatActivity implements
		ChoosedCallback {

	AddressChoose ac;
	private LocationClient mLocationClient;
	private BDLocationListener mListener = new MyLocationListener();

	private TextView autoAddr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_address);
		init();

		ac = new AddressChoose(this, this);
		mLocationClient = new LocationClient(getApplicationContext());
		mLocationClient.registerLocationListener(mListener);
		mLocationClient.start();

		LocationClientOption option = new LocationClientOption();
		option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
		mLocationClient.setLocOption(option);

	}

	private void init() {
		autoAddr = (TextView) findViewById(R.id.autoAddr);
	}

	@Override
	public void chooseDone(String address) {

		if (TextUtils.isEmpty(address)) {
			Intent aintent = new Intent();
			aintent.putExtra("area", autoAddr.getText().toString().trim());
			setResult(RESULT_OK, aintent); // 这理有2个参数(int resultCode, Intent
											// intent)
			finish();
		} else {

			Intent aintent = new Intent();
			aintent.putExtra("area", address);
			setResult(RESULT_OK, aintent); // 这理有2个参数(int resultCode, Intent
											// intent)
			finish();
		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mLocationClient.stop();
	}

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			LogUtils.e("地址：" + location.getAddrStr());
			Message msg = Message.obtain();
			msg.what = 0;
			msg.obj = location.getAddrStr();
			handler.sendMessage(msg);
		}
	}

	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				autoAddr.setText((String) msg.obj);
				mLocationClient.stop();
				break;

			default:
				return false;
			}
			return true;
		}
	});
}
