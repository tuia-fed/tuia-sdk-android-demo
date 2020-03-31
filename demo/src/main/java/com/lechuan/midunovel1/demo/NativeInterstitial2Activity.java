package com.lechuan.midunovel1.demo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lechuan.midunovel.base.util.FoxBaseCommonUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.nativead.Ad;
import com.lechuan.midunovel.nativead.AdCallBack;
import com.lechuan.midunovel.nativead.DefaultAdCallBack;
import com.lechuan.midunovel.nativead.bean.AdResponseBean;

public class NativeInterstitial2Activity extends BaseActivity {


    private Ad ad;
    private String userId;
    private AdResponseBean.DataBean mData;
    private int slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_interstitial2);
        if (getIntent() != null) {
            userId = getIntent().getStringExtra("userId");
            slotId = getIntent().getIntExtra("slotId", 0);
        }
        final ImageView iv_image = findViewById(R.id.iv_image);
        //s1：slotId  s2:userId  s3:deviceId
        ad = new Ad("4UycwwZv41rwzne1ZXgtQBgDSnPH", slotId + "", userId, "");
        ad.init(NativeInterstitial2Activity.this, null, Ad.AD_URL_NEW, new DefaultAdCallBack() {
            @Override
            public void onReceiveAd(AdResponseBean.DataBean dataBean) {
                super.onReceiveAd(dataBean);
                mData = dataBean;
                if (dataBean != null && !FoxBaseCommonUtils.isEmpty(dataBean.getImageUrl())) {
                    if (dataBean.getImageUrl().endsWith(".gif")) {
                        Glide.with(NativeInterstitial2Activity.this).load(dataBean.getImageUrl()).asGif().into(iv_image);
                    } else {
                        Glide.with(NativeInterstitial2Activity.this).load(dataBean.getImageUrl()).into(iv_image);
                    }
                }
            }
        });
        findViewById(R.id.btn_req).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ad != null) {
//                    ad.resetSlotId(""+slotId);
                    ad.loadAd(NativeInterstitial2Activity.this, true);
                }
            }
        });
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ad != null && mData != null) {
                    ad.show();
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
