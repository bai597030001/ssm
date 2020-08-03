package com.sample.ssm.config;

import com.sample.ssm.mapper.*;
import com.sample.ssm.model.Permission;
import com.sample.ssm.model.Resource;
import com.sample.ssm.model.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.*;

/**
 * @program: ssm
 * @description: servletContext
 * @author: baijd-a
 * @create: 2020-07-17 15:30
 * <p>
 * 思路
 * （1）系统启动的时候，建立一个Map<String,Set> resourceMap，键名为resource（即URL），键值为跟该resource关联的所有permission。
 * 由于一个资源可能对应多个permission（如文章查看权限可以授予一般用户，同时也授予文章管理员），因此这里需要用到一个集合。
 * <p>
 * （2）当用户登录的时候，获取该用户所对应的角色，再根据角色获取其拥有的permission集合
 * <p>
 * （3）当用户访问受限资源的时候，通过URL从resourceMap中获取所需的permission集合，再跟该用户拥有的permission集合比对，用户有相应的permission则通过，否则不通过。
 **/
@Configuration
public class WebServletContextInitializer implements WebApplicationInitializer {

    private static PermissionMapper permissionMapper;
    private static PermissionResourceMapper permissionResourceMapper;
    private static ResourceMapper resourceMapper;
    private static RoleMapper roleMapper;
    private static RolePermissionMapper rolePermissionMapper;
    private static UserMapper userMapper;
    private static UserRoleMapper userRoleMapper;

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        permissionMapper = (PermissionMapper) context.getBean("permissionMapper");
        permissionResourceMapper = (PermissionResourceMapper) context.getBean("permissionResourceMapper");
        resourceMapper = (ResourceMapper) context.getBean("resourceMapper");
        roleMapper = (RoleMapper) context.getBean("roleMapper");
        rolePermissionMapper = (RolePermissionMapper) context.getBean("rolePermissionMapper");
        userMapper = (UserMapper) context.getBean("userMapper");
        userRoleMapper = (UserRoleMapper) context.getBean("userRoleMapper");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        List<Role> roles = roleMapper.getRoles();
        List<Permission> permissions = permissionMapper.getPermissions();
        List<Resource> resource = resourceMapper.getResource();
        // 建立一个Map<String,Set> resourceMap，键名为resource（即URL），键值为跟该resource关联的所有permission。
        Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>(16) {{
            put("/", new HashSet<String>() {{
                add("basic");
                add("advance");
            }});
            put("/login", new HashSet<String>() {{
                add("basic");
                add("advance");
            }});
            put("/register", new HashSet<String>() {{
                add("basic");
                add("advance");
            }});
            put("/home", new HashSet<String>() {{
                add("basic");
                add("advance");
            }});
            put("/error", new HashSet<String>() {{
                add("basic");
                add("advance");
            }});
            put("/userInfo", new HashSet<String>() {{
                add("advance");
            }});
            put("/roleInfo", new HashSet<String>() {{
                add("advance");
            }});
        }};
        servletContext.setAttribute("resourceMap", resourceMap);
    }
}
