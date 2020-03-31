package com.lechuan.midunovel1.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.holder.FoxNativeAdHelper;
import com.lechuan.midunovel.view.holder.FoxNativeSplashHolder;
import com.lechuan.midunovel.view.holder.FoxSplashAd;

/**
 * 开屏广告
 */
public class SplashActivity extends BaseActivity implements View.OnClickListener, FoxNativeSplashHolder.LoadSplashAdListener {

    private FrameLayout mContainer;
    private FoxNativeSplashHolder foxNativeSplashHolder;
    private String userId;
    private int slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (getIntent()!=null){
            userId = getIntent().getStringExtra("userId");
            slotId = getIntent().getIntExtra("slotId", 0);
        }
        mContainer = findViewById(R.id.container);
        foxNativeSplashHolder = FoxNativeAdHelper.getNativeSplashHolder();
        findViewById(R.id.btn_req).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (foxNativeSplashHolder !=null){
            foxNativeSplashHolder.destroy();
        }
    }

    /**
     * 跳转主页
     */
    private void jumpMain() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        if (foxNativeSplashHolder !=null){
           foxNativeSplashHolder.loadSplashAd(slotId,userId,this);
        }
    }

    @Override
    public void onError(String errorBody) {
        Toast.makeText(SplashActivity.this,"Error-"+errorBody,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void splashAdSuccess(FoxSplashAd foxSplashAd) {
        View view = null;
        if(foxSplashAd != null){
            foxSplashAd.setScaleType(ImageView.ScaleType.FIT_XY);
            view = foxSplashAd.getView();
        }
        if (view != null && mContainer != null && !SplashActivity.this.isFinishing()){
            mContainer.removeAllViews();
            mContainer.addView(view);
        }else{
            jumpMain();
        }
    }

    @Override
    public void onTimeOut() {
        Toast.makeText(SplashActivity.this,"倒计时时间到",Toast.LENGTH_SHORT).show();
        jumpMain();
    }

    @Override
    public void onReceiveAd() {
        Toast.makeText(SplashActivity.this,"广告已加载完成",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailedToReceiveAd() {

    }

    @Override
    public void onLoadFailed() {
        Toast.makeText(SplashActivity.this,"广告加载失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCloseClick() {
        Toast.makeText(SplashActivity.this,"点击了跳过按钮",Toast.LENGTH_SHORT).show();
        jumpMain();
    }

    @Override
    public void onAdClick() {
        Toast.makeText(SplashActivity.this,"点击了广告",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdExposure() {
        Toast.makeText(SplashActivity.this,"广告曝光成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdActivityClose(String data) {
        jumpMain();
    }
}

