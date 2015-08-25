package com.utoo.chunguanyouli.server;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.utoo.chunguanyouli.dbentity.CgCityId;
import com.utoo.chunguanyouli.dbentity.CgCollectId;
import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.dbentity.CgNewsId;
import com.utoo.chunguanyouli.dbentity.CgOrderGoodsId;
import com.utoo.chunguanyouli.dbentity.CgOrderId;
import com.utoo.chunguanyouli.dbentity.CgReplyId;
import com.utoo.chunguanyouli.dbentity.CgStoreInfoId;
import com.utoo.chunguanyouli.dbentity.Pics;
import com.utoo.chunguanyouli.dbentity.UUserInfoId;
import com.utoo.gson.GsonHelper;

public class API_F {
	/**
	 * 登陆
	 * 
	 * @param userName
	 * @param password
	 * @param TAG
	 * @return
	 */
	public static Request getLoginRequest(String userName, String password,
			int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_USER + "?mod=l&t=login");
		r.putParam("user", userName);
		r.putParam("pass", password);
		return r;
	}

	/**
	 * 注册
	 * 
	 * @param userName
	 * @param password
	 * @param TAG
	 * @return
	 */
	public static Request Regist(String userName, String password, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_USER + "?mod=a");
		r.putParam("username", userName);
		r.putParam("userpass", password);
		return r;
	}

	/**
	 * 商品分类
	 * 
	 * @param pid
	 * @param TAG
	 * @return
	 */
	public static Request getGoodsType(int pid, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_TYPE + "?mod=l&t=p");
		r.putParam("pid", pid);
		return r;
	}

	/**
	 * 根据类别获取商品
	 * 
	 * @param typeid
	 * @param sortBy
	 * @param pageIndex
	 * @param des
	 * @param TAG
	 * @return
	 */
	public static Request getGoodsByType(int typeid, int sortBy, int pageIndex,
			int des, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=l&t=type");
		r.putParam("tid", typeid);
		r.putParam("by", sortBy);
		r.putParam("des", 1);
		r.putParam("p", pageIndex);
		return r;
	}

	/**
	 * 首页最新商品
	 * 
	 * @param TAG
	 * @return
	 */
	public static Request getMainNewGoods(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=l&t=type&tid=");
		return r;
	}

	/**
	 * 首页-折扣 http://121.42.138.126:82/cg/goods.aspx?mod=l&t=type&tid=&tj=0
	 * 
	 * @param TAG
	 * @return
	 */
	public static Request getMainRecommendNewGoods(int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=l&t=type&tid=&tj=1");
		return r;
	}

	/**
	 * 首页商品
	 * 
	 * @param typeid
	 * @param sortBy
	 * @param pageIndex
	 * @param des
	 * @param TAG
	 * @return
	 */
	public static Request getGoodsMain(int pageIndex, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=l&t=type");
		r.putParam("p", pageIndex);
		return r;
	}

	/**
	 * 获取商品完整
	 * 
	 * @param goodsId
	 * @param TAG
	 * @return
	 */
	public static Request getGoodsInfo(int goodsId, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=l&t=id");
		r.putParam("id", goodsId);
		return r;
	}
	/**
	 * 获取商品附加信息
	 * 
	 * @param goodsId
	 * @param TAG
	 * @return
	 */
	public static Request getGoodsExInfo(int goodsId, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_USER + "?mod=g");
		r.putParam("id", goodsId);
		return r;
	}

	/**
	 * 添加商品
	 * @param newGoods
	 * @param userId
	 * @param TAG
	 * @return
	 */
	public static Request AadGoods(CgGoodsId newGoods, int userId, String sid,
			int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=a&uid=" + userId + "&sid="
				+ sid);
		JSONObject jsonParam = GsonHelper.getJson(newGoods);
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
	 * 修改商品
	 * @param newGoods
	 * @param userId
	 * @param TAG
	 * @return
	 */
	public static Request updateGoods(CgGoodsId newGoods, int userId,
			String sid, int goodsId, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=u&id=" + goodsId + "&sid="
				+ sid);
		JSONObject jsonParam = GsonHelper.getJson(newGoods);
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
	 * 我的店铺信息
	 * 
	 * @param userId
	 * @param TAG
	 * @return
	 */
	public static Request getMyStoreInfo(int userId, String sid, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_STORE + "?mod=l&t=u");
		r.putParam("uid", userId);
		r.putParam("sid", sid);
		return r;
	}

	/**
	 * 我的店铺数据
	 * 
	 * @param userId
	 * @param TAG
	 * @return
	 */
	public static Request getMyStoreData(int userId, String sid, int cid,
			int TAG) {
		// http://121.42.138.126:82/cg/store.aspx?mod=n&t=city&cid=1
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_STORE + "?mod=n&t=city&cid=" + cid);
		r.putParam("uid", userId);
		r.putParam("sid", sid);
		return r;
	}

	// http://121.42.138.126:82/cg/store.aspx?mod=n&t=city&cid=1
	/**
	 * 修改店铺信息
	 * 
	 * @param storeinfo
	 * @param userId
	 * @param TAG
	 * @return
	 */
	public static Request updateStoreInfo(CgStoreInfoId storeinfo, int userID,
			String sid, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_STORE + "?mod=u&t=id&sid=" + sid
				+ "&uid=" + userID);
		JSONObject jsonParam = GsonHelper.getJson(storeinfo);
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
	 * 
	 * @param storeId
	 * @param state
	 *            0:仓库 1：上架 2：审核中
	 * @param TAG
	 * @return
	 */
	public static Request getMyStoreGoods( int cid,
			int state, int pageIndex, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=l&t=city");
//		r.putParam("uid", userid);
//		r.putParam("sid", sid);
		r.putParam("cid", cid);
		r.putParam("s", state);
		r.putParam("p", pageIndex);
		return r;
	}

	/**
	 * 商品评价获取
	 * 
	 * @param goodsId
	 * @param pageIndex
	 * @param TAG
	 * @return
	 */
	public static Request getGoodsReply(int goodsId, int pageIndex, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_REPLY + "?mod=l&t=g");
		r.putParam("gid", goodsId);
		r.putParam("p", pageIndex);
		return r;
	}

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @param userId
	 * @param TAG
	 * @return
	 */
	public static Request makeOrder(CgOrderId order, int userId, String sid,
			int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_ORDER + "?mod=a&uid=" + userId + "&sid="
				+ sid);
		JSONObject jsonParam = GsonHelper.getJson(order);
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

	public static Request deleteOrder(int orderId, int userId, String sid,
			int TAG) {
		// http://121.42.138.126:82/cg/order.aspx?mod=d&id=&uid=
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_ORDER + "?mod=d");
		r.putParam("id", orderId);
		r.putParam("uid", userId);
		r.putParam("sid", sid);
		return r;
	}

	/**
	 * 添加订单商品
	 * 
	 * @param goods
	 * @param userId
	 * @param TAG
	 * @return
	 */
	public static Request makeOrderGoods(CgOrderGoodsId goods, int userId,
			String sid, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_ORDERGOODS + "?mod=a&uid=" + userId
				+ "&sid=" + sid);
		JSONObject jsonParam = GsonHelper.getJson(goods);
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
	 * 订单列表
	 * 
	 * @param userId
	 * @param state
	 * @param pageIndex
	 * @param isOfficer
	 * @param TAG
	 * @return
	 */
	public static Request getOrderList(int userId, String sid, int state,
			int pageIndex, boolean isOfficer, int TAG) {
		// 2. http://121.42.138.126:82/cg/order.aspx?mod=l&t=user&uid=&state=
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		if (isOfficer) {
			r.setUrl(ClientConfigs.APIHOST_ORDER + "?mod=l&t=city");
		} else {
			r.setUrl(ClientConfigs.APIHOST_ORDER + "?mod=l&t=user");
		}
		r.putParam("uid", userId);
		r.putParam("sid", sid);
		r.putParam("p", pageIndex);
		if (state != -1) {
			r.putParam("state", state);
		}
		return r;
	}

	/**
	 * g根据id查订单
	 * 
	 * @param orderid
	 * @param userId
	 * @param TAG
	 * @return
	 */
	public static Request getOrderInfo(int orderid, int userId, String sid,
			int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_ORDER + "?mod=l&t=id");
		r.putParam("uid", userId);
		r.putParam("sid", sid);
		r.putParam("id", orderid);
		return r;
	}

	/**
	 * 获取订单商品
	 * 
	 * @param orderid
	 * @param userId
	 * @param sid
	 * @param TAG
	 * @return
	 */
	public static Request getOrderGoods(int orderid, int userId, String sid,
			int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_ORDERGOODS + "?mod=l&t=id");
		r.putParam("uid", userId);
		r.putParam("id", orderid);
		r.putParam("sid", sid);
		return r;
	}

	/**
	 * 获取收藏商品
	 * 
	 * @param userId
	 * @param pageIndex
	 * @param TAG
	 * @return
	 */
	public static Request getCollectionGoods(int userId,String sid, int pageIndex, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_COLLECTION + "?mod=l&tid=0");
		r.putParam("uid", userId);
		r.putParam("sid", sid);
		r.putParam("p", pageIndex);
		return r;
	}

	/**
	 * 获取收藏村子
	 * 
	 * @param userId
	 * @param pageIndex
	 * @param TAG
	 * @return
	 */
	public static Request getCollectionCity(int userId,String sid, int pageIndex, int TAG) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_COLLECTION + "?mod=l&tid=1");
		r.putParam("uid", userId);
		r.putParam("sid", sid);
		r.putParam("p", pageIndex);
		return r;
	}

	/**
	 * 添加收藏
	 * 
	 * @param coll
	 * @param sid
	 * @param TAG
	 * @return
	 */
	public static Request collect(CgCollectId coll, String sid, int TAG) {
		// http://121.42.138.126:82/cg/collect.aspx?mod=a&uid=7&sid=x
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_COLLECTION + "?mod=a&uid="
				+ coll.getUid() + "&sid=" + sid);
		JSONObject jsonParam = GsonHelper.getJson(coll);
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
	 * shancu收藏
	 * 
	 * @param coll
	 * @param sid
	 * @param TAG
	 * @return
	 */
	public static Request deleteCollect(int gid, int uid, String sid, int tid,
			int TAG) {
		// http://121.42.138.126:82/cg/collect.aspx?mod=d&uid=&gid=&tid=
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_COLLECTION + "?mod=d&uid=" + uid
				+ "&sid=" + sid + "&gid=" + gid + "&tid=" + tid);
		return r;
	}

	public static Request deleteGoods(int id, int tag) {
		Request r = new Request(tag);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=d");
		r.putParam("id", id);
		return r;
	}

	/**
	 * 店铺推荐商品 //http://121.42.138.126:82/cg/goods.aspx?mod=l&t=city&cid=1&utj=0
	 * 
	 * @param cid
	 * @param tAG
	 * @return
	 */
	public static Request getStoreRecommendGoods(int cid, int tAG) {
		Request r = new Request(tAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=l&t=city&utj=1");
		r.putParam("cid", cid);
		return r;
	}

	/**
	 * 根据类别查找推荐商品
	 * 
	 * @param tid
	 * @param TAG
	 * @return
	 */
	public static Request getTypeRecommendGoods(int tid, int TAG) {
		// http://121.42.138.126:82/cg/goods.aspx?mod=l&t=type&tid=4&tj=1
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=l&t=type&tj=1");
		r.putParam("tid", tid);
		return r;
	}

	/**
	 * 修改个人信息
	 * 
	 * @param user
	 * @param TAG
	 * @return
	 */
	public static Request updatePersonal(UUserInfoId user, int TAG) {
		// http://121.42.138.126:82/cg/collect.aspx?mod=a&uid=7&sid=x
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_USER + "?mod=u&id=" + user.getId()
				+ "&sid=" + user.getSid());
		JSONObject jsonParam = GsonHelper.getJson(user);
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

	public static Request updateGoodsPic(int TAG, Pics pics) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=up");
		JSONObject jsonParam = GsonHelper.getJson(pics);
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
	 * 添加图片
	 * 
	 * @param TAG
	 * @param pics
	 * @return
	 */
	public static Request addGoodsPic(int TAG, Pics pics) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=ap");
		JSONObject jsonParam = GsonHelper.getJson(pics);
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
	 * 删除图片
	 * 
	 * @param TAG
	 * @param pics
	 * @return
	 */
	public static Request deleteGoodsPic(int TAG, Pics pics) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_GOODS + "?mod=dp");
		r.putParam("id", pics.getId());
		return r;
	}

	/**
	 * 修改村子信息
	 * 
	 * @param TAG
	 * @param pics
	 * @return
	 */
	public static Request updateCity(int TAG, int uid, String sid, CgCityId city) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_CITY + "?mod=u&t=city&uid=" + uid
				+ "&sid=" + sid);
		JSONObject jsonParam = GsonHelper.getJson(city);
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
	 * 修改新闻信息 http://121.42.138.126:82/cg/news.aspx?mod=u&uid=7&sid=5
	 * 
	 * @param TAG
	 * @param pics
	 * @return
	 */
	public static Request updateNews(int TAG, CgNewsId news, int uid, String sid) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_NEWS + "?mod=u&uid=" + uid + "&sid="
				+ sid);
		JSONObject jsonParam = GsonHelper.getJson(news);
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
	 * 删除新闻
	 * 
	 * @param TAG
	 * @param id
	 * @return
	 */
	public static Request deleteNews(int TAG, int id) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_NEWS + "?mod=d");
		r.putParam("id", id);
		return r;
	}

	// http://121.42.138.126:82/cg/type.aspx?mod=l&t=city&id=1

	public static Request getTypeInfo(int TAG, int id) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_TYPE + "?mod=l&t=city");
		r.putParam("id", id);
		return r;
	}

	public static Request updateOrder(CgOrderId order, int tagUpdateorder3,
			int uid, String sid) {
		Request r = new Request(tagUpdateorder3);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_ORDER + "?mod=u&uid=" + uid + "&sid="
				+ sid);
		JSONObject jsonParam = GsonHelper.getJson(order);
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

	public static Request addReply(int tagAddrep, int uid, String sid,
			CgReplyId rep) {
		Request r = new Request(tagAddrep);
		r.setMethod(Request.METHOD_POST);
		r.setUrl(ClientConfigs.APIHOST_REPLY + "?mod=a&uid=" + uid + "&sid="
				+ sid);
		JSONObject jsonParam = GsonHelper.getJson(rep);
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

	public static Request getReply(int TAG, int goodsid) {
		Request r = new Request(TAG);
		r.setMethod(Request.METHOD_GET);
		r.setUrl(ClientConfigs.APIHOST_REPLY + "?mod=l&t=gid");
		r.putParam("id", goodsid);
		return r;
	}
}
