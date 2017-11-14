package com.quantpower.hkquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantpower.hkquotes.R;
import com.quantpower.hkquotes.adapter.RecyclerAdapter;
import com.quantpower.hkquotes.adapter.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShuLin on 2017/6/1.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class CurriculumActivity extends Activity {
    private ImageView imageBack;
    RecyclerView recyclerView;
    private RecyclerAdapter myOrderReycAdapter;
    private List<String> myOrderReycList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiyt_curriculum);
        imageBack = (ImageView) findViewById(R.id.image_back);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imageBack.setOnClickListener(v -> finish());
        this.bindRecycleView();
    }

    private void bindRecycleView() {
        this.getData();
        this.myOrderReycAdapter = new RecyclerAdapter<String>(CurriculumActivity.this, myOrderReycList,
                R.layout.item_curriculum) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
                TextView tvName = helper.getView(R.id.tv_name);
                TextView tvState = helper.getView(R.id.tv_state);
                TextView tvWeek = helper.getView(R.id.tv_week);
                TextView tvTime = helper.getView(R.id.tv_time);
                if (position == 0) {
                    tvName.setText("秦老师");
                    tvState.setText("黄金");
                    tvWeek.setText("周三至周五");
                    tvTime.setText("09:00-10:00");
                } else if (position == 1) {
                    tvName.setText("唐老师");
                    tvState.setText("白银");
                    tvWeek.setText("周一至周五");
                    tvTime.setText("10:00-12:00");
                } else if (position == 2) {
                    tvName.setText("秦天柱");
                    tvState.setText("黄金");
                    tvWeek.setText("周三至周五");
                    tvTime.setText("09:00-10:00");
                } else if (position == 3) {
                    tvName.setText("皮卡丘");
                    tvState.setText("白银");
                    tvWeek.setText("周一至周五");
                    tvTime.setText("10:00-12:00");
                } else if (position == 4) {
                    tvName.setText("毛老师");
                    tvState.setText("黄金");
                    tvWeek.setText("周三至周五");
                    tvTime.setText("09:00-10:00");
                } else if (position == 5) {
                    tvName.setText("张老师");
                    tvState.setText("黄金");
                    tvWeek.setText("周三至周五");
                    tvTime.setText("15:00-15:40");
                } else if (position == 6) {
                    tvName.setText("李老师");
                    tvState.setText("黄金");
                    tvWeek.setText("周三至周五");
                    tvTime.setText("13:00-12:00");
                } else if (position == 7) {
                    tvName.setText("周老师");
                    tvState.setText("黄金");
                    tvWeek.setText("周三至周五");
                    tvTime.setText("14:00-15:00");
                }

            }
        };

        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(CurriculumActivity.this, 1,
                LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.myOrderReycAdapter);

    }

    private void getData() {
        myOrderReycList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            myOrderReycList.add("" + i);
        }
    }

}
