package com.sample.ssm.config;

import com.sample.ssm.model.Permission;
import com.sample.ssm.model.User;
import lombok.SneakyThrows;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: ssm
 * @description: 权限拦截器
 * @author: baijd-a
 * @create: 2020-07-15 10:38
 **/
public class ShiroHandlerInterceptorAdapter implements HandlerInterceptor {
    @SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 用户未登录
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        List<Permission> permissions = user.getPermissions();
        //用户未定义任何权限，直接跳转登录界面
        if (permissions == null || permissions.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        String uri = request.getRequestURI();
        String matchUri = uri;
        int pos = uri.indexOf("?");
        if (pos != -1) {
            matchUri = uri.substring(0, pos);
        }
        Map<String, Set<String>> resourceMap = (Map<String, Set<String>>) request.getServletContext().getAttribute("resourceMap");
        Set<String> permissionSet = resourceMap.get(matchUri);
        if (permissionSet == null || permissionSet.isEmpty()) {
            // 该资源无需权限
            return true;
        } else {
            for (Permission p : permissions) {
                if (permissionSet.contains(p.getPermission())) {
                    return true;
                }
            }
            // 否则提示没有操作权限
            // 判断是否为 ajax 请求
            if (request.getHeader("X-Requested-With") == null) {
                response.sendRedirect("/unauthorized");
            } else {
                response.getWriter().write("{\"statue\":\"false\",\"message\":\"您没有操作权限\"}");
            }
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
