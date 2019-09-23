package com.lechuan.midunovel.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String userId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.sTMBrButton).setOnClickListener(this);
        findViewById(R.id.TMItButton258092).setOnClickListener(this);
        findViewById(R.id.TMBrButton).setOnClickListener(this);
        findViewById(R.id.floatButton).setOnClickListener(this);
        findViewById(R.id.appWButton).setOnClickListener(this);
        findViewById(R.id.nsButton).setOnClickListener(this);
        userId = getIntent().getStringExtra("userId");

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.sTMBrButton:
                //横幅
                intent = new Intent(this, SbannerActivity.class);
                break;
            case R.id.TMItButton258092:
                //插屏
                intent = new Intent(this, InterstitialActivity.class);
                break;
            case R.id.TMBrButton:
                //Banner
                intent = new Intent(this, BannerActivity.class);
                break;
            case R.id.appWButton:
                //积分墙
                intent = new Intent(this, AppWallActivity.class);
                break;
            case R.id.floatButton:
                //浮标
                intent = new Intent(this, DobberActivity.class);
                break;
            case R.id.nsButton:
                //自定义
                intent = new Intent(this, NonStandarActivity.class);
                break;
            default:
                return;
        }
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}
