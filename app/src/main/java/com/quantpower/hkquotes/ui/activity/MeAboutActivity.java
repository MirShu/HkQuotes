package com.quantpower.hkquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantpower.hkquotes.R;

/**
 * Created by ShuLin on 2017/6/2.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class MeAboutActivity  extends Activity{
    ImageView image_back;
    String loadUrl = "   港股行情软件，短线模拟交易大师，致力于为投资者及对金融感兴趣的人士提供实时行情，新鲜行业快讯，以及热门行业交易产品，让您了解港股,读懂金融，从港股开始，助力您每一份财富的实现。\n" +
            "\n   港股行情软件，免费为广大投资者提供最快最全的财经服务内容，包括：原油，贵金属资讯行情解读，财经日历，时事要闻，走势行情，专家解读，数据分析，做多做空一键搞定。\n" +
            "\n  港股行情软件,是一家专业提供外汇软件类型白银、金属、黄金、外汇等走势的财经新媒体平台，网站主要是有新闻资讯、学习视频，投资者交流等服务，致力于为投资者提供最及时的一手港股软件类型外汇资讯。";
    TextView tvAbout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_about);
        tvAbout = (TextView) findViewById(R.id.tv_about);
        tvAbout.setText(loadUrl);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(v -> finish());
    }
}
