package com.lechuan.midunovel.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.lechuan.midunovel.view.tools.FoxLogger;
import com.lechuan.midunovel.view.video.FoxVideoListener;
import com.lechuan.midunovel.view.video.FoxVideoView;

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
                        FoxLogger.jLog().d("onFoxRequestRewardVideo");
                    }

                    @Override
                    public void onFoxAdSuccessed() {
                        FoxLogger.jLog().d("onFoxAdSuccessed");
                    }

                    @Override
                    public void onFoxAdEmpty() {
                        FoxLogger.jLog().d("onFoxAdEmpty");
                    }

                    @Override
                    public void onFoxAdFailed(String response) {
                        FoxLogger.jLog().d("onFoxAdFailed");
                    }

                    @Override
                    public void onFoxAdClick() {
                        FoxLogger.jLog().d("onFoxAdClick");
                    }

                    @Override
                    public void onFoxAdShow() {
                        FoxLogger.jLog().d("onFoxAdShow");
                    }

                    @Override
                    public void onFoxAdClose(String s) {
                        FoxLogger.jLog().d("onFoxAdClose"+s);
                    }

                });
                magicVideoView.openNewVideoTask(VideoActivity.this,true);
            }
        });
    }
}
