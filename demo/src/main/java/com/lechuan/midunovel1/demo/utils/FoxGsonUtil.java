package com.lechuan.midunovel1.demo.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class FoxGsonUtil {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private FoxGsonUtil() {
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (FoxBaseCommonUtils.isEmpty(gsonString)) {
            return null;
        }
        try {
            if (gson != null) {
                t = gson.fromJson(gsonString, cls);
            }
        } catch (Exception e) {
            return null;
        }

        return t;
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (FoxBaseCommonUtils.isEmpty(gsonString)) {
            return null;
        }
        try {
            if (gson != null) {
                list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    @Deprecated
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (FoxBaseCommonUtils.isEmpty(gsonString)) {
            return null;
        }
        try {
            if (gson != null) {
                map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 对gosn转化的map特殊处理,主要处理int类型
     *
     * @param maps
     */
    public static Map<String, Object> HandleIntMaps(Map<String, Object> maps) {
        if (maps != null) {
            for (String key : maps.keySet()) {
                Object value = maps.get(key);
                //判断是否是 .0 结尾
                boolean isInt = value.toString().substring(value.toString().indexOf(".") + 1, value.toString().length()).equals("0");
                if (value instanceof Double && isInt) {
                    maps.put(key, ((Double) value).intValue());
                } else {
                    maps.put(key, value.toString());
                }
            }
        }
        return maps;
    }

    /**
     * 对gosn转化的map特殊处理,主要处理int类型
     *
     * @param maps
     */
    public static Map<String, Object> HandleSignIntMaps(Map<String, Object> maps) {
        if (maps != null) {
            for (String key : maps.keySet()) {
                Object value = maps.get(key);
                //判断是否是 .0 结尾
                boolean isInt = value.toString().substring(value.toString().indexOf(".") + 1, value.toString().length()).equals("0");
                if (value instanceof Double && isInt) {
                    maps.put(key, ((Double) value).intValue());
                } else {
                    maps.put(key, value);
                }
            }
        }
        return maps;
    }

    /**
     * Map转成json
     *
     * @param o
     */
    public static String MapToGson(Object o) {
        if (gson != null) {
            return gson.toJson(o);
        }
        return "";
    }
}
