package com.springsercurity.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_role_menu")
public class SysRoleMenu {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("role_id")
    private String roleId;
    @TableField("menu_id")
    private String menuId;
}
