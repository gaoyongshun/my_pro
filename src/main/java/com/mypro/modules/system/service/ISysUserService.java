package com.mypro.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mypro.common.vo.Result;
import com.mypro.modules.system.entity.SysUser;
import com.mypro.modules.system.entity.UserInfo;

import java.util.Set;

public interface ISysUserService extends IService<SysUser> {

    public SysUser getUserByName(String userName,String state);

    public Result<?> checkUserIsEffective(SysUser sysUser) ;

    Set<String> getUserRolesSet(String username);

    IPage<UserInfo> findPageData(SysUser sysUser, Integer pageNo, Integer pageSize);
}
