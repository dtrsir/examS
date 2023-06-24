package com.zhong.service;

import com.zhong.pojo.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author zhong
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 */
public interface UserService extends IService<User> {

    /**
     * 保存验证码到redis中，并设置键过期时间为2分钟
     *
     * @param key  键
     * @param code 验证码
     */
    void redisSaveCode(String key, String code);

    /**
     * 获取验证码
     *
     * @param key 键
     * @return redis保存的正确的验证码
     */
    String redisGetCode(String key);

    /**
     * 移除验证码
     *
     * @param key redis中验证码的key值
     */
    void redisRemoveCode(String key);

    /**
     * 保存用户
     *
     * @param token 用户登陆令牌
     * @param user 用户对象
     */
    void redisSaveUser(String token, User user);

    /**
     * 查询用户
     *
     * @param token 用户令牌
     * @return 用户对象
     */
    User redisGetUser(String token);

    /**
     * 移除用户
     *
     * @param token 用户令牌
     */
    void redisRemoveUser(String token);

    /**
     * 检查用户名是否存在
     *
     * @param username
     * @return User对象
     */
    User checkExistsUsername(String username);

    /**
     * 删除用户
     *
     * @param user
     */
    void deleteUser(User user);
}
