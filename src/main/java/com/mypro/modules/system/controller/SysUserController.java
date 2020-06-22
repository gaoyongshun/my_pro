package com.mypro.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mypro.common.vo.Result;
import com.mypro.modules.system.entity.SysUser;
import com.mypro.modules.system.entity.UserInfo;
import com.mypro.modules.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/sysuser")
public class SysUserController {

    @Autowired
    ISysUserService sysUserService;
    /**
     * 根据条件获取分页数据
     * @param sysUser
     * @return
     */
    @RequestMapping("/findPageData")
    public Result<IPage<SysUser>> findPageData(SysUser sysUser,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize){
        Result<IPage<SysUser>> result = new Result<>();
        //构建分页参数
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(i -> i.like("name",sysUser.getName()));//模糊匹配登录名
        //构建分页对象
        Page<SysUser> page = new Page<>(pageNo,pageSize);
        IPage<SysUser> pageData = sysUserService.page(page,queryWrapper);
        result.setResult(pageData);
        result.isSuccess();
        return result;
    }
}
