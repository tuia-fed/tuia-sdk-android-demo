package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lechuan.midunovel.base.util.FoxBaseCommonUtils;
import com.lechuan.midunovel.view.FoxActivity;
import com.lechuan.midunovel.view.FoxCustomerTm;
import com.lechuan.midunovel.view.FoxNsTmListener;
import com.lechuan.midunovel.view.video.Constants;
import com.lechuan.midunovel.view.video.bean.FoxResponseBean;
import com.lechuan.midunovel.view.video.utils.FoxGsonUtil;


/**
 * 自定义广告：
 * 1.媒体方自己处理：
 *     素材曝光完成的时候调用 mOxCustomerTm.adExposed()
 *     素材点击的时候调用 mOxCustomerTm.adClicked()
 *     同时需要支持webview加载活动url-设置webview的下载监听-支持下载安装行为
 *
 * 2.SDK内部处理：
 *     素材展示媒体自己加载并在加载成功时调用素材曝光mOxCustomerTm.adExposed()，
 *     素材点击请调用mOxCustomerTm.adClicked()，同时传入返回的活动链接url调用
 *     FoxActivity.starActivity(mContext,url,Constants.BUNDLE_KEY_FROM_FOXCUSTOMERTM)
 */
public class NonStandarActivity extends BaseActivity {
    private FoxCustomerTm mOxCustomerTm;

    private TextView textView;
    private FoxResponseBean.DataBean mDataBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_standar);
        textView = (TextView)findViewById(R.id.content_text);
        String userId = getIntent().getStringExtra("userId");

        mOxCustomerTm = new FoxCustomerTm(this);

        mOxCustomerTm.loadAd(304507,userId);

        mOxCustomerTm.setAdListener(new FoxNsTmListener() {
            @Override
            public void onReceiveAd(String result) {
                Log.d("========", "onReceiveAd:"+result);
                if (!FoxBaseCommonUtils.isEmpty(result)){
                    FoxResponseBean.DataBean dataBean = FoxGsonUtil.GsonToBean(result,FoxResponseBean.DataBean.class);
                    if (dataBean!=null){
                        mDataBean = dataBean;
                    }
                    //素材加载成功时候调用素材加载曝光方法
                    mOxCustomerTm.adExposed();
                }
                textView.setText(result);
            }

            @Override
            public void onFailedToReceiveAd() {
                Log.d("========", "onFailedToReceiveAd");
            }

            @Override
            public void onAdActivityClose(String s) {

            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataBean!=null && !FoxBaseCommonUtils.isEmpty(mDataBean.getActivityUrl())){
                    //素材点击时候调用素材点击曝光方法
                    mOxCustomerTm.adClicked();
                    FoxActivity.starActivity(NonStandarActivity.this,mDataBean.getActivityUrl(),Constants.BUNDLE_KEY_FROM_FOXCUSTOMERTM);
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        if (mOxCustomerTm != null) {
            mOxCustomerTm.destroy();
        }
        super.onDestroy();
    }
}
