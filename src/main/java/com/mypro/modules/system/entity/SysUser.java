package com.mypro.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mypro.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String realName;
    private String salt;
    private String password;
    private String departId;
    private String sex;
    private String email;
    private String phone;
    private String state;
}
