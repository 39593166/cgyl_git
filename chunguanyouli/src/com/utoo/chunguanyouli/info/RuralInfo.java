package com.utoo.chunguanyouli.info;

import java.util.List;

public class RuralInfo {

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

	public List<Val> getVals() {
		return val;
	}

	public void setVals(List<Val> val) {
		this.val = val;
	}

	public static class Val {

		private String id;
		private String pic;
		private String name;
		private String content;
		private String effect;
		private String num1;
		private String num2;
		private String num3;
		private String num4;

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

		public void setContent(String content) {
			this.content = content;
		}

		public String getContent() {
			return this.content;
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

		public void setNum4(String num4) {
			this.num4 = num4;
		}

		public String getNum4() {
			return this.num4;
		}

	}

}
