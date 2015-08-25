package com.utoo.chunguanyouli.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlLiteHelper extends SQLiteOpenHelper {

	private static final int DB_VERSION = 11;
	private static final String DB_NAME = "cgyl";

	public SqlLiteHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql1 = "CREATE TABLE  IF NOT EXISTS car ([id] int,[info] text);";
		String sql2 = "CREATE TABLE  IF NOT EXISTS catch ([id]int,[info] text);";
		db.execSQL(sql1);
		db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropTable(db);
		onCreate(db);
	}

	private void dropTable(SQLiteDatabase db) {
		String sql = "DROP TABLE IF EXISTS " + DB_NAME;
		db.execSQL(sql);
	}

}
