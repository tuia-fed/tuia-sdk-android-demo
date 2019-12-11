package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.FoxInfoStreamView;
import com.lechuan.midunovel.view.FoxListener;
import com.lechuan.midunovel.view.video.util.CommonUtils;


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
            public void onAdActivityClose(String s) {
                Log.d("========", "onAdActivityClose"+s);
                if (!CommonUtils.isEmpty(s)){
                    ToastUtils.showShort(s);
                }
            }

        });
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
