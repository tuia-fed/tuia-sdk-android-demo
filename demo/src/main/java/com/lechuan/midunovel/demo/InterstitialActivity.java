package com.lechuan.midunovel.demo;

import android.os.Bundle;
import android.util.Log;

import com.lechuan.midunovel.view.FoxListener;
import com.lechuan.midunovel.view.FoxTbScreen;


public class InterstitialActivity extends BaseActivity {
    private FoxTbScreen mTMItAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        String userId = getIntent().getStringExtra("userId");
        mTMItAd = new FoxTbScreen(this);
        mTMItAd.setAdListener(new FoxListener() {
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
//        mTMItAd.loadAd(459);
        mTMItAd.loadAd(301968,userId);
    }
    @Override
    protected void onDestroy() {
        if (mTMItAd != null) {
            mTMItAd.destroy();
        }
        super.onDestroy();
    }
}
