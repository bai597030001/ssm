package com.sample.ssm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: springmvc-demo
 * @description: 权限
 * @author: baijd-a
 * @create: 2020-07-15 16:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    private String permission;

    private String description;
}
