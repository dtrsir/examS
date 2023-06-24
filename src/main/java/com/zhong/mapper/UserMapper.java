package com.zhong.mapper;

import com.zhong.pojo.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhong
 * @description 针对表【sys_user(用户表)】的数据库操作Mapper
 * @Entity com.zhong.pojo.po.User
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}




