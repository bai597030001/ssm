package com.sample.ssm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: springmvc-demo
 * @description: 用户角色表
 * @author: baijd-a
 * @create: 2020-07-15 16:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    private long userId;

    private long roleId;
}
