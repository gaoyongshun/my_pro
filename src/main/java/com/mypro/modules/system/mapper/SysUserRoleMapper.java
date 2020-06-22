package com.mypro.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mypro.modules.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    @Select("select role_code from sys_role where id in (select role_id from sys_user_role where sys_user_id = (select id from sys_user where name=#{username}))")
    List<String> getRoleByUserName(String username);
}
