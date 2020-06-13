package com.mypro.modules.system.service;

import com.mypro.common.vo.Result;
import com.mypro.modules.system.entity.SysUser;

public interface ISysUserService {

    public SysUser getUserByName(String userName,String state);

    public Result<?> checkUserIsEffective(SysUser sysUser) ;
}
