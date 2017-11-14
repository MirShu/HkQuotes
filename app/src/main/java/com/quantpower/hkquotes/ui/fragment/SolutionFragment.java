package com.quantpower.hkquotes.ui.fragment;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.quantpower.hkquotes.R;
import com.quantpower.hkquotes.adapter.RecyclerAdapter;
import com.quantpower.hkquotes.adapter.RecyclerViewHolder;
import com.quantpower.hkquotes.ui.activity.LiveRoomActivity;
import com.quantpower.hkquotes.utils.Constants;
import com.quantpower.hkquotes.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShuLin on 2017/6/1.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class SolutionFragment extends Fragment {
    RecyclerView recyclerView;
    private RecyclerAdapter myOrderReycAdapter;
    private List<String> myOrderReycList;
    XRefreshView xrefreshview;
    private View rootView;

    public static SolutionFragment newInstance(String s) {
        SolutionFragment homeFragment = new SolutionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_solution, container, false);
        this.xrefreshview = (XRefreshView) rootView.findViewById(R.id.xrefreshview);
        this.recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        this.bindRecycleView();
        this.myOrderReycAdapter.notifyDataSetChanged();
        this.xRefreshView();
        return rootView;
    }

    private void bindRecycleView() {
        this.getData();
        this.myOrderReycAdapter = new RecyclerAdapter<String>(getActivity(), myOrderReycList,
                R.layout.item_found) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
                TextView tvState = helper.getView(R.id.tv_name);
                TextView tvProductLabel = helper.getView(R.id.tv_product_label);
                TextView tvCenter = helper.getView(R.id.tv_center);
                ImageView imItem = helper.getView(R.id.im_item_bg);
                TextView tvStar = helper.getView(R.id.tv_star);
                ImageView imageHead = helper.getView(R.id.iv_head);

                if (position == 0) {
                    tvState.setText("张老师");
                    tvProductLabel.setText("金融泰斗,业界神话");
                    imItem.setImageResource(R.mipmap.bg_crude_index);
                    imageHead.setImageResource(R.mipmap.head_one);
                } else if (position == 1) {
                    tvState.setText("西门老师");
                    tvProductLabel.setText("专职私募操盘手");
                    imItem.setImageResource(R.mipmap.bg_gold_index);
                    imageHead.setImageResource(R.mipmap.head_two);
                } else if (position == 2) {
                    tvState.setText("李老师");
                    tvProductLabel.setText("资深讲师,专业讲解");
                    imItem.setImageResource(R.mipmap.bg_hang_seng_index);
                    imageHead.setImageResource(R.mipmap.head_three);
                } else if (position == 3) {
                    tvState.setText("上官老师");
                    tvProductLabel.setText("分析精准,独具慧眼");
                    tvCenter.setText("短线暴利建仓");
                    imItem.setImageResource(R.mipmap.bg_silver_index);
                    imageHead.setImageResource(R.mipmap.head_one);
                }
                tvStar.setOnClickListener(view -> {
                    if (tvStar.getText().equals("+关注")) {
                        UIHelper.toastMessage(getActivity(), "已关注");
                        tvStar.getBackground().setAlpha(100);
                        tvStar.setText("已关注");
                    } else {
                        UIHelper.toastMessage(getActivity(), "已取消关注");
                        tvStar.getBackground().setAlpha(255);
                        tvStar.setText("+关注");
                    }
                });

            }
        };

        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1,
                LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.myOrderReycAdapter);
        this.myOrderReycAdapter.setOnItemClickListener((parent, position) -> {
            UIHelper.startActivity(getActivity(), LiveRoomActivity.class);
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
