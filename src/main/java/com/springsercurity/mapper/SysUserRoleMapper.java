package com.springsercurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleMapper> {

    List<String> getRoleName(@Param("userId") String userId);

}
