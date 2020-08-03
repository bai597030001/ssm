package com.sample.ssm.controller;

import com.sample.ssm.mapper.*;
import com.sample.ssm.model.Permission;
import com.sample.ssm.model.Role;
import com.sample.ssm.model.User;
import com.sample.ssm.model.UserRole;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @program: ssm
 * @description: 控制器
 * @author: baijd-a
 * @create: 2020-07-16 11:09
 **/
@Controller
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
    @ResponseBody
    public String getUserInfoModelAndView() {
        return "world";
    }

    // @GetMapping("/permission") // Spring4.3中引进
    @RequestMapping("/permission")
    @ResponseBody
    public String getPermission() {
        return permissionMapper.getPermissions().toString();
    }

    @RequestMapping("/permissionResource")
    @ResponseBody
    public String getPermissionResource() {
        return permissionResourceMapper.getPermissionResourceByPermissionId(1).toString();
    }

    @RequestMapping("/resource")
    @ResponseBody
    public String getResource() {
        return resourceMapper.getResource().toString();
    }

    @RequestMapping("/role")
    @ResponseBody
    public String getRole() {
        return roleMapper.getRoles().toString();
    }

    @RequestMapping("/rolePermission")
    @ResponseBody
    public String getRolePermission() {
        return rolePermissionMapper.getRolePermission().toString();
    }

    @RequestMapping("/user")
    @ResponseBody
    public String getUsers() {
        return userMapper.getUsers().toString();
    }

    @RequestMapping("/userRole")
    @ResponseBody
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
    public ModelAndView register(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        userMapper.addUser(user);
        ModelAndView register = new ModelAndView();
        register.setViewName("home");
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
        /**用户登录的时候加载用户的权限并放到Session**/
        HttpSession session = request.getSession();
        String username = null;
        String password = null;
        try {
            username = request.getParameter("login_username");
            password = request.getParameter("passwd");
            User user = userMapper.getUserByName(username);
            if (user == null || !user.getPassword().equals(password)) {
                System.out.println("user does not exist or password wrong");
                response.sendRedirect(request.getContextPath());
                return;
            }
            // 1.根据用户名获取用户角色
            // 2.根据用户角色获取用户权限
            UserRole userRole = userRoleMapper.getUserRoleByUserId(user.getId());
            long roleId = userRole.getRoleId();
            List<Permission> permissions = rolePermissionMapper.getPermissionIdByRoleId(roleId);
            user.setPermissions(permissions);
            // 将用户信息添加到session
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
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
