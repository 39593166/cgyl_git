package com.utoo.chunguanyouli.ui.personal.cg.dianpu.goodsmanner;

import java.util.ArrayList;

import com.utoo.chunguanyouli.entity.Classify;

public class PostGoods {
	private String goodsTitle;
	private String gooesImageFilePath;
	private float price;
	private int store;
	private float append;
	private String des;
	private String appendSpace;
	private ArrayList<String> desFiles;
	private Classify type;
	

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getGooesImageFilePath() {
		return gooesImageFilePath;
	}

	public void setGooesImageFilePath(String gooesImageFilePath) {
		this.gooesImageFilePath = gooesImageFilePath;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}

	public float getAppend() {
		return append;
	}

	public void setAppend(float append) {
		this.append = append;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getAppendSpace() {
		return appendSpace;
	}

	public void setAppendSpace(String appendSpace) {
		this.appendSpace = appendSpace;
	}

	public ArrayList<String> getDesFiles() {
		return desFiles;
	}

	public void setDesFiles(ArrayList<String> desFiles) {
		this.desFiles = desFiles;
	}

	public Classify getType() {
		return type;
	}

	public void setType(Classify type) {
		this.type = type;
	}

}
