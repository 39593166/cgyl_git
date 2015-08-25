package com.utoo.chunguanyouli.entity;

import java.util.List;

public class TopClassify {
	private String classifyId;
	private String classifyName;
	private String classifyImage;
	private List<Classify> childList;

	public String getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getClassifyImage() {
		return classifyImage;
	}

	public void setClassifyImage(String classifyImage) {
		this.classifyImage = classifyImage;
	}

	public List<Classify> getChildList() {
		return childList;
	}

	public void setChildList(List<Classify> childList) {
		this.childList = childList;
	}

}
