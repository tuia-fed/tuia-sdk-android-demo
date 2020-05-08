package com.lechuan.midunovel1.demo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.lechuan.midunovel.demo.R;
import com.ta.android.nativead.Ad;
import com.ta.android.nativead.AdCallBack;

public class NativeInterstitialActivity extends BaseActivity {

    private Ad ad;
    private String userId;
    private int slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_interstitial);
        if (getIntent()!=null){
            userId = getIntent().getStringExtra("userId");
            slotId = getIntent().getIntExtra("slotId", 0);
        }
        //s1：slotId  s2:userId  s3:deviceId
        ad = new Ad("4UycwwZv41rwzne1ZXgtQBgDSnPH", slotId+"", userId, "");
        ad.init(NativeInterstitialActivity.this, null, Ad.AD_URL_NEW, new AdCallBack() {

            @Override
            public void onReceiveAd() {
                Log.d("========", "onReceiveAd");
            }

            @Override
            public void onFailedToReceiveAd() {
                Log.d("========", "onFailedToReceiveAd");
            }

            @Override
            public void onActivityClose() {
                Log.d("========", "onActivityClose");
            }

            @Override
            public void onActivityShow() {
                Log.d("========", "onActivityShow");
            }

            @Override
            public void onRewardClose() {
                Log.d("========", "onRewardClose");
            }

            @Override
            public void onRewardShow() {
                Log.d("========", "onRewardShow");
            }

            @Override
            public void onPrizeClose() {
                Log.d("========", "onPrizeClose");
            }

            @Override
            public void onPrizeShow() {
                Log.d("========", "onPrizeShow");
            }
        });
        findViewById(R.id.btn_req).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ad != null) {
//                    ad.resetSlotId(slotId+"");
                    ad.loadAd(NativeInterstitialActivity.this, false);
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (ad != null) {
            ad.resetAdSize(newConfig.orientation);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isConsume = false;
        if (ad != null) {
            isConsume = ad.onKeyBack(keyCode, event);
        }
        if (!isConsume) {
            return super.onKeyDown(keyCode, event);
        }
        return isConsume;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ad != null) {
            ad.destroy();
        }
    }
}
