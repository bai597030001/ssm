package com.sample.ssm.mapper;

import com.sample.ssm.model.Permission;

import java.util.List;

/**
 * @program: springmvc-demo
 * @description: 权限mapper
 * @author: baijd-a
 * @create: 2020-07-15 16:59
 **/
public interface PermissionMapper {

    /**
     * @return java.util.List<Permission>
     * @Description 查询权限
     **/
    List<Permission> getPermissions();

}
