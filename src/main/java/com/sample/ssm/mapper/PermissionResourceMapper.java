package com.sample.ssm.mapper;

import com.sample.ssm.model.PermissionResource;

import java.util.List;

/**
 * @program: springmvc-demo
 * @description:
 * @author: baijd-a
 * @create: 2020-07-15 16:59
 **/
public interface PermissionResourceMapper {

    List<PermissionResource> getPermissionResourceByPermissionId(int permissionId);

}
