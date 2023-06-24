package com.zhong.other.config;

import com.zhong.other.interceptor.StudentInterceptor;
import com.zhong.other.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;
    @Autowired
    private StudentInterceptor studentInterceptor;

    // 与学生相关路径
    public static final String[] aboutStudent = {
            "/student/**"
    };

    // 与管理员相关路径
    public static final String[] aboutAdmin = {
            "/admin/**"
    };

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
          用户拦截器思路（拦截未登录的请求）：
          1. 拦截管理员请求
          2. 拦截学生请求
         */
        registry.addInterceptor(userInterceptor)
                .addPathPatterns(aboutAdmin)
                .addPathPatterns(aboutStudent);

        /*
         * 学生拦截器思路（拦截管理员的请求）：
         * 1. 拦截管理员请求
         */
        registry.addInterceptor(studentInterceptor)
                .addPathPatterns(aboutAdmin);

    }
}
