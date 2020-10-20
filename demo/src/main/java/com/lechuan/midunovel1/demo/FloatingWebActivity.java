package com.lechuan.midunovel1.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lechuan.midunovel.demo.R;

/**
 * author: likaixuan
 * date: 2020/9/15
 * desc:
 */
public class FloatingWebActivity extends AppCompatActivity {

    private EditText appKeyEdt;
    private EditText appSecretEdt;
    private EditText slotIdEdt;
    private EditText leftEdit;
    private EditText topEdit;
    private EditText rightEdit;
    private EditText bottomEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_web);

        appKeyEdt = findViewById(R.id.edt_app_key);
        appSecretEdt = findViewById(R.id.edt_app_secret);
        slotIdEdt = findViewById(R.id.edt_slot_id);
        leftEdit = findViewById(R.id.edt_left);
        topEdit = findViewById(R.id.edt_top);
        rightEdit = findViewById(R.id.edt_right);
        bottomEdit = findViewById(R.id.edt_bottom);

        findViewById(R.id.btn_load_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appKey = appKeyEdt.getText().toString().trim();
                String appSecret = appSecretEdt.getText().toString().trim();
//                if (TextUtils.isEmpty(appKey)) {
//                    appKey = FoxBaseCommonUtils.getAppKey();
//                }
//                if (TextUtils.isEmpty(appSecret)) {
//                    appSecret= FoxBaseCommonUtils.getAppSecret();
//                }
                String slotIdStr = slotIdEdt.getText().toString().trim();
                int slotId = TextUtils.isEmpty(slotIdStr) ? 0 : Integer.parseInt(slotIdStr);
                String left = leftEdit.getText().toString().trim();
                String top = topEdit.getText().toString().trim();
                String right = rightEdit.getText().toString().trim();
                String bottom = bottomEdit.getText().toString().trim();

                Intent intent = new Intent(FloatingWebActivity.this, FloatTestActivity.class);
                intent.putExtra(FloatTestActivity.BUNDLE_APP_KEY, appKey);
                intent.putExtra(FloatTestActivity.BUNDLE_APP_SECRET, appSecret);
                intent.putExtra(FloatTestActivity.BUNDLE_SLOT_ID, slotId);
                intent.putExtra(FloatTestActivity.BUNDLE_USER_ID, "test-12345");

                intent.putExtra(FloatTestActivity.BUNDLE_LEFT, left);
                intent.putExtra(FloatTestActivity.BUNDLE_TOP, top);
                intent.putExtra(FloatTestActivity.BUNDLE_RIGHT, right);
                intent.putExtra(FloatTestActivity.BUNDLE_BOTTOM, bottom);
                startActivity(intent);
            }
        });
    }
}
