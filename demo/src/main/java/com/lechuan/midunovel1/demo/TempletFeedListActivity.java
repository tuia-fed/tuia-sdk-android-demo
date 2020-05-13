package com.lechuan.midunovel1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lechuan.midunovel.base.util.FoxBaseCommonUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.feed.IFoxTempletInfoFeedAd;
import com.lechuan.midunovel.view.holder.FoxNativeAdHelper;
import com.lechuan.midunovel.view.holder.FoxTempletInfoFeedHolder;
import com.lechuan.midunovel1.demo.widget.ILoadMoreListener;
import com.lechuan.midunovel1.demo.widget.LoadMoreListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 信息流Demo（ListView 模板渲染：支持开发者进行微调）
 */
@SuppressWarnings("ALL")
public class TempletFeedListActivity extends Activity {
    private static final String TAG = "NativeFeedListActivity";

    private static final int AD_POSITION = 3;
    private static final int LIST_ITEM_COUNT = 20;
    private LoadMoreListView mListView;
    private EditText editAdImagewidth;
    private EditText editAdImageHeight;
    private MyAdapter myAdapter;
    private List<IFoxTempletInfoFeedAd> mData;

    private FoxTempletInfoFeedHolder mFoxTempletInfoFeedHolder;

    private String userId;
    private int slotId;
    private int imageWidth = 0;
    private int imageHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_feed_listview);
        mFoxTempletInfoFeedHolder = FoxNativeAdHelper.getFoxTempletInfoFeedHolder();

        final EditText editAppkey = (EditText) findViewById(R.id.editAppkey);
        final EditText editAppSecret = (EditText) findViewById(R.id.editAppSecret);
        final EditText editAdSlotid = (EditText) findViewById(R.id.editAdSlotid);
        editAdImagewidth = (EditText) findViewById(R.id.editAdImageWidth);
        editAdImageHeight = (EditText) findViewById(R.id.editAdImageHeight);

        if (getIntent() != null) {
            userId = getIntent().getStringExtra("userId");
            slotId = getIntent().getIntExtra("slotId", 0);
        }
        Button btnRequest = (Button) findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mData.clear();
                    myAdapter.notifyDataSetChanged();
                    try {
                        imageWidth = Integer.valueOf(editAdImagewidth.getText().toString());
                        imageHeight = Integer.valueOf(editAdImageHeight.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    mListView.setVisibility(View.VISIBLE);
                    mFoxTempletInfoFeedHolder.setConfigInfo(editAppkey.getText().toString().trim(), editAppSecret.getText().toString().trim());
                    final String mSlotId = editAdSlotid.getText().toString().trim();
                    if (!FoxBaseCommonUtils.isEmpty(mSlotId)) {
                        slotId = Integer.parseInt(mSlotId);
                    }

                    loadListAd();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        initListView();
    }

    @SuppressWarnings("RedundantCast")
    private void initListView() {
        mListView = (LoadMoreListView) findViewById(R.id.lmlv_native_feed);
        mData = new ArrayList<>();
        myAdapter = new MyAdapter(this, mData);
        mListView.setAdapter(myAdapter);
        mListView.setLoadMoreListener(new ILoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadListAd();
            }
        });
    }

    /**
     * 加载feed广告
     */
    private void loadListAd() {

        //请求广告   注：下载类广告Activity必须传
        mFoxTempletInfoFeedHolder.loadInfoAd(this, slotId, userId, new FoxTempletInfoFeedHolder.LoadInfoAdListener() {
            @Override
            public void onError(String errorBody) {
                Log.e(TAG, "error:" + errorBody);
            }

            @Override
            public void infoAdSuccess(List<IFoxTempletInfoFeedAd> foxInfoAds) {
                if (mListView != null) {
                    mListView.setLoadingFinish();
                }

                if (FoxBaseCommonUtils.isEmpty(foxInfoAds)) {
                    return;
                }

                for (int i = 0; i < LIST_ITEM_COUNT; i++) {
                    mData.add(null);
                }

                int count = mData.size();
                for (IFoxTempletInfoFeedAd ad : foxInfoAds) {
                    int random = (int) (Math.random() * LIST_ITEM_COUNT) + count - LIST_ITEM_COUNT;
                    //支持开发者进行布局微调
                    if (imageWidth != 0 || imageHeight != 0) {
                        ad.setImageSize(imageWidth, imageHeight);
                    }
//                    ad.setImageMargin(0, 0, 0, 0);
//                    ad.setTextSize(16);
//                    ad.setTextColor(R.color.base_red);
                    mData.set(random, ad);
                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onReceiveAd() {
                Log.e(TAG, "onReceiveAd()");
            }

            @Override
            public void onFailedToReceiveAd() {
                Log.e(TAG, "onFailedToReceiveAd()");
            }

            @Override
            public void onLoadFailed() {
                Log.e(TAG, "onLoadFailed()");
            }

            @Override
            public void onCloseClick() {
                Log.e(TAG, "onCloseClick()");
            }

            @Override
            public void onAdClick() {
                Log.e(TAG, "onAdClick()");
            }

            @Override
            public void onAdExposure() {
                Log.e(TAG, "onAdExposure()");
            }

            @Override
            public void onAdActivityClose(String data) {
                Log.e(TAG, "onAdActivityClose()=回调内容：" + data);
                if (!FoxBaseCommonUtils.isEmpty(data)) {
                    Toast.makeText(TempletFeedListActivity.this, "奖励内容：" + data, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @SuppressWarnings("CanBeFinal")
    private static class MyAdapter extends BaseAdapter {

        private static final int ITEM_VIEW_TYPE_NORMAL = 0;
        private static final int ITEM_VIEW_TYPE_AD = 1;

        private List<IFoxTempletInfoFeedAd> mData;
        private Context mContext;

        public MyAdapter(Context context, List<IFoxTempletInfoFeedAd> data) {
            this.mContext = context;
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public IFoxTempletInfoFeedAd getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        //信息流广告的样式，有左图，右图，组图，大图（模板渲染支持微调）
        @Override
        public int getItemViewType(int position) {
            IFoxTempletInfoFeedAd ad = getItem(position);
            if (null != ad && ad.getSpecType() > 0) {
                return ITEM_VIEW_TYPE_AD;
            } else {
                return ITEM_VIEW_TYPE_NORMAL;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 5;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            IFoxTempletInfoFeedAd ad = getItem(position);
            if (getItemViewType(position) == ITEM_VIEW_TYPE_AD) {
                return getTempletInfoFeedView(convertView, parent, position);
            } else {
                return getNormalView(convertView, parent, position);
            }
        }


        @SuppressWarnings("RedundantCast")
        private View getTempletInfoFeedView(View convertView, ViewGroup parent, int position) {
            AdViewHolder normalViewHolder;
            if (convertView == null) {
                normalViewHolder = new AdViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_info_feed, parent, false);
                normalViewHolder.mContainor = (FrameLayout) convertView.findViewById(R.id.fl_container);
                convertView.setTag(normalViewHolder);
            } else {
                normalViewHolder = (AdViewHolder) convertView.getTag();
            }
            IFoxTempletInfoFeedAd tempAd = getItem(position);
            if (tempAd.getView().getParent() instanceof ViewGroup) {
                ((ViewGroup) tempAd.getView().getParent()).removeView(tempAd.getView());
            }
            normalViewHolder.mContainor.removeAllViews();
            normalViewHolder.mContainor.addView(tempAd.getView());
            return convertView;
        }

        /**
         * 非广告list
         *
         * @param convertView
         * @param parent
         * @param position
         * @return
         */
        @SuppressWarnings("RedundantCast")
        private View getNormalView(View convertView, ViewGroup parent, int position) {
            NormalViewHolder normalViewHolder;
            if (convertView == null) {
                normalViewHolder = new NormalViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_normal, parent, false);
                normalViewHolder.idle = (TextView) convertView.findViewById(R.id.tv_normal);
                convertView.setTag(normalViewHolder);
            } else {
                normalViewHolder = (NormalViewHolder) convertView.getTag();
            }
            normalViewHolder.idle.setText("ListView item " + position);
            return convertView;
        }

        private static class AdViewHolder {
            FrameLayout mContainor;
        }

        private static class NormalViewHolder {
            TextView idle;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //重要：这个资源销毁一定要加否则会造成内存泄漏
        if (null != mFoxTempletInfoFeedHolder) {
            mFoxTempletInfoFeedHolder.destroy();
        }
    }
}
