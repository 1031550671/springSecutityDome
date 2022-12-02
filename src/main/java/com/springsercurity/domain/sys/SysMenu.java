package com.springsercurity.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName("sys_menu")
public class SysMenu {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "menu_name")
    private String menuName;


    @TableField(value = "parent_id")
    private Integer parentId;

//    @TableField(value = "interface_code")
//    private String interfaceCode;

//    @TableField(value = "url")
//    private String url;


    @TableField(value = "menu_type")
    private Integer menuType;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "rank")
    private int rank;


    @TableField(exist = false)
    private List<SysMenu> list;


    @TableField("code")
    private String code;
}
