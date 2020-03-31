package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lechuan.midunovel.base.util.FoxBaseCommonUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.holder.FoxInfoAd;
import com.lechuan.midunovel.view.holder.FoxNativeAdHelper;
import com.lechuan.midunovel.view.holder.FoxNativeInfoHolder;

/**
 * 信息流广告
 */
public class NativeAdActivity extends BaseActivity implements FoxNativeInfoHolder.LoadInfoAdListener {

    private LinearLayout mContainer;
    private FoxNativeInfoHolder nativeInfoHolder;
    private String userId;
    private int slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad);
        mContainer = findViewById(R.id.container);
        if (getIntent()!=null){
            userId = getIntent().getStringExtra("userId");
            slotId = getIntent().getIntExtra("slotId", 0);
        }
        nativeInfoHolder = FoxNativeAdHelper.getNativeInfoHolder();
        nativeInfoHolder.loadInfoAd(slotId,userId,this);
    }

    @Override
    protected void onDestroy() {
        if (nativeInfoHolder != null) {
            nativeInfoHolder.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onError(String errorBody) {

    }

    @Override
    public void infoAdSuccess(FoxInfoAd foxInfoAd) {
        View view = null;
        if (foxInfoAd != null) {
            view = foxInfoAd.getView();
        }
        if (view != null && mContainer != null && !NativeAdActivity.this.isFinishing()) {
            mContainer.removeAllViews();
            mContainer.addView(view);
        }
    }

    @Override
    public void onReceiveAd() {

    }

    @Override
    public void onFailedToReceiveAd() {

    }

    @Override
    public void onLoadFailed() {

    }

    @Override
    public void onCloseClick() {

    }

    @Override
    public void onAdClick() {

    }

    @Override
    public void onAdExposure() {

    }

    @Override
    public void onAdActivityClose(String data) {

    }

}
