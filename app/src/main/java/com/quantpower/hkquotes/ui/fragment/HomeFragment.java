package com.quantpower.hkquotes.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.quantpower.hkquotes.R;
import com.quantpower.hkquotes.ui.activity.MeActivity;
import com.quantpower.hkquotes.utils.Constants;
import com.quantpower.hkquotes.utils.UIHelper;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by ShuLin on 2017/6/1.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class HomeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private View rootView;
    private SegmentedGroup mSegmentedGroup;
    private RadioButton radioButtonOne, radioButtonTwo;
    private ImageView ivMe;

    public static HomeFragment newInstance(String s) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initWidget();//初始化组件
        initData();//初始化数据
        initEvent();//初始化是事件
        return rootView;
    }


    private void initEvent() {
        radioButtonOne.setChecked(true);//默认选择第一个（案例展示碎片）
    }

    private void initData() {
    }

    private void initWidget() {
        mSegmentedGroup = (SegmentedGroup) rootView.findViewById(R.id.mSegmentedGroup);//控件组
        radioButtonOne = (RadioButton) rootView.findViewById(R.id.radioButtonOne);//单选按钮一
        radioButtonTwo = (RadioButton) rootView.findViewById(R.id.radioButtonTwo);//单选按钮二
        ivMe = (ImageView) rootView.findViewById(R.id.iv_me);
        ivMe.setOnClickListener(v -> UIHelper.startActivity(getActivity(), MeActivity.class));
        mSegmentedGroup.setTintColor(Color.WHITE);//设置默认线条颜色及背景色
        mSegmentedGroup.setOnCheckedChangeListener(this);//绑定单选按钮选择监听

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonOne:
                FragmentManager fm = getActivity().getSupportFragmentManager();//获得碎片管理器
                FragmentTransaction tr = fm.beginTransaction();//Transaction事物
                //将fragment碎片添加到对应的帧布局中
                tr.replace(R.id.foundFrameLayout, new TradingFragment());//案例展示碎片
                tr.commit();//提交
                break;
            case R.id.radioButtonTwo:
                FragmentManager fm2 = getActivity().getSupportFragmentManager();
                FragmentTransaction tr2 = fm2.beginTransaction();
                tr2.replace(R.id.foundFrameLayout, new MarketFragment());//导师咨询碎片
                tr2.commit();
                break;
        }
    }

}
