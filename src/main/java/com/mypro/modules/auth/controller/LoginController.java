package com.mypro.modules.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.mypro.common.vo.Result;
import com.mypro.modules.auth.entity.SysLoginModel;
import com.mypro.modules.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/noauth")
public class LoginController {

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public Result<JSONObject> login(SysLoginModel sysLoginModel){
        Result<JSONObject> result = new Result<JSONObject>();
        String username = sysLoginModel.getUsername();
        String password = sysLoginModel.getPassword();
        //步骤1：TODO 前端密码加密，后端进行密码解密，防止传输密码篡改等问题，不配就直接提示密码错误，并记录日志后期进行统计分析是否锁定
        password = AesEncryptUtil.desEncrypt(sysLoginModel.getPassword()).trim();//密码解密
        //1. 校验用户是否有效
        SysUser sysUser = sysUserService.getUserByName(username);
        result = sysUserService.checkUserIsEffective(sysUser);
        if(!result.isSuccess()) {
            return result;
        }

        //2. 校验用户名或密码是否正确
        String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
        String syspassword = sysUser.getPassword();
        if (!syspassword.equals(userpassword)) {
            result.error500("用户名或密码错误");
            return result;
        }

        //用户登录信息
        userInfo(sysUser, result);
        sysBaseAPI.addLog("用户名: " + username + ",登录成功！", CommonConstant.LOG_TYPE_1, null);

        return result;
    }
}
