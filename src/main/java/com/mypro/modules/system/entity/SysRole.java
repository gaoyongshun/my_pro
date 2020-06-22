package com.mypro.modules.system.entity;

import com.mypro.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole extends BaseEntity implements Serializable {

    private String name;

    private String roleCode;
}
