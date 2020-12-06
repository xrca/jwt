package com.xrca.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xrca
 * @description 用户上下文
 * @date 2020/11/6 22:31
 **/
public class UserContext {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    /**
     * @author xrca
     * @description 放入值
     * @date 2020/12/6 16:05
     **/
    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    /**
     * @author xrca
     * @description 获取值
     * @date 2020/12/6 16:06
     **/
    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    /**
     * @author xrca
     * @description 设置map
     * @date 2020/12/6 16:07
     **/
    public static void setMap(Map<String, Object> map) {
        threadLocal.set(map);
    }
}
