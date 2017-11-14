package com.quantpower.hkquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.quantpower.hkquotes.R;
import com.quantpower.hkquotes.utils.UIHelper;

/**
 * Created by ShuLin on 2017/6/2.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class MeActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_me_collection:
                UIHelper.startActivity(MeActivity.this, MeCollectionActivity.class);
                break;
            case R.id.tv_me_guide:
                UIHelper.startActivity(MeActivity.this, MeGuideActivity.class);
                break;
            case R.id.tv_me_clean:
                UIHelper.toastMessage(MeActivity.this, "清除成功");
                break;
            case R.id.tv_me_opinion:
                UIHelper.startActivity(MeActivity.this, MeOpinionActivity.class);
                break;
            case R.id.tv_me_about:
                UIHelper.startActivity(MeActivity.this, MeAboutActivity.class);
                break;
            case R.id.tv_me_version:
                UIHelper.toastMessage(MeActivity.this, "为最新版本");
                break;
            case R.id.image_back:
                finish();
                break;
        }
    }
}
