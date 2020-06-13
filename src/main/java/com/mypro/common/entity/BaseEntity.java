package com.mypro.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @TableId(type = IdType.UUID)
    private String id;
    private Date update_time;
    private Date create_time;

}
