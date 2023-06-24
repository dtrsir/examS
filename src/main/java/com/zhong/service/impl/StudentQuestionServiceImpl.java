package com.zhong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhong.pojo.po.StudentQuestion;
import com.zhong.service.StudentQuestionService;
import com.zhong.mapper.StudentQuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author wangpeng
* @description 针对表【s_student_question(学生答题表)】的数据库操作Service实现
* @createDate 2022-04-10 13:50:27
*/
@Service
public class StudentQuestionServiceImpl extends ServiceImpl<StudentQuestionMapper, StudentQuestion>
    implements StudentQuestionService{

}




