package com.mypro.modules.system.entity;

/**
 * 用于前端显示用户信息
 * 防止重要信息泄露 例如：密码等
 */

import lombok.Data;

@Data
public class UserInfo {
    private String Id;
    private String name;
    private String sex;
    private String departId;
    private String phone;
    private String email;
}
