package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel1.demo.utils.FoxBaseCommonUtils;
import com.lechuan.midunovel1.demo.utils.FoxGsonUtil;
import com.mediamain.android.view.FoxCustomerTm;
import com.mediamain.android.view.bean.FoxResponseBean;
import com.mediamain.android.view.bean.MessageData;
import com.mediamain.android.view.interfaces.FoxNsTmListener;


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
 *     mOxCustomerTm.openFoxActivity(mDataBean.getActivityUrl());
 */
public class NonStandarActivity extends BaseActivity {
    private FoxCustomerTm mOxCustomerTm;

    private TextView textView;
    private FoxResponseBean.DataBean mDataBean;
    private String userId;
    private int slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_standar);
        textView = (TextView)findViewById(R.id.content_text);
        if (getIntent()!=null){
            userId = getIntent().getStringExtra("userId");
            slotId = getIntent().getIntExtra("slotId", 0);
        }

        mOxCustomerTm = new FoxCustomerTm(this);
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
                //展示使用
                textView.setText(result);
            }

            @Override
            public void onFailedToReceiveAd(int errorCode, String errorMsg) {
                Log.d("========", "onFailedToReceiveAd  Message="+errorMsg);
            }

            @Override
            public void onAdActivityClose(String s) {
                Log.d("========", "onAdActivityClose"+s);
                if (!FoxBaseCommonUtils.isEmpty(s)){
                    ToastUtils.showShort(s);
                }
            }

            @Override
            public void onAdMessage(MessageData messageData) {

            }

        });
        //第三个参数  //isimageUrl  1 使用素材(默认为1)   0不使用素材
        // mOxCustomerTm.loadAd(slotId, userId,isimageUrl);
        mOxCustomerTm.loadAd(slotId,userId,1);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOxCustomerTm!=null && mDataBean!=null && !FoxBaseCommonUtils.isEmpty(mDataBean.getActivityUrl())){
                    //素材点击时候调用素材点击曝光方法
                    mOxCustomerTm.adClicked();
                    mOxCustomerTm.openFoxActivity(mDataBean.getActivityUrl());
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
