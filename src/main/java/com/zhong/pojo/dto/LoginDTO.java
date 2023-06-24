package com.zhong.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: zhong
 * @Description: 登陆传输对象，只封装了登陆所需要的数据
 */
@Data
public class LoginDTO {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码的key
     */
    @NotBlank(message = "key不能为空")
    private String key;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;
}
