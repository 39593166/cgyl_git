package com.utoo.chunguanyouli.server;

import java.util.HashMap;

import org.json.JSONObject;

import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.gson.GsonHelper;

public class API_C {

	// 村官之星
	public static Request getVillageOfficial(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_USER + "?mod=tj");
		return r;
	}

	// 乡村之巅/美丽乡村
	public static Request getRural(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_CITY + "?mod=tj");
		return r;
	}

	// 优产推荐，
	public static Request getRecommended(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=tj");
		return r;
	}

	// 地区列表
	public static Request getVillagesCategory(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_CITY + "?mod=bl&t=p&pid=263");
		return r;
	}

	// 模范村镇列表
	public static Request getWalkIntoNanChuan(int TAG, int pid) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_CITY + "?mod=l&t=p&pid=" + pid);
		return r;
	}

	// 关于本镇
	public static Request getAboutVillagers(int TAG, int id) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_CITY + "?mod=l&t=id&id=" + id);
		return r;
	}

	// 本镇成员
	public static Request getVillagersPeople(int TAG, int cid) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_USER + "?mod=l&t=city&cid=" + cid);
		return r;
	}

	// 查询用户
	public static Request getUser(int TAG, int id) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_USER + "?mod=l&t=user&id=" + id);
		return r;
	}

	// 特色产品
	public static Request getFeatureProduct(int TAG, int cid) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_USER + "?mod=l&t=city&cid=" + cid);
		return r;
	}

	// 每日必读
	public static Request getDailyRequired(int TAG, int p) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_NEWS + "?mod=l&t=tj&tid=4&p=" + p);
		return r;
	}

	// 产品分类
	public static Request getProductCategories(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_TYPE + "?mod=l&t=p&pid=0");
		return r;
	}

	// 产品分类图片
	public static Request getProductCategories(int TAG, int pid) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_TYPE + "?mod=l&t=p&pid=" + pid);
		return r;
	}

	// 删除新闻
	public static Request getDelete(int TAG, int pid) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_NEWS + "?mod=d&id=" + pid);
		return r;
	}

	// 村官点滴
	public static Request getVillageDrops(int TAG, int uid, int p) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_NEWS + "?mod=l&t=user&uid=" + uid
				+ "&p=" + p);// uid=0加载所有//
		// +
		// uid);
		return r;
	}

	// 村官点滴,根据村子id查找
	/**
	 * 
	 * @param TAG
	 * @param cid
	 *            村子id
	 * @param tid
	 * @param p
	 * @return
	 */
	public static Request getVillageNews(int TAG, int cid, int tid, int p) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_NEWS + "?mod=l&t=city&cid=" + cid
				+ "&tid=" + tid + "&p=" + p);
		return r;
	}

	// 首页数据
	public static Request getHome(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_CITY + "?mod=n");
		return r;
	}

	// 类型
	public static Request getType(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_TYPE + "?mod=tj");
		return r;
	}

	public static Request postNews(CgNewsId article, int userId, String sid,
			int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_NEWS + "?mod=a&uid=" + userId + "&sid="
				+ sid);
		JSONObject jsonParam = GsonHelper.getJson(article);
		try {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> mapParam = GsonHelper.getBean(
					jsonParam.toString(), HashMap.class);
			r.getRequestParam().setParam(mapParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 首页新闻图
	 * 
	 * @param TAG
	 * @return
	 */
	public static Request getMainNews(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_INDEX + "?mod=adv&id=1");
		return r;
	}

	/**
	 * 获取版本信息
	 * 
	 * @param TAG
	 * @return
	 */
	public static Request update(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_INDEX + "?mod=v");
		return r;
	}

	/**
	 * 获取下载地址
	 * 
	 * @param TAG
	 * @return
	 */
	public static Request getDownloadUrl(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_INDEX + "?mod=d&t=a");
		return r;
	}
	/**
	 * 获取alipay支付字符串
	 * @param TAG
	 * @param orderId
	 * @param userId
	 * @param sid
	 * @return
	 * order.aspx?mod=alipay&oid=
	 */
	public static Request getAliPayInfo(int TAG, String orderStr, int userId,
			String sid) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_ORDER + "?mod=alipay");
		r.putParam("numstr", orderStr);
		r.putParam("userid", userId);
		r.putParam("sid", sid);
		return r;
	}
}
