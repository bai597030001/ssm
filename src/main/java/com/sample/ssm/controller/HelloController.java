package com.sample.ssm.controller;

import com.sample.ssm.mapper.*;
import com.sample.ssm.model.Permission;
import com.sample.ssm.model.Role;
import com.sample.ssm.model.User;
import com.sample.ssm.utils.MD5Util;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: ssm
 * @description: 控制器
 * @author: baijd-a
 * @create: 2020-07-16 11:09
 **/
@RestController
// @CrossOrigin(allowCredentials = "true") // 从Spring4.2版本增加
public class HelloController {
    // @Autowired
    // private PermissionMapper permissionMapper;
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

    @RequestMapping("/hello")
    public String getUserInfoModelAndView() {
        return "world";
    }

    // @GetMapping("/permission") // Spring4.3中引进
    @RequestMapping("/permission")
    public String getPermission() {
        return permissionMapper.getPermissions().toString();
    }

    @RequestMapping("/permissionResource")
    public String getPermissionResource() {
        return permissionResourceMapper.getPermissionResourceByPermissionId(1).toString();
    }

    @RequestMapping("/resource")
    public String getResource() {
        return resourceMapper.getResource().toString();
    }

    @RequestMapping("/role")
    public String getRole() {
        return roleMapper.getRoles().toString();
    }

    @RequestMapping("/rolePermission")
    public String getRolePermission() {
        return rolePermissionMapper.getRolePermission().toString();
    }

    @RequestMapping("/user")
    public String getUsers() {
        return userMapper.getUsers().toString();
    }

    @RequestMapping("/userRole")
    public String getUserRole() {
        return userRoleMapper.getUserRole().toString();
    }

    @RequestMapping("home")
    public ModelAndView home() {
        ModelAndView home = new ModelAndView();
        home.setViewName("home");
        return home;
    }

    @RequestMapping("error")
    public ModelAndView error() {
        ModelAndView home = new ModelAndView();
        home.setViewName("error");
        return home;
    }

    @RequestMapping("register")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("register user " + username + " success");
        ModelAndView register = new ModelAndView();
        register.setViewName("register");
        return register;
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /**设置请求编码*/
        request.setCharacterEncoding("UTF-8");
        /**设置响应类型*/
        response.setContentType("text/html");
        /**设置响应编码*/
        response.setCharacterEncoding("UTF-8");
        //获取MD5加密对象
        //MD5 pwd = new MD5();
        //获取输入的用户名和密码
        String username = null;
        String password = null;
        try {
            username = request.getParameter("login_username");
            password = request.getParameter("passwd");
            //TODO 查数据表
            List<Permission> permissions = permissionMapper.getPermissions();
            System.out.println(permissions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("username : " + username + "; password : " + password);
        String md5Password = password == null ? "" : MD5Util.md5(password);
        System.out.println("md5 password : " + md5Password);
        if (username.equals("admin") && password.equals("admin")) {
            response.sendRedirect("home");
        } else {
            response.sendRedirect("home");
        }
    }

    @RequestMapping("/userInfo")
    public ModelAndView getUserInfoModelAndView(int startIndex, int endIndex) {
        List<User> userInfo = null;
        try {
            userInfo = userMapper.getUsers();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ModelAndView model = new ModelAndView("userInfo");
        if (userInfo != null) {
            model.addObject("userInfo", userInfo);
        }
        return model;
    }

    @RequestMapping("/roleInfo")
    public ModelAndView getRoleInfo(int startIndex, int endIndex) {
        List<Role> roleInfo = null;
        try {
            roleInfo = roleMapper.getRoles();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ModelAndView model = new ModelAndView("roleInfo");
        if (roleInfo != null) {
            model.addObject("roleInfo", roleInfo);
        }
        return model;
    }
}
