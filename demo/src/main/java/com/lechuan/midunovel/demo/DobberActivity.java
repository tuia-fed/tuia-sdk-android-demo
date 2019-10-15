package com.lechuan.midunovel.demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lechuan.midunovel.view.FoxListener;
import com.lechuan.midunovel.view.FoxWallView;

import java.util.ArrayList;
import java.util.List;

public class DobberActivity extends BaseActivity {

    private ListView mListView;
    private FoxWallView mOxWallView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobber);
        mListView = (ListView) findViewById(R.id.list);
        mOxWallView = (FoxWallView) findViewById(R.id.TMAw1);
        String userId = getIntent().getStringExtra("userId");
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        mOxWallView.setAdListener(new FoxListener() {
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
            }

        });
        mOxWallView.loadAd(301971,userId);
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
