package com.zhong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhong.pojo.po.StudentOption;
import com.zhong.service.StudentOptionService;
import com.zhong.mapper.StudentOptionMapper;
import org.springframework.stereotype.Service;

/**
* @author wangpeng
* @description 针对表【s_student_option】的数据库操作Service实现
* @createDate 2022-04-16 20:52:02
*/
@Service
public class StudentOptionServiceImpl extends ServiceImpl<StudentOptionMapper, StudentOption>
    implements StudentOptionService{

}




