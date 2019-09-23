package com.lechuan.midunovel.demo;

import android.os.Bundle;
import android.util.Log;

import com.lechuan.midunovel.view.FoxInfoStreamView;
import com.lechuan.midunovel.view.FoxListener;


public class NativeAdActivity extends BaseActivity {


    private FoxInfoStreamView mTMNaAdView;
    private FoxInfoStreamView mTMNaAdView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad);
        String userId = getIntent().getStringExtra("userId");
        mTMNaAdView = (FoxInfoStreamView) findViewById(R.id.TMNaView);
        mTMNaAdView1 = (FoxInfoStreamView) findViewById(R.id.TMNaView1);
        mTMNaAdView.setAdListener(new FoxListener() {
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
                Log.d("========", "onAdExposure");
            }

            @Override
            public void onAdReward(String data) {
                Log.d("========", "onAdReward");
            }
        });
//        mTMNaAdView.loadAd(461);
//        mTMNaAdView1.loadAd(460);
        mTMNaAdView.loadAd(306438,userId);
        mTMNaAdView1.loadAd(306438,userId);
    }

    @Override
    protected void onDestroy() {
        if (mTMNaAdView != null) {
            mTMNaAdView.destroy();
        }
        if (mTMNaAdView1 != null) {
            mTMNaAdView1.destroy();
        }
        super.onDestroy();
    }
}
