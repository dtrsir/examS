package com.zhong.mapper;

import com.zhong.pojo.po.Repo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhong
 * @description 针对表【t_repo(课程表)】的数据库操作Mapper
 * @Entity com.zhong.pojo.po.Repo
 */
@Repository
public interface RepoMapper extends BaseMapper<Repo> {

}




