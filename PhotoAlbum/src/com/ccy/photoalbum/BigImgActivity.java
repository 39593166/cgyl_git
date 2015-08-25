package com.ccy.photoalbum;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

//@ContentView(R.layout.activity_big_img)
public class BigImgActivity extends Activity {

	private Matrix matrix = new Matrix();
	private Matrix savedMatrix = new Matrix();

	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;

	// Remember some things for zooming
	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist = 1f;

	private Picasso picasso;

//	@ViewInject(R.id.icon)
	private ImageView icon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_big_img);
//		ViewUtils.inject(this);
		picasso = Picasso.with(this);

		String path = getIntent().getStringExtra("path");
		Bitmap bitmap = BitmapFactory.decodeFile(path);
		icon = (ImageView) this.findViewById(R.id.icon);
		icon.setImageBitmap(bitmap);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			matrix.set(icon.getImageMatrix());
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			mode = DRAG;
			break;
		case MotionEvent.ACTION_POINTER_DOWN: // 多点触控
			oldDist = this.spacing(event);
			if (oldDist > 10f) {
				savedMatrix.set(matrix);
				midPoint(mid, event);
				mode = ZOOM;
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mode == DRAG) { // 此实现图片的拖动功能...
				matrix.set(savedMatrix);
				matrix.postTranslate(event.getX() - start.x, event.getY()
						- start.y);
			} else if (mode == ZOOM) {// 此实现图片的缩放功能...
				float newDist = spacing(event);
				if (newDist > 10) {
					matrix.set(savedMatrix);
					float scale = newDist / oldDist;
					matrix.postScale(scale, scale, mid.x, mid.y);
				}
			}
			break;
		}
		icon.setImageMatrix(matrix);
		return false;

	}

	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}

}
