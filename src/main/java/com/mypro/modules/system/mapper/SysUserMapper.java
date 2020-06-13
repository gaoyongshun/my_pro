package com.mypro.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mypro.modules.system.entity.SysUser;

public interface SysUserMapper extends  BaseMapper<SysUser>{
    public SysUser getUserByName(String username,String state);
}
