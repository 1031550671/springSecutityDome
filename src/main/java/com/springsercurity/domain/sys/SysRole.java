package com.springsercurity.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_role")
public class SysRole {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("name")
    private String name;
    @TableField("type")
    private String type;
}
