package com.zhong.mapper;

import com.zhong.pojo.po.StudentExam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhong
 * @description 针对表【s_student_exam(学生考试表)】的数据库操作Mapper
 * @Entity com.zhong.pojo.po.StudentExam
 */
@Repository
public interface StudentExamMapper extends BaseMapper<StudentExam> {

}




