package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel1.demo.utils.FoxBaseCommonUtils;
import com.mediamain.android.view.FoxWallView;
import com.mediamain.android.view.bean.MessageData;
import com.mediamain.android.view.interfaces.FoxListener;


public class AppWallActivity extends BaseActivity {

    private FoxWallView mOxWallView1;
    private FoxWallView mOxWallView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_wall);
        mOxWallView1 = (FoxWallView) findViewById(R.id.app1);
        mOxWallView2 = (FoxWallView) findViewById(R.id.app2);
        String userId = getIntent().getStringExtra("userId");
        mOxWallView1.setAdListener(new FoxListener() {
            @Override
            public void onReceiveAd() {
                Log.d("========", "onReceiveAd");
            }

            @Override
            public void onFailedToReceiveAd(int errorCode, String errorMsg) {
                Log.d("========", "onFailedToReceiveAd Message="+ errorMsg);
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

        mOxWallView2.setAdListener(new FoxListener() {
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

        mOxWallView1.loadAd(301972,userId);
        mOxWallView2.loadAd(301972,userId);

//        使用本地素材方式（注：在后台勾选不是用推啊素材，否则不生效）
//        mOxWallView1.loadCustomImage(slotId,userId,resId);
    }

    @Override
    protected void onDestroy() {
        if (mOxWallView1 != null) {
            mOxWallView1.destroy();
        }
        if (mOxWallView2 != null) {
            mOxWallView2.destroy();
        }
        super.onDestroy();
    }
}
