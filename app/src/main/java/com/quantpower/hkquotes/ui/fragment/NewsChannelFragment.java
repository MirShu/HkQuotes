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

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.quantpower.hkquotes.R;
import com.quantpower.hkquotes.ui.activity.NewsDetailActivity;
import com.quantpower.hkquotes.utils.MessageResult;
import com.quantpower.hkquotes.utils.UIHelper;
import com.quantpower.hkquotes.utils.URLS;
import com.quantpower.hkquotes.adapter.RecyclerAdapter;
import com.quantpower.hkquotes.adapter.RecyclerViewHolder;
import com.quantpower.hkquotes.model.NewsModel;
import com.quantpower.hkquotes.widget.LodingDialog;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShuLin on 2017/6/2.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class NewsChannelFragment extends Fragment {
    RecyclerView recyclerView;
    private LodingDialog lodingDialog;
    private RecyclerAdapter<NewsModel> myOrderReycAdapter;
    private List<NewsModel> myOrderReycList = new ArrayList<>();
    XRefreshView xrefreshview;
    private View rootView;
    private static final String ARG_POSITION = "position";
    //    private int newsType;
    private int readsize = 0;
//    private int orreadsize = 0;
//    private String newsTime = "0";
//    private String ornewsTime = "0";

    public static NewsChannelFragment newInstance(int position) {
        NewsChannelFragment newsChannelFragment = new NewsChannelFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        newsChannelFragment.setArguments(bundle);
        return newsChannelFragment;
    }

    /**************************************************************
     * NewsHomeFragment 通过标签传递参数跳转到 NewsChannelFragment
     **************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        newsType = getArguments().getInt(ARG_POSITION);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_news, container, false);
        this.xrefreshview = (XRefreshView) rootView.findViewById(R.id.xrefreshview);
        this.recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        this.bindRecycleView();
        this.xRefreshView();
        this.lodingDialog = new LodingDialog(getActivity(), "加载中...");
        this.lodingDialog.show();
        new Handler().postDelayed(() -> lodingDialog.dismiss(), 1000);
        return rootView;
    }


    private void bindRecycleView() {
        this.getData();
        this.myOrderReycAdapter = new RecyclerAdapter<NewsModel>(getActivity(), myOrderReycList,
                R.layout.item_news) {
            @Override
            public void convert(RecyclerViewHolder helper, NewsModel item, int position) {
                ImageView news_img = helper.getView(R.id.news_img);
                helper.setText(R.id.news_title, item.getNews_title());
                helper.setText(R.id.news_time, item.getNews_time_show());
                helper.setText(R.id.news_readcount, item.getNews_readcount());
                helper.setText(R.id.news_type, item.getNews_type());
                helper.setImageUrl(R.id.news_img, item.getNews_img());
                news_img.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        };

        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1,
                LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.myOrderReycAdapter);
        this.myOrderReycAdapter.setOnItemClickListener((parent, position) -> {
            Bundle bundle = new Bundle();
            bundle.putInt(NewsDetailActivity.NEWS_ID_TAG, myOrderReycList.get(position).getId());
            UIHelper.startActivity(getActivity(), NewsDetailActivity.class, bundle);
        });
    }

    private void getData() {
        String readSize = String.valueOf(readsize);
        RequestParams params = new RequestParams(URLS.NEWS_NEWS_LIST);
        params.addBodyParameter("news_time", String.valueOf(0));
        params.addBodyParameter("type", "2,4,7,8,9,12");
        params.addBodyParameter("read", readSize);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    MessageResult message = MessageResult.parse(result);
                    List<NewsModel> newsList = JSON.parseArray(message.getData(),
                            NewsModel.class);
                    myOrderReycList.addAll(newsList);
                    myOrderReycAdapter.notifyDataSetChanged();
                    lodingDialog.dismiss();
                } catch (Exception e) {

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    /**
     * 刷新机制
     */
    private void xRefreshView() {
        this.xrefreshview.setPullLoadEnable(true);
        this.xrefreshview.setMoveForHorizontal(true);
        this.xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                myOrderReycList.clear();
                readsize = 0;
                getData();
                new Handler().postDelayed(() -> xrefreshview.stopRefresh(), 1000);
            }

            @Override
            public void onLoadMore(boolean isSlience) {
                new Handler().postDelayed(() -> {
                    readsize += 10;
                    getData();
                }, 600);
                new Handler().postDelayed(() -> xrefreshview.stopLoadMore(), 1500);
            }

        });
    }
}
