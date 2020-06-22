package com.mypro.modules.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.mypro.common.constant.CommonConstant;
import com.mypro.common.util.JwtUtil;
import com.mypro.common.util.PasswordUtil;
import com.mypro.common.vo.Result;
import com.mypro.modules.auth.entity.SysLoginModel;
import com.mypro.modules.system.constant.EnumConstant;
import com.mypro.modules.system.entity.SysUser;
import com.mypro.modules.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/noauth")
public class LoginController {

    @Autowired
    ISysUserService sysUserService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public Result<JSONObject> login(SysLoginModel sysLoginModel){
        Result<JSONObject> result = new Result<JSONObject>();
        String username = sysLoginModel.getUsername();
        String password = sysLoginModel.getPassword();

        //1. 校验用户是否有效
        SysUser sysUser = sysUserService.getUserByName(username, null);
        result = (Result<JSONObject>) sysUserService.checkUserIsEffective(sysUser);
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
//        sysBaseAPI.addLog("用户名: " + username + ",登录成功！", CommonConstant.LOG_TYPE_1, null);

        return result;
    }

    /**
     * 用户信息
     *
     * @param sysUser
     * @param result
     * @return
     */
    private Result<JSONObject> userInfo(SysUser sysUser, Result<JSONObject> result) {
        String syspassword = sysUser.getPassword();
        String username = sysUser.getName();
        // 生成token
        String token = JwtUtil.sign(username, syspassword);
//        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        // 设置超时时间
//        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
        // 获取用户部门信息
        JSONObject obj = new JSONObject();
//        List<SysDepart> departs = sysDepartService.queryUserDeparts(sysUser.getId());
//        obj.put("departs", departs);
//        if (departs == null || departs.size() == 0) {
//            obj.put("multi_depart", 0);
//        } else if (departs.size() == 1) {
//            sysUserService.updateUserDepart(username, departs.get(0).getOrgCode());
//            obj.put("multi_depart", 1);
//        } else {
//            obj.put("multi_depart", 2);
//        }
        obj.put("token", token);
        result.setResult(obj);
        result.success("登录成功");
        return result;
    }
}
