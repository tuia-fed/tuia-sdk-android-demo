package com.lechuan.midunovel.demo;

import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.view.FoxListener;
import com.lechuan.midunovel.view.FoxWallView;
import com.lechuan.midunovel.view.video.util.CommonUtils;


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

        mOxWallView2.setAdListener(new FoxListener() {
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

//        mOxWallView1.loadAd(464);
//        mOxWallView2.loadAd(464);
        mOxWallView1.loadAd(301972,userId);
        mOxWallView2.loadAd(301972,userId);
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
