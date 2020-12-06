package com.xrca.entity;

import lombok.Data;

/**
 * @author xrca
 * @description 用户信息
 * @date 2020/11/6 22:31
 **/
@Data
public class User {
    private Long userId;

    private String userName;

    private String orgCode;
}
