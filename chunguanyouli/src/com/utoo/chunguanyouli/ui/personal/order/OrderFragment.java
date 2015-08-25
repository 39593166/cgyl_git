package com.utoo.chunguanyouli.ui.personal.order;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.utoo.chunguanyouli.R;
import com.utoo.chunguanyouli.dbentity.CgOrderGoodsId;
import com.utoo.chunguanyouli.dbentity.CgOrderId;
import com.utoo.chunguanyouli.server.API_F;
import com.utoo.chunguanyouli.server.CgResponse;
import com.utoo.chunguanyouli.tool.CommonExpandableAdapter;
import com.utoo.chunguanyouli.tool.ViewHolderHelper;
import com.utoo.chunguanyouli.ui.base.NetDataBaseFragment;
import com.utoo.chunguanyouli.ui.shopping.goodsinfo.GoodsInfoActivity;

public class OrderFragment extends NetDataBaseFragment implements
        OnRefreshListener2<ExpandableListView>, OnChildClickListener,
        OnGroupClickListener {
    OrderListActivity context;
    View pView;

    final int TAG_MORE = 1;
    final int TAG_REFLESH = 2;

    PullToRefreshExpandableListView orderListView;
    CommonExpandableAdapter<CgOrderId, CgOrderGoodsId> orderAdapter;
    public int state;
    private int pageIndex = 1;

    public static OrderFragment getInstance(int state) {
        OrderFragment instance = new OrderFragment();
        instance.state = state;
        return instance;
    }
//	public OrderFragment(int state) {
//		super();
//		this.state = state;
//	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (OrderListActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        pView = inflater
                .inflate(R.layout.fragment_order_list, container, false);
        initAdapter();
        initViews();
        getOrders(TAG_MORE);
        return pView;
    }

    private void getOrders(int tag) {
        int userId = context.userNeed.getId();
        String sid = context.userNeed.getSid();
        apply(API_F.getOrderList(userId,sid, state, pageIndex, false, tag));
    }

    private void initAdapter() {
        orderAdapter = new CommonExpandableAdapter<CgOrderId, CgOrderGoodsId>(
                context, R.layout.activity_orders_list_item,
                R.layout.item_ordergoods) {

            @Override
            public List<CgOrderGoodsId> getChildDatas(CgOrderId per) {
                return per.getGoodslist();
            }

            @Override
            public void convertchiView(ViewHolderHelper helper,
                                       CgOrderGoodsId item) {
                helper.setText(R.id.order_goods_name, item.getGoodsname())
                        .setText(R.id.order_goods_price, "￥" + item.getPrice())
                        .setText(R.id.order_goods_num, item.getNum() + "")
                        .setImageByUrl(R.id.order_goods_img, item.getPic(),
                                R.drawable.index_goods_list,
                                R.drawable.index_goods_list);
            }

            @Override
            public void convertPerView(ViewHolderHelper helper, CgOrderId item) {
                helper.setText(R.id.orderListItemNum, item.getNumstr());
                helper.setText(R.id.orderListItemPrice,
                        "￥" + item.getGoodsprice());
                helper.setText(R.id.orderListItemTime, "" + item.getDateset());
                if (item.getNowState() == CgOrderId.STATU_USER_SUBMIT) {
                    helper.setText(R.id.orderListItemState, "待付款");
                }
                if (item.getNowState() == CgOrderId.STATU_PAYED
                        || item.getNowState() == CgOrderId.STATU_WAITTING_APPEND) {
                    helper.setText(R.id.orderListItemState, "待发货");
                }
                if (item.getNowState() == CgOrderId.STATU_APPENDDING) {
                    helper.setText(R.id.orderListItemState, "已发货");
                }
                if (item.getNowState() == CgOrderId.STATU_OVER) {
                    helper.setText(R.id.orderListItemState, "已完成");
                }
            }
        };
    }

    ExpandableListView ex = null;

    private void initViews() {
        this.orderListView = (PullToRefreshExpandableListView) pView
                .findViewById(R.id.orderListView);
        ex = orderListView.getRefreshableView();
        ex.setGroupIndicator(null);
        orderListView.setMode(Mode.BOTH);
        orderListView.setOnRefreshListener(this);
        ex.setOnChildClickListener(this);
        ex.setOnGroupClickListener(this);
        ex.setAdapter(orderAdapter);
    }

    private void setOrder(int tag, List<CgOrderId> orderList) {
        pageIndex++;
        if (tag == TAG_MORE) {
            orderAdapter.addData(orderList);
        } else if (tag == TAG_REFLESH) {
            orderAdapter.resetData(orderList);
        }
        for (int i = 0; i < orderAdapter.getGroupCount(); i++) {
            ex.expandGroup(i);
        }

    }

    /**
     * 刷新完成
     */
    public void onRefreshComplete() {
        orderListView.onRefreshComplete();
    }

    /**
     * 设置正在
     */
    public void setRefreshing() {
        orderListView.setRefreshing();
    }

    @Override
    protected void NetFailed(VolleyError error, int TAG) {
        onRefreshComplete();
    }

    @Override
    protected void onReturned(String response, int TAG) {
        onRefreshComplete();
        CgResponse cr = new CgResponse(response);
        if (cr.getState() == 0) {
            return;
        }
        Gson gson = new Gson();
        List<CgOrderId> orderList = gson.fromJson(cr.getValStr(),
                new TypeToken<List<CgOrderId>>() {
                }.getType());
        setOrder(TAG, orderList);

    }

    @Override
    public void onPullDownToRefresh(
            PullToRefreshBase<ExpandableListView> refreshView) {
        pageIndex = 1;
        getOrders(TAG_REFLESH);
    }

    @Override
    public void onPullUpToRefresh(
            PullToRefreshBase<ExpandableListView> refreshView) {
        getOrders(TAG_MORE);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), GoodsInfoActivity.class);
        intent.putExtra("goodsId",
                orderAdapter.getChild(groupPosition, childPosition)
                        .getGoodsid());
        getActivity().startActivity(intent);
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v,
                                int groupPosition, long id) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), OrderInfoActivity.class);
        intent.putExtra("orderId", orderAdapter.getGroup(groupPosition).getId());
        getActivity().startActivity(intent);
        return true;
    }

}
