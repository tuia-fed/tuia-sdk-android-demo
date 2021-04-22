package com.lechuan.midunovel1.demo;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.mediamain.android.view.base.FoxSDK;

public class SdkApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);
        Stetho.initializeWithDefaults(this);
        FoxSDK.init(this,"4UycwwZv41rwzne1ZXgtQBgDSnPH","3WpyTLfifQyGhvgivxtUjvzXxtkzdceETBU2n5g");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}