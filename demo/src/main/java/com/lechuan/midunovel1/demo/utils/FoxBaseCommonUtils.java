package com.lechuan.midunovel1.demo.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.util.List;

public class FoxBaseCommonUtils {

    /**
     * 判断列表是否为空
     *
     * @param list
     * @return
     */
    public static <T> boolean isEmpty(List<T> list) {
        if (list != null && list.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    public static String getDefaultSaveRootPath(Context context) {
        if (context.getExternalCacheDir() == null) {
            return Environment.getDownloadCacheDirectory().getAbsolutePath();
        } else {
            //noinspection ConstantConditions
            return context.getExternalCacheDir().getAbsolutePath();
        }
    }

}
