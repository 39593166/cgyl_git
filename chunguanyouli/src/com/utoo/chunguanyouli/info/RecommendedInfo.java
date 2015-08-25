package com.utoo.chunguanyouli.info;

import java.util.List;

public class RecommendedInfo {

	private String state;
	private String sid;
	private String num;
	private List<Val> val;

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSid() {
		return this.sid;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getNum() {
		return this.num;
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
		private String effect;
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

		public void setEffect(String effect) {
			this.effect = effect;
		}

		public String getEffect() {
			return this.effect;
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
