package com.quantpower.hkquotes.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.quantpower.hkquotes.R;
import com.quantpower.hkquotes.utils.Constants;
import com.quantpower.hkquotes.widget.scollview.HorizontalScrollViewEx;

import java.lang.reflect.Field;

/**
 * Created by ShuLin on 2017/6/1.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class AlertsFragment extends Fragment {
    public static AlertsFragment newInstance(String s) {
        AlertsFragment homeFragment = new AlertsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    private View rootView;
    private HorizontalScrollViewEx scrollViewEx;
    private DisplayMetrics dm;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_alerts, container, false);
        this.setOverflowShowingAlways();
        this.dm = getResources().getDisplayMetrics();
        this.viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        this.viewPager.setOffscreenPageLimit(13);
        this.scrollViewEx = (HorizontalScrollViewEx) rootView.findViewById(R.id.tabs);
        this.viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        this.scrollViewEx.setViewPager(viewPager);
        setTabsValue();
        onPageChangeListener();
        return rootView;
    }


    private void onPageChangeListener() {
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 对HorizontalScrollViewEx的各项属性进行赋值。
     */
    private void setTabsValue() {
        this.scrollViewEx.setShouldExpand(true);
        this.scrollViewEx.setDividerColor(Color.TRANSPARENT);
        this.scrollViewEx.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15, dm));
        this.scrollViewEx.setSelectedTextColor(Color.parseColor("#f23b17"));
        this.scrollViewEx.setTabBackground(0);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] titles = {"港股", "外汇", "黄金", "白银", "原油", "股票"};

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            return NewsChannelFragment.newInstance(position);
        }

    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration
                    .get(getParentFragment().getActivity());
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
