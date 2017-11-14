package com.quantpower.hkquotes.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.quantpower.hkquotes.R;

/**
 * Created by ShuLin on 2017/6/2.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class TradingFragment extends Fragment {
    private View rootView;
    private WebView market_WebView;
    private WebSettings wvSettings;
    private String menu_url = "http://m.dyhjw.com/quote/,http://m.dyhjw.com/shanghaihuangjin/,\r\nhttp://m.dyhjw.com/guojijin.html,http://m.dyhjw.com/zhipan.html,\r\nhttp://m.dyhjw.com/guzhi.html,http://m.dyhjw.com/guijinshu.html,\r\nhttp://m.dyhjw.com/meiguoyuanyou/";
    private String webview = "http://m.zhicheng.com/stock/list/hk/1.html";
    private String jquery = "$('.phone-top').remove();" +
            "$('.phone-wrapper').css('padding-top','0px');" +
            "$('#aabbcc').remove();" +
            "$('body').css('padding-top','0px');";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_trading, container, false);
        this.market_WebView = (WebView) rootView.findViewById(R.id.market_WebView);
        loadJsCod();
        showWebViewData();
        return rootView;
    }


    private void loadJsCod() {
        market_WebView.loadUrl(webview);
        jquery = "javascript: " + jquery;
    }

    /**
     * 显示行情与隐藏行情上面title标题
     */
    private void showWebViewData() {
        this.market_WebView.setVisibility(View.INVISIBLE);
        wvSettings = market_WebView.getSettings();
        wvSettings.setJavaScriptEnabled(true);
        wvSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        wvSettings.setAppCacheEnabled(true);
        wvSettings.setDomStorageEnabled(true);
        wvSettings.setDatabaseEnabled(true);
        wvSettings.setAllowFileAccess(true);
        wvSettings.setBlockNetworkImage(true);
        wvSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        wvSettings.setDomStorageEnabled(true);
        market_WebView.setOnLongClickListener(view -> true);

        /**
         * 定时执行
         */
        new Thread(() -> {
            int count = 0;
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        /**
         * WebView 控件webViewClient 所有回调方法
         */
        market_WebView.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (menu_url.indexOf(url) != -1) {
                } else {
                }
                market_WebView.setVisibility(View.GONE);

            }


            /**
             * 加载给定URL资源内容   该方法待使用    判断webview是否可以返回,不能返回就隐藏返回按钮
             */
            @Override
            public void onLoadResource(final WebView view, String url) {
                view.loadUrl(jquery);
                super.onLoadResource(view, url);
            }

            /**
             * 页面加载完成回调方法，获取对应url地址
             * */
            @Override
            public void onPageFinished(final WebView view, String url) {

                new Handler().postDelayed(() -> {
                    market_WebView.setVisibility(View.VISIBLE);
                }, 100);
                wvSettings.setBlockNetworkImage(false);
                super.onPageFinished(view, url);
            }

            /**
             * 自己定义加载错误处理效果，比如：TeachCourse定义在没有网络时候，显示一张无网络的图片
             * API 23 之后调用
             */
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                view.setVisibility(View.GONE);
                super.onReceivedError(view, request, error);

            }

            /**
             * 自己定义加载错误处理效果，比如：TeachCourse定义在没有网络时候，显示一张无网络的图片
             *API 23之前调用
             */
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.setVisibility(View.GONE);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }

}
