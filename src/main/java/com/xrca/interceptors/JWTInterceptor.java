package com.xrca.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xrca.context.UserContext;
import com.xrca.util.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xrca
 * @description JWT拦截器
 * @date 2020/11/6 22:31
 **/
public class JWTInterceptor implements HandlerInterceptor {
    /**
     * @author xrca
     * @description 预处理，解析jwt
     * @date 2020/12/6 15:27
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        boolean tokenValid = JWTUtil.verifyToken(token);
        if (!tokenValid) {
            Map<String, String> resp = new HashMap<>();
            resp.put("code", "500");
            resp.put("msg", "token失效");
            String json = new ObjectMapper().writeValueAsString(resp);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
            return false;
        }
        // 将用户信息放入上下文
        Map<String, Object> userInfo = JWTUtil.getUserInfo(token);
        UserContext.setMap(userInfo);
        return true;
    }
}
