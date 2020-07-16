package com.sample.ssm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: springmvc-demo
 * @description: 资源表
 * @author: baijd-a
 * @create: 2020-07-15 16:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    private String resource;

    private String description;
}
