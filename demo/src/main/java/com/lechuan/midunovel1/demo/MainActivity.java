package com.lechuan.midunovel1.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lechuan.midunovel.demo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String userId = "test-123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.sTMSplashButton).setOnClickListener(this);
        findViewById(R.id.sTMBrButton).setOnClickListener(this);
        findViewById(R.id.sTMInfoButton).setOnClickListener(this);
        findViewById(R.id.TMItButton258092).setOnClickListener(this);
        findViewById(R.id.TMBrButton).setOnClickListener(this);
        findViewById(R.id.floatButton).setOnClickListener(this);
        findViewById(R.id.nsButton).setOnClickListener(this);
        findViewById(R.id.nsCPButton).setOnClickListener(this);
        findViewById(R.id.nsCP2Button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.sTMSplashButton:
                //横幅
                intent = new Intent(this, SplashActivity.class);
                intent.putExtra("slotId", 330487);
                break;
            case R.id.sTMBrButton:
                //横幅
                intent = new Intent(this, SbannerActivity.class);
                intent.putExtra("slotId", 323777);
                break;
            case R.id.sTMInfoButton:
                //横幅
                intent = new Intent(this, NativeAdActivity.class);
                intent.putExtra("slotId", 331035);
                break;
            case R.id.TMItButton258092:
                //插屏
                intent = new Intent(this, InterstitialActivity.class);
                intent.putExtra("slotId", 323776);
                break;
            case R.id.TMBrButton:
                //Banner
                intent = new Intent(this, BannerActivity.class);
                intent.putExtra("slotId", 323778);
                break;
            case R.id.floatButton:
                //浮标
                intent = new Intent(this, DobberActivity.class);
                intent.putExtra("slotId", 323779);
                break;
            case R.id.nsButton:
                //自定义
                intent = new Intent(this, NonStandarActivity.class);
                intent.putExtra("slotId", 323780);
                break;
            case R.id.nsCPButton:
                //自定义
                intent = new Intent(this, NativeInterstitialActivity.class);
                intent.putExtra("slotId", 331946);
                break;
            case R.id.nsCP2Button:
                //自定义
                intent = new Intent(this, NativeInterstitial2Activity.class);
                intent.putExtra("slotId", 331946);
                break;
            default:
                return;
        }
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}
