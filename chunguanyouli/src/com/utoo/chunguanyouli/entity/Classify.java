package com.utoo.chunguanyouli.entity;

import java.io.Serializable;

public class Classify implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8211200330854264968L;
	private String classifyId;
	private String classifyName;
	private String classifyImage;

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


}
