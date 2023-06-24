package com.zhong.mapper;

import com.zhong.pojo.po.Exam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhong
 * @description 针对表【t_exam(考试表)】的数据库操作Mapper
 * @Entity com.zhong.pojo.po.Exam
 */
@Repository
public interface ExamMapper extends BaseMapper<Exam> {

}




