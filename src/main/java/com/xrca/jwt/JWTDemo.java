package com.xrca.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xrca.util.JWTUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xrca
 * @description 示例
 * @date 2020/11/6 22:31
 **/
public class JWTDemo {
    private static final String SECRET = "#@#!235451";
    /**
     * @author xrca
     * @description 生成token
     * @date 2020/12/6 14:18
     **/
    @Test
    public void generateToken() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 60);

        String token = JWT.create()
                .withClaim("userId", 1293L) // 添加payload
                .withClaim("orgCode", "3201")
                .withExpiresAt(calendar.getTime()) // 过期时间
                .sign(Algorithm.HMAC256(SECRET));

        System.out.println(token);
    }

    /**
     * @author xrca
     * @description 获取token
     * @date 2020/12/6 14:26
     **/
    @Test
    public void getToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcmdDb2RlIjoiMzIwMSIsImV4cCI6MTYwNzQ1MjU0NSwidXNlcklkIjoxMjkzfQ.9czEgL1Xlz9eukB1A5LNmQ2dtIdz4D2O4V9qBi9FRiY";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        // 获取payload
        System.out.println(decodedJWT.getClaim("userId").asLong());
        System.out.println(decodedJWT.getClaim("orgCode").asString());

        // 获取过期时间
        System.out.println(decodedJWT.getExpiresAt());

        // 获取token
        System.out.println(decodedJWT.getToken());

        // 获取算法
        System.out.println(decodedJWT.getAlgorithm());
    }

    /**
     * @author xrca
     * @description 测试JWT工具类
     * @date 2020/12/6 14:55
     **/
    @Test
    public void testJWTUtil() {
        Map<String, String> user = new HashMap<>();
        user.put("userId", "34321");
        user.put("userName", "xrca");
        user.put("orgCode", "3201");

        // 生产token
        String token = JWTUtil.generateToken(user);
        System.out.println(token);

        // 修改token
        token = token + "21d";


        //严重token
        boolean valid = JWTUtil.verifyToken(token);
        System.out.println(valid);

        // 获取用户信息
        Map<String, Object> userInfo = JWTUtil.getUserInfo(token);
        System.out.println(userInfo);
    }
}