package com.sample.ssm.mapper;

import com.sample.ssm.model.Resource;
import com.sample.ssm.model.Role;

import java.util.List;

/**
 * @program: springmvc-demo
 * @description: 权限mapper
 * @author: baijd-a
 * @create: 2020-07-15 16:59
 **/
public interface RoleMapper {

    List<Role> getRoles();

    Resource getRoleById();
}
