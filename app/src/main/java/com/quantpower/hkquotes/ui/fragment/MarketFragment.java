package com.quantpower.hkquotes.ui.fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.quantpower.hkquotes.R;
import com.quantpower.hkquotes.adapter.RecyclerAdapter;
import com.quantpower.hkquotes.adapter.RecyclerViewHolder;
import com.quantpower.hkquotes.ui.activity.ProductIntroductionActivity;
import com.quantpower.hkquotes.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShuLin on 2017/6/2.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class MarketFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private RecyclerAdapter myOrderReycAdapter;
    private List<String> myOrderReycList;
    XRefreshView xrefreshview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_market, container, false);
        this.xrefreshview = (XRefreshView) rootView.findViewById(R.id.xrefreshview);
        this.recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        this.bindRecycleView();
        this.xRefreshView();
        return rootView;
    }

    private void bindRecycleView() {
        this.getData();
        this.myOrderReycAdapter = new RecyclerAdapter<String>(getActivity(), myOrderReycList,
                R.layout.item_market) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
                TextView tvState = helper.getView(R.id.tv_name);
                RelativeLayout rlItemBg = helper.getView(R.id.rl_item_bg);
                TextView tvProductLabel = helper.getView(R.id.tv_product_label);
                if (position == 0) {
                    tvState.setText("恒生指数");
                    tvProductLabel.setText("产品一");
                    Resources resources = getContext().getResources();
                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_hang_seng_index);
                    rlItemBg.setBackgroundDrawable(btnDrawable);
                } else if (position == 1) {
                    tvState.setText("原油期货");
                    tvProductLabel.setText("产品二");
                    Resources resources = getContext().getResources();
                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_crude_index);
                    rlItemBg.setBackgroundDrawable(btnDrawable);
                } else if (position == 2) {
                    tvState.setText("黄金期货");
                    tvProductLabel.setText("产品三");
                    Resources resources = getContext().getResources();
                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_gold_index);
                    rlItemBg.setBackgroundDrawable(btnDrawable);
                } else if (position == 3) {
                    tvState.setText("白银期货");
                    tvProductLabel.setText("产品四");
                    Resources resources = getContext().getResources();
                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_silver_index);
                    rlItemBg.setBackgroundDrawable(btnDrawable);
                }
            }
        };

        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1,
                LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.myOrderReycAdapter);
        this.myOrderReycAdapter.setOnItemClickListener((parent, position) -> {
            Bundle bundle = new Bundle();
            int tag = Integer.valueOf(myOrderReycList.get(position));
            bundle.putInt(ProductIntroductionActivity.ORDER_TAG, tag);
            UIHelper.startActivity(getActivity(), ProductIntroductionActivity.class, bundle);
        });

    }

    private void getData() {
        myOrderReycList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            myOrderReycList.add("" + i);
        }

    }

    /**
     * 刷新机制
     */
    private void xRefreshView() {
        this.xrefreshview.setAutoRefresh(true);
        this.xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(() -> xrefreshview.stopRefresh(), 1000);
            }

            @Override
            public void onLoadMore(boolean isSlience) {

            }
        });
    }
}
