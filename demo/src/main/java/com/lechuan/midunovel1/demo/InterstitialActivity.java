package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel1.demo.utils.FoxBaseCommonUtils;
import com.mediamain.android.view.FoxTbScreen;
import com.mediamain.android.view.bean.MessageData;
import com.mediamain.android.view.interfaces.FoxListener;

/**
 * 基础插屏
 */
public class InterstitialActivity extends BaseActivity {

    private FoxTbScreen mTMItAd;
    private String userId;
    private int slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        if (getIntent()!=null){
            userId = getIntent().getStringExtra("userId");
            slotId = getIntent().getIntExtra("slotId", 0);
        }
        mTMItAd = new FoxTbScreen(this);
        mTMItAd.setAdListener(new FoxListener() {
            @Override
            public void onReceiveAd() {
                Log.d("========", "onReceiveAd");
            }

            @Override
            public void onFailedToReceiveAd(int errorCode, String errorMsg) {
                Log.d("========", "onFailedToReceiveAd  Message="+errorMsg);
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
                if (!FoxBaseCommonUtils.isEmpty(s)){
                    ToastUtils.showShort(s);
                }
            }

            @Override
            public void onAdMessage(MessageData messageData) {

            }
        });
        mTMItAd.loadAd(slotId,userId);
    }
    @Override
    protected void onDestroy() {
        if (mTMItAd != null) {
            mTMItAd.destroy();
        }
        super.onDestroy();
    }
}
