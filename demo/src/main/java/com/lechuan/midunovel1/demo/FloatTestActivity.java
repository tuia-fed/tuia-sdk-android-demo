package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.lechuan.midunovel.demo.R;
import com.mediamain.android.view.bean.MessageData;
import com.mediamain.android.view.holder.FoxFloatingWebHolder;
import com.mediamain.android.view.holder.FoxNativeAdHelper;

/**
 * 模拟媒体Activity：显示悬浮升级
 */
public class FloatTestActivity extends AppCompatActivity {

    public static final String BUNDLE_APP_KEY = "bundle_app_key";
    public static final String BUNDLE_APP_SECRET = "bundle_app_secret";
    public static final String BUNDLE_SLOT_ID = "bundle_slot_id";
    public static final String BUNDLE_USER_ID = "bundle_user_id";
    public static final String BUNDLE_LEFT = "bundle_left";
    public static final String BUNDLE_TOP = "bundle_top";
    public static final String BUNDLE_RIGHT = "bundle_right";
    public static final String BUNDLE_BOTTOM = "bundle_bottom";

    private FoxFloatingWebHolder holder;

    private String appKey;
    private String appSecret;
    private int slotId;
    private String userId;
    // 单位dp
    private String left;
    private String top;
    private String right;
    private String bottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_test);

        holder = FoxNativeAdHelper.getFoxFloatingWebHolder();
        holder.setFloatingHost(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            appKey = bundle.getString(BUNDLE_APP_KEY);
            appSecret = bundle.getString(BUNDLE_APP_SECRET);

            slotId = bundle.getInt(BUNDLE_SLOT_ID);
            userId = bundle.getString(BUNDLE_USER_ID);

            left = bundle.getString(BUNDLE_LEFT);
            top = bundle.getString(BUNDLE_TOP);
            right = bundle.getString(BUNDLE_RIGHT);
            bottom = bundle.getString(BUNDLE_BOTTOM);
        }


        // 换成你的应用appKey, appSecret
        holder.setConfigInfo(appKey, appSecret);

        requestFloatingWebAd();

        // 测试WebView是否屏蔽了Activity 其他区域的点击事件
        findViewById(R.id.btn_float_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatTestActivity.this, "Refresh Button clicked!", Toast.LENGTH_SHORT).show();
                // refresh: 先destroy, 再请求
                holder.destroy();
                requestFloatingWebAd();
            }
        });
        findViewById(R.id.btn_hint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.hide();
            }
        });
    }

    private void requestFloatingWebAd() {
        holder.loadFloatingWebAd(slotId, userId, left, top, right, bottom, new FoxFloatingWebHolder.FloatingWebAdLoadListener() {
            @Override
            public void onLoadSuccess() {

            }

            @Override
            public void onLoadFailed(int code, String message) {
                Toast.makeText(FloatTestActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClick() {
                //素材点击
            }

            @Override
            public void onCloseClick() {
                //素材关闭
            }

            @Override
            public void onAdActivityClose(String s) {
                //活动点击
            }

            @Override
            public void onAdMessage(MessageData messageData) {

            }

        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isConsumed = false;
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (holder != null) {
                isConsumed = holder.goBack();
            }
        }
        if (!isConsumed) {
            return super.onKeyDown(keyCode, event);
        }
        return isConsumed;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (holder != null) {
            holder.destroy();
        }
    }
}