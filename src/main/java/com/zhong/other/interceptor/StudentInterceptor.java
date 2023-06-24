package com.zhong.other.interceptor;

import com.zhong.other.utils.ResponseJson;
import com.zhong.pojo.enums.RoleEnum;
import com.zhong.pojo.po.User;
import com.zhong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zhong
 * @Description: 学生类型用户登陆拦截器
 */
@Component
public class StudentInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行预请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) return true;
        // 获取请求中的Token
        String token = request.getHeader("X-Token");
        if (token == null) {
            ResponseJson.errorMsg(response, "身份认证失败，请重新登录！");
            return false;
        }
        // 根据token取用户
        User user = userService.redisGetUser(token);
        // 如果不是管理员，则不能访问
        if (user.getRole() != RoleEnum.ADMIN) {
            ResponseJson.errorMsg(response, "身份权限不够，请重新登录！");
            return false;
        }
        return true;
    }
}
