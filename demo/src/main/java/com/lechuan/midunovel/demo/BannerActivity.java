package com.lechuan.midunovel.demo;

import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.view.FoxListener;
import com.lechuan.midunovel.view.FoxStreamerView;
import com.lechuan.midunovel.view.video.util.CommonUtils;


public class BannerActivity extends BaseActivity {

    private FoxStreamerView mTMBrAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        String userId = getIntent().getStringExtra("userId");
        mTMBrAdView = (FoxStreamerView) findViewById(R.id.TMBrView);
        mTMBrAdView.setAdListener(new FoxListener() {
            @Override
            public void onReceiveAd() {
                Log.d("========", "onReceiveAd");
            }

            @Override
            public void onFailedToReceiveAd() {
                Log.d("========", "onFailedToReceiveAd");
            }

            @Override
            public void onLoadFailed() {
                Log.d("========", "onLoadFailed");
            }

            @Override
            public void onCloseClick() {
                Log.d("========", "onCloseClick");
            }

            @Override
            public void onAdClick() {
                Log.d("========", "onAdClick");
            }

            @Override
            public void onAdExposure() {
                Log.d("========", "onExposure");
            }

            @Override
            public void onAdActivityClose(String s) {
                Log.d("========", "onAdActivityClose"+s);
                if (!CommonUtils.isEmpty(s)){
                    ToastUtils.showShort(s);
                }
            }
        });

        mTMBrAdView.loadAd(301970,userId);
    }

    @Override
    protected void onDestroy() {
        if (mTMBrAdView != null) {
            mTMBrAdView.destroy();
        }
        super.onDestroy();
    }
}
