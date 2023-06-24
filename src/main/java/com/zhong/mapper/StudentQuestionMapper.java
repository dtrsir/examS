package com.zhong.mapper;

import com.zhong.pojo.po.StudentQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhong
 * @description 针对表【s_student_question(学生答题表)】的数据库操作Mapper
 * @Entity com.zhong.pojo.po.StudentQuestion
 */
@Repository
public interface StudentQuestionMapper extends BaseMapper<StudentQuestion> {

}




