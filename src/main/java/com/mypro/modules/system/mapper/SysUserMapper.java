package com.mypro.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mypro.modules.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends  BaseMapper<SysUser>{
    public SysUser getUserByName(@Param("username") String username,@Param("state") String state);
}
