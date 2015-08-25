package com.utoo.chunguanyouli.info;

import java.util.List;

public class VillageOfficialInfo {

	private String state;
	private String sid;
	private String num;
	private List<Val> val;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public List<Val> getVal() {
		return val;
	}

	public void setVal(List<Val> val) {
		this.val = val;
	}

	public static class Val {

		private String id;
		private String pic;
		private String name;
		private String lv;
		private String area;
		private String tj;
		private String num1;
		private String num2;
		private String num3;

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

		public String getPic() {
			return this.pic;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public void setLv(String lv) {
			this.lv = lv;
		}

		public String getLv() {
			return this.lv;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getArea() {
			return this.area;
		}

		public void setTj(String tj) {
			this.tj = tj;
		}

		public String getTj() {
			return this.tj;
		}

		public void setNum1(String num1) {
			this.num1 = num1;
		}

		public String getNum1() {
			return this.num1;
		}

		public void setNum2(String num2) {
			this.num2 = num2;
		}

		public String getNum2() {
			return this.num2;
		}

		public void setNum3(String num3) {
			this.num3 = num3;
		}

		public String getNum3() {
			return this.num3;
		}

	}

}