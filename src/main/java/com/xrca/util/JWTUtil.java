package com.xrca.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xrca
 * @description 工具类
 * @date 2020/11/6 22:31
 **/
public class JWTUtil {
    // 密钥
    private static final String SECRET = "#@#!235451";

    /**
     * @author xrca
     * @description 生成token
     * @date 2020/12/6 14:42
     **/
    public static String generateToken(Map<String, String> map) {
        // 获取build
        JWTCreator.Builder builder = JWT.create();

        // 设置payload
        if (map != null) {
            map.forEach((k, v) -> builder.withClaim(k, v));
        }

        // 设置过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        builder.withExpiresAt(calendar.getTime());

        // 生产token
        String token = builder.sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * @author xrca
     * @description 验证token
     * @date 2020/12/6 14:49
     **/
    public static boolean verifyToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @author xrca
     * @description 从token中获取用户信息
     * @date 2020/12/6 14:50
     **/
    public static Map<String, Object> getUserInfo(String token) {
        DecodedJWT decodedJWT = null;
        Map<String, Object> userInfo = new HashMap<>();
        try {
            decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        if (decodedJWT != null) {
            decodedJWT.getClaims().forEach((k, v) -> {
                if ("exp".equals(k)) {
                    userInfo.put(k, v.asDate());
                } else {
                    userInfo.put(k, v.asString());
                }
            });
        }
        return userInfo;
    }
}
