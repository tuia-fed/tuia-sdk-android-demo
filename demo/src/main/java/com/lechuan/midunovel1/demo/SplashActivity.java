package com.lechuan.midunovel1.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.base.util.FoxBaseCommonUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.FoxShListener;
import com.lechuan.midunovel.view.FoxShView;

/**
 * 开屏广告参考自定义广告实现方式：NonStandarActivity
 *
 * 1.参考自定义广告请求，素材曝光，活动点击加载
 * 2.媒体自己处理素材展示加载逻辑
 *
 */
public class SplashActivity extends Activity {

    protected FoxShView mOXShView;

    private static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= 23) {
            if(!lacksPermissions(this,NEEDED_PERMISSIONS)){
                ActivityCompat.requestPermissions(this,NEEDED_PERMISSIONS,0);
            }
        }
        mOXShView = (FoxShView)findViewById(R.id.TMSh_container);
        mOXShView.setTargetClass(this,MainActivity.class);
        mOXShView.setAdListener(new FoxShListener(){
            @Override
            public void onTimeOut() {
                Log.d("TMShActivity", "onTimeOut");
            }

            @Override
            public void onReceiveAd() {
                Log.d("TMShActivity", "onReceiveAd");
            }

            @Override
            public void onFailedToReceiveAd() {
                Log.d("TMShActivity", "onFailedToReceiveAd");
            }

            @Override
            public void onLoadFailed() {
                Log.d("TMShActivity", "onLoadFailed");
            }

            @Override
            public void onCloseClick() {
                Log.d("TMShActivity", "onCloseClick");
            }

            @Override
            public void onAdClick() {
                Log.d("TMShActivity", "onClick");
            }

            @Override
            public void onAdExposure() {
                Log.d("TMShActivity", "onAdExposure");
            }

            @Override
            public void onAdActivityClose(String s) {
                Log.d("========", "onAdActivityClose"+s);
                if (!FoxBaseCommonUtils.isEmpty(s)){
                    ToastUtils.showShort(s);
                }
            }

        });
        mOXShView.loadAd(323775);
    }


        @Override
    protected void onDestroy() {
        if (mOXShView != null) {
            mOXShView.destroy();
        }
        super.onDestroy();
    }

    /**
     * 判断权限集合
     * permissions 权限数组
     * return false-表示没有改权限  true-表示权限已开启
     */
    public  boolean lacksPermissions(Context mContexts, String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}

