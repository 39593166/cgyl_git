package com.utoo.chunguanyouli.info;

import java.util.List;

public class HomeInfo {

	private String state;
	private List<Val> val;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Val> getVal() {
		return val;
	}

	public void setVal(List<Val> val) {
		this.val = val;
	}

	public static class Val {
		private String num1;
		private String num2;
		private String num3;

		public String getNum1() {
			return num1;
		}

		public void setNum1(String num1) {
			this.num1 = num1;
		}

		public String getNum2() {
			return num2;
		}

		public void setNum2(String num2) {
			this.num2 = num2;
		}

		public String getNum3() {
			return num3;
		}

		public void setNum3(String num3) {
			this.num3 = num3;
		}

	}

}
