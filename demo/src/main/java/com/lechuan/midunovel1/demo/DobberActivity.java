package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.demo.R;
import com.mediamain.android.base.util.FoxBaseCommonUtils;
import com.mediamain.android.view.FoxWallView;
import com.mediamain.android.view.interfaces.FoxListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 浮标
 */
public class DobberActivity extends BaseActivity {

    private ListView mListView;
    private FoxWallView mOxWallView;
    private String userId;
    private int slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobber);
        if (getIntent()!=null){
            userId = getIntent().getStringExtra("userId");
            slotId = getIntent().getIntExtra("slotId", 0);
        }
        mListView = (ListView) findViewById(R.id.list);
        mOxWallView = (FoxWallView) findViewById(R.id.TMAw1);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        mOxWallView.setAdListener(new FoxListener() {
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

        });
        mOxWallView.loadAd(slotId,userId);
    }

    @Override
    protected void onDestroy() {
        if (mOxWallView != null) {
            mOxWallView.destroy();
        }
        super.onDestroy();
    }

    private List<String> getData() {

        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            data.add("测试数据" + i);
        }
        return data;
    }
}
