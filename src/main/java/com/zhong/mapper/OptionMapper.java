package com.zhong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhong.pojo.po.Option;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhong
 * @description 针对表【t_option(题目选项表)】的数据库操作Mapper
 * @Entity com.zhong.pojo.po.Option
 */
@Repository
public interface OptionMapper extends BaseMapper<Option> {
    List<Option> selectByQuestionId(@Param("questionId") Integer questionId);
}




