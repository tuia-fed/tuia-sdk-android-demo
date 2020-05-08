package com.lechuan.midunovel1.demo;


import android.os.Bundle;
import android.util.Log;
import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.demo.R;
import com.ta.android.base.util.FoxBaseCommonUtils;
import com.ta.android.view.FoxListener;
import com.ta.android.view.FoxStreamerView;

/**
 * 横幅
 */
public class SbannerActivity extends BaseActivity {

    private FoxStreamerView mTMBrAdView;
    private String userId;
    private int slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbanner);
        mTMBrAdView = (FoxStreamerView) findViewById(R.id.TMBrView);
        if (getIntent()!=null){
            userId = getIntent().getStringExtra("userId");
            slotId = getIntent().getIntExtra("slotId", 0);
        }
        mTMBrAdView.setAdListener(new FoxListener() {
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
                Log.d("========", "onClick");
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
        });
        mTMBrAdView.loadAd(slotId,userId);//加载对应GGid
    }

    @Override
    protected void onDestroy() {
        if (mTMBrAdView != null) {
            mTMBrAdView.destroy();
        }
        super.onDestroy();
    }

}
