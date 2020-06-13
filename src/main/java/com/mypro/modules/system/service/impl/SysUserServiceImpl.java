package com.mypro.modules.system.service.impl;

import com.mypro.modules.system.entity.SysUser;
import com.mypro.modules.system.mapper.SysUserMapper;
import com.mypro.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    public SysUser getUserByName(String userName){
        return sysUserMapper.getUserByName(userName,);
    }

}
