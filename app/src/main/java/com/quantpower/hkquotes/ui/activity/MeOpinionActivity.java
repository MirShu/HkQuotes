package com.quantpower.hkquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantpower.hkquotes.R;
import com.quantpower.hkquotes.utils.UIHelper;

/**
 * Created by ShuLin on 2017/6/2.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class MeOpinionActivity extends Activity {
    ImageView imageBack;
    TextView suBmit;
    EditText edDetailed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_opinion);
        imageBack = (ImageView) findViewById(R.id.image_back);
        suBmit = (TextView) findViewById(R.id.submit);
        imageBack.setOnClickListener(v -> finish());
        edDetailed = (EditText) findViewById(R.id.ed_detailed);
        String content = edDetailed.getText().toString().trim();
        suBmit.setOnClickListener(v -> {
            if (TextUtils.isEmpty(content)) {
                UIHelper.toastMessage(MeOpinionActivity.this, "意见提交成功");
                return;
            } else if (!TextUtils.isEmpty(content)) {
                UIHelper.toastMessage(MeOpinionActivity.this, "意见提交成功");
            }
        });
    }
}
