package com.travel.mybootdemo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-08-58
 * @Description: 拦截器
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser != null){
            return true;
        }

        request.setAttribute("msg","您尚未登录，请先登录！");
        request.getRequestDispatcher("/login").forward(request,response);

        return false;
    }
}
