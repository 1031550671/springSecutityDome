package com.springsercurity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@TableName("user")
public class User implements Serializable {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("name")
    private String name;

    @TableField("code")
    private String code;

//    @ApiModelProperty("角色类型-> 1:项目管理者2:管理员3:普通用户")
    @TableField("type")
    private String type;

    @TableField("phone")
    private String phone;

//    @NotEmpty(message="密码不能为空")
//    @Length(max = 50,message = "密码长度不能超过50")
//    @ApiModelProperty("密码")
    @TableField("pwd")
    private String pwd;


    @TableField("cs_id")
    private String csId;

    @TableField("describes")
    private String describes;

    @TableField("create_id")
    private String createId;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @TableField("update_by_id")
    private String updateById;

    @TableField("deleted")
    private Boolean deleted;
}
