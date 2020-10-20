package com.lechuan.midunovel1.demo.utils;

import android.content.Context;
import android.widget.Toast;

public final class FoxBaseToastUtils {

    public static void showShort(Context context, CharSequence text) {
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

}


