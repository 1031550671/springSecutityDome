package com.springsercurity.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_user_role")
public class SysUserRole {

    @TableId(type=IdType.ASSIGN_UUID)
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("role_id")
    private String roleId;

}
