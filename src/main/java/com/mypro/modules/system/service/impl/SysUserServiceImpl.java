package com.mypro.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mypro.common.vo.Result;
import com.mypro.modules.system.constant.EnumConstant;
import com.mypro.modules.system.entity.SysUser;
import com.mypro.modules.system.entity.UserInfo;
import com.mypro.modules.system.mapper.SysUserMapper;
import com.mypro.modules.system.mapper.SysUserRoleMapper;
import com.mypro.modules.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements ISysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    /**
     * 根据用户名获取用户
     * @param userName
     * @param state
     * @return
     */
    @Override
    public SysUser getUserByName(String userName,String state){
        return sysUserMapper.getUserByName(userName, state);
    }

    /**
     * 校验用户是否有效
     * @param sysUser
     * @return
     */
    @Override
    public Result<?> checkUserIsEffective(SysUser sysUser) {
        Result<?> result = new Result<Object>();
        //情况1：根据用户信息查询，该用户不存在
        if (sysUser == null) {
            result.error500("该用户不存在，请注册");
            return result;
        }
        //情况2：根据用户信息查询，该用户已注销
        if (EnumConstant.SYS_USER_STATE_DEL.equals(sysUser.getState())) {
//            sysBaseAPI.addLog("用户登录失败，用户名:" + sysUser.getName() + "已注销！", CommonConstant.LOG_TYPE_1, null);
            result.error500("该用户已注销");
            return result;
        }
        //情况3：根据用户信息查询，该用户已冻结
        if (!EnumConstant.SYS_USER_STATE_NORMAL.equals(sysUser.getState())) {
//            sysBaseAPI.addLog("用户登录失败，用户名:" + sysUser.getUsername() + "已冻结！", CommonConstant.LOG_TYPE_1, null);
            result.error500("该用户状态异常,请联系管理员！");
            return result;
        }
        return result;
    }

    @Override
    public Set<String> getUserRolesSet(String username) {
        // 查询用户拥有的角色集合
        List<String> roles = sysUserRoleMapper.getRoleByUserName(username);
        log.info("-------通过数据库读取用户拥有的角色Rules------username： " + username + ",Roles size: " + (roles == null ? 0 : roles.size()));
        return new HashSet<>(roles);
    }

    @Override
    public IPage<UserInfo> findPageData(SysUser sysUser, Integer pageNo, Integer pageSize) {

        return null;
    }
}
