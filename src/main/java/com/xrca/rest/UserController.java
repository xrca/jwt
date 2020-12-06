package com.xrca.rest;

import com.xrca.entity.User;
import com.xrca.util.JWTUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xrca
 * @description TODO
 * @date 2020/11/6 22:31
 **/
@RestController
@RequestMapping("/")
public class UserController {

    @PostMapping("login")
    public Map<String, Object> login(String userName, String password) {
        // 模拟校验用户密码，并查询用户信息
        User user = new User();
        user.setUserId(231L);
        user.setUserName("xrca");
        user.setOrgCode("3201");

        Map<String, String> userInfo = new HashMap<>(3);
        userInfo.put("userId", user.getUserId().toString());
        userInfo.put("userName", user.getUserName());
        userInfo.put("orgCode", user.getOrgCode());

        String token = JWTUtil.generateToken(userInfo);

        Map<String, Object> resp = new HashMap<>();
        resp.put("code", 200);
        resp.put("token", token);
        return resp;
    }
}
