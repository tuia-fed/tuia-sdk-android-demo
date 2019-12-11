package com.lechuan.midunovel1.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.video.FoxVideoListener;
import com.lechuan.midunovel.view.video.FoxVideoView;
import com.lechuan.midunovel.view.video.util.CommonUtils;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        findViewById(R.id.btnVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoxVideoView magicVideoView =new FoxVideoView(getApplication(),
                        "91200183954567", "", "312152", "", new FoxVideoListener() {
                    @Override
                    public void onFoxRequestRewardVideo() {
                    }

                    @Override
                    public void onFoxAdSuccessed() {
                    }

                    @Override
                    public void onFoxAdEmpty() {
                    }

                    @Override
                    public void onFoxAdFailed(String response) {
                    }

                    @Override
                    public void onFoxAdClick() {
                    }

                    @Override
                    public void onFoxAdShow() {
                    }

                    @Override
                    public void onFoxAdClose(String s) {
                        if (!CommonUtils.isEmpty(s)){
                            ToastUtils.showShort(s);
                        }
                    }

                });
                magicVideoView.openNewVideoTask(VideoActivity.this,true);
            }
        });
    }
}
