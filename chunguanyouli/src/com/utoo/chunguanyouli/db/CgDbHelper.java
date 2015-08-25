package com.utoo.chunguanyouli.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.Pics;
import com.utoo.gson.GsonHelper;

public class CgDbHelper {
	String TAG = "db";
	private static CgDbHelper instance = null;

	private SqlLiteHelper helper;

	private SQLiteDatabase db;

	private CgDbHelper(Context context) {
		helper = new SqlLiteHelper(context);
		db = helper.getWritableDatabase();
	}

	public static CgDbHelper getInstance(Context context) {
		if (instance == null) {
			instance = new CgDbHelper(context);
		}
		return instance;
	}

	/**
	 * 添加到购物车
	 * 
	 * @param CgGoodsId
	 * @param buyNum
	 * @return
	 */
	public long addCar(CgGoodsId CgGoodsId) {
		String goodsInfo = GsonHelper.getJson(CgGoodsId).toString();

		Log.e("Car_CgGoodsId_INSERT", CgGoodsId.getId() + "");
		db.delete("car", "id=?",
				new String[] { String.valueOf(CgGoodsId.getId()) });
		ContentValues values = new ContentValues();
		values.put("id", CgGoodsId.getId());
		values.put("info", goodsInfo);
		return db.insert("car", null, values);
	}

	/**
	 * 删除购物车
	 * 
	 * @param CgGoodsIdes
	 * @param teamCgGoodsId
	 * @return
	 */
	public boolean deleteCar(List<CgGoodsId> CgGoodsIdes) {
		if (CgGoodsIdes != null)
			for (CgGoodsId CgGoodsId : CgGoodsIdes) {
				String sql = "delete from car where id = '" + CgGoodsId.getId()
						+ "'";
				db.execSQL(sql);
			}
		return true;
	}

	public List<CgGoodsId> getShoppingCar() {

		List<CgGoodsId> CgGoodsIdList = new ArrayList<CgGoodsId>();
		String sql2 = "select * from car ";
		Cursor cursor = db.rawQuery(sql2, null);
		try {
			while (cursor.moveToNext()) {
				String goodsInfo = cursor.getString(1);// 商品名

				CgGoodsId goods = GsonHelper
						.getBean(goodsInfo, CgGoodsId.class);
				CgGoodsIdList.add(goods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cursor.close();
		return CgGoodsIdList;
	}

	/**
	 * 获取购物车数量
	 * 
	 * @return
	 */
	public int getCarCount() {
		String sql = "select count(*) from car";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToFirst()) {
			return cursor.getInt(0);
		}
		return 0;
	}

	public void addSoftCatch(CgGoodsId goods) {
		int id = (int) Math.random() * 10000;
		goods.setId(id);
		String goodsInfo = GsonHelper.getJson(goods).toString();
		Log.e("catch", goodsInfo);
		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("info", goodsInfo);
		db.insert("catch", null, values);
	}

	public List<CgGoodsId> getGoodsCatch() {
		List<CgGoodsId> CgGoodsIdList = new ArrayList<CgGoodsId>();
		String sql2 = "select * from catch";
		Cursor cursor = db.rawQuery(sql2, null);
		try {
			while (cursor.moveToNext()) {
				String goodsInfo = cursor.getString(1);// 商品名
				CgGoodsId goods = GsonHelper
						.getBean(goodsInfo, CgGoodsId.class);
				CgGoodsIdList.add(goods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cursor.close();
		return CgGoodsIdList;
	}

	public boolean deleteCatch(CgGoodsId CgGoodsId) {
		String sql = "delete from catch where id = '" + CgGoodsId.getId() + "'";
		db.execSQL(sql);
		return true;
	}
}
