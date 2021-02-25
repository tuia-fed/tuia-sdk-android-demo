package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel1.demo.utils.FoxBaseCommonUtils;
import com.mediamain.android.view.FoxStreamerView;
import com.mediamain.android.view.bean.MessageData;
import com.mediamain.android.view.interfaces.FoxListener;


public class BannerActivity extends BaseActivity {

    private FoxStreamerView mTMBrAdView;
    private String userId;
    private int slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        if (getIntent()!=null){
            userId = getIntent().getStringExtra("userId");
            slotId = getIntent().getIntExtra("slotId", 0);
        }
        mTMBrAdView = (FoxStreamerView) findViewById(R.id.TMBrView);
        mTMBrAdView.setAdListener(new FoxListener() {
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
                Log.d("========", "onExposure");
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

        mTMBrAdView.loadAd(slotId,userId);
//        使用本地素材方式（注：在后台勾选不是用推啊素材，否则不生效）
//        mTMBrAdView.loadCustomImage(slotId,userId,resId);
    }

    @Override
    protected void onDestroy() {
        if (mTMBrAdView != null) {
            mTMBrAdView.destroy();
        }
        super.onDestroy();
    }
}
