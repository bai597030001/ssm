package com.sample.ssm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: springmvc-demo
 * @description: 角色权限表
 * @author: baijd-a
 * @create: 2020-07-15 16:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    private long roleId;

    private long permissionId;
}
