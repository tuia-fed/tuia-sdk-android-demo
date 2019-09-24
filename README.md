AndroidSDK 对接指南：

#一.依赖引入

1.项目的build.gradle文件中添加

    buildscript {
        repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
        maven { url "https://dl.bintray.com/sunjiangrong/maven" }
    }
        
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        }
    }

    allprojects {
            repositories {
                google()
                jcenter()
                maven { url "https://jitpack.io" }
                maven { url "https://dl.bintray.com/sunjiangrong/maven" }
            }
    }
    
2.app下的build.gradle添加：(最小支持minSdkVersion 15)

    dependencies {
        implementation ('com.tuia:sdk:1.0.0.0'){
        	transitive = true
    	}
    }
    
#二.权限(sdk内部已经处理相关权限问题，如果遇到冲突咨询对应开发即可)

    <uses-permission android:name="android.permission.INTERNET"/>

#三.使用(参考demo中的MainActivity使用)

##1.设置meta-data
    
        <!-- 推啊appkey -->
        <meta-data
            android:name="TUIA_APPKEY"
            android:value="kEzAJT4iRMMag29Z7yWcJGfcVgG" />
        <!-- 推啊appSecret -->
        <meta-data
            android:name="TUIA_APPSECRET"
            android:value="3Wq4afvvdPhyHBR29LgRwEC16gkrrFXZ5BRoL2E" />
    
##2.初始化
    在自定义的Application 的 onCreate方法中，调用以下方法：（详细内容请参考demo中的代码示例）

    FoxSDK.init(this);
    
##3.选择合适的广告类型

###1.开屏广告接入（开屏广告嵌入代码说明（详见demo代码））

####第一步：xml中引入布局文件

    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:background="@drawable/default_image_background">

        <com.lechuan.midunovel.view.FoxShView
            android:id="@+id/TMSh_container"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:countTime="2" />

    </RelativeLayout>

####第二步：代码引入

    public class SplashActivity extends Activity {

    protected FoxShView mOXShView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mOXShView = (FoxShView)findViewById(R.id.TMSh_container);
        mOXShView.setTargetClass(this,MainActivity.class);
        mOXShView.setAdListener(new FoxShListener(){
            @Override
            public void onTimeOut() {
                Log.d("TMShActivity", "onTimeOut");
            }

            @Override
            public void onReceiveAd() {
                Log.d("TMShActivity", "onReceiveAd");
            }

            @Override
            public void onFailedToReceiveAd() {
                Log.d("TMShActivity", "onFailedToReceiveAd");
            }

            @Override
            public void onLoadFailed() {
                Log.d("TMShActivity", "onLoadFailed");
            }

            @Override
            public void onCloseClick() {
                Log.d("TMShActivity", "onCloseClick");
            }

            @Override
            public void onAdClick() {
                Log.d("TMShActivity", "onClick");
            }

            @Override
            public void onAdExposure() {
                Log.d("TMShActivity", "onAdExposure");
            }
            
            @Override
            public void onAdReward(String data) {
                Log.d("========", "onAdReward");
            }
        });
        //传入对应的广告位id和用户id
        mOXShView.loadAd(466,"userId");
    }


        @Override
    protected void onDestroy() {
        if (mOXShView != null) {
            mOXShView.destroy();
        }
        super.onDestroy();
    }
    
    
###2.横幅广告接入（开屏广告嵌入代码说明（详见demo代码））

####第一步：xml中引入布局文件

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/apk/res-auto"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.lechuan.midunovel.view.FoxStreamerView
            android:id="@+id/TMBrView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            tools:fox_size="lander_foxBr"/>

        <View
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@color/background"/>

    </LinearLayout>

    
####第二步：代码引入

    public class SbannerActivity extends BaseActivity {

    private FoxStreamerView mTMBrAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbanner);
        mTMBrAdView = (FoxStreamerView) findViewById(R.id.TMBrView);
        String userId = getIntent().getStringExtra("userId");
        mTMBrAdView.setAdListener(new FoxListener() {
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
                Log.d("========", "onClick");
            }

            @Override
            public void onAdExposure() {
                Log.d("========", "onAdExposure");
            }

            @Override
            public void onAdReward(String data) {
                Log.d("========", "onAdReward");
            }
        });
        //传入对应的广告位id和用户id
        mTMBrAdView.loadAd(308403,userId);
    }

    @Override
    protected void onDestroy() {
        if (mTMBrAdView != null) {
            mTMBrAdView.destroy();
        }
        super.onDestroy();
    }

}



###3.浮标广告接入（开屏广告嵌入代码说明（详见demo代码））

####第一步：xml中引入布局文件

    <?xml version="1.0" encoding="utf-8"?>
    <FrameLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">


        <com.lechuan.midunovel.view.FoxWallView
            android:id="@+id/TMAw1"
            android:layout_width="60dp"
            android:layout_height="60dp"
            android:layout_gravity="center_vertical|right"
            tools:wall_shape="square"/>

    </FrameLayout>
    
####第二步：代码引入

    public class DobberActivity extends BaseActivity {
    private ListView mListView;
    private FoxWallView mOxWallView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobber);
        mOxWallView = (FoxWallView) findViewById(R.id.TMAw1);
        String userId = getIntent().getStringExtra("userId");
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
            public void onAdReward(String data) {
                Log.d("========", "onAdReward");
            }
        });
        //传入对应的广告位id和用户id
        mOxWallView.loadAd(301971,userId);
    }

    @Override
    protected void onDestroy() {
        if (mOxWallView != null) {
            mOxWallView.destroy();
        }
        super.onDestroy();
        }
    }

    
###4.插屏广告接入（开屏广告嵌入代码说明（详见demo代码））
    
####：代码直接引入

    public class InterstitialActivity extends BaseActivity {
    private FoxTbScreen mTMItAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        String userId = getIntent().getStringExtra("userId");
        mTMItAd = new FoxTbScreen(this);
        mTMItAd.setAdListener(new FoxListener() {
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
            public void onAdReward(String data) {
                Log.d("========", "onAdReward");
            }
        });
        //传入对应的广告位id和用户id
        mTMItAd.loadAd(301968,userId);
    }
    @Override
    protected void onDestroy() {
        if (mTMItAd != null) {
            mTMItAd.destroy();
        }
        super.onDestroy();
    }
    }


###5.应用墙广告接入（开屏广告嵌入代码说明（详见demo代码））

####第一步：xml中引入布局文件

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center_vertical"
        android:orientation="horizontal">
        
        <com.lechuan.midunovel.view.FoxWallView
            android:id="@+id/app1"
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:scaleType="centerCrop"
            tools:wall_shape="circular"/>

    </LinearLayout>

    
####第二步：代码引入

    public class AppWallActivity extends BaseActivity {
    private FoxWallView mOxWallView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_wall);
        mOxWallView1 = (FoxWallView) findViewById(R.id.app1);
        String userId = getIntent().getStringExtra("userId");
        mOxWallView1.setAdListener(new FoxListener() {
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
            public void onAdReward(String data) {
                Log.d("========", "onAdReward");
            }
        });
        mOxWallView2.loadAd(301972,userId);
    }

    @Override
    protected void onDestroy() {
        if (mOxWallView1 != null) {
            mOxWallView1.destroy();
        }
        if (mOxWallView2 != null) {
            mOxWallView2.destroy();
        }
        super.onDestroy();
    }
    }


    
###6.Banner广告接入（开屏广告嵌入代码说明（详见demo代码））

####第一步：xml中引入布局文件

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <com.lechuan.midunovel.view.FoxStreamerView
            android:id="@+id/TMBrView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            tools:fox_size="foxBr" />

    </LinearLayout>
    
###第二步：代码引入

    public class BannerActivity extends BaseActivity {
    private FoxStreamerView mTMBrAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        String userId = getIntent().getStringExtra("userId");
        mTMBrAdView = (FoxStreamerView) findViewById(R.id.TMBrView);
        mTMBrAdView.setAdListener(new FoxListener() {
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
                Log.d("========", "onExposure");
            }

            @Override
            public void onAdReward(String data) {
                Log.d("========", "onAdReward");
            }
        });
        mTMBrAdView.loadAd(301970,userId);
    }

    @Override
    protected void onDestroy() {
        if (mTMBrAdView != null) {
            mTMBrAdView.destroy();
        }
        super.onDestroy();
        }
    }


            
####.返回奖励信息（激励类型广告使用，非激励类型广告不需要）


    {
	    "finishType" : 1,
	    "orderId" : "168408070629",
	    "userId" : "123",
	    "timestamp" : "1566791113031",
	    "prizeFlag" : "XXX",
	    "appKey" : "4W8ReCvDm4fy3Fpn52MgPgUWmdfS",
	    "sign" : "5093659d6bf802d1a407df81d6aab9f9",
	    "score" : null,
	    "reason" : "-1",
	    "url" : null
    } 

| 名称 | 类型 | 备注 |
| :---------------------: | :---------------------: | :----------------------: |
| userId | String | 用户 id，用户在媒体的唯一识别信息，来源于活动链接中的&userId=xxx，由媒体拼接提供 |
| timestamp | Number | 时间戳 |
| prizeFlag | String | 常量，请求上报的奖励在对接方媒体系统内的奖励标识，用于标识具体的奖励类型，由媒体提供 |
| orderId | String | 推啊订单号，具有唯一性，幂等由媒体保障 |
| appKey | String | 媒体公钥 |
| sign | String | 签名 |
| score | Number | 如果是数值类型的奖励，则同时请求充值对应的数值 score，比如积分、金币、倍数 |
    
四.混淆

    -ignorewarnings
    -dontwarn com.lechuan.midunovel.view.**
    -keep class com.lechuan.midunovel.view.** { *; }

五.对接问题

    咨询对应开发
    
