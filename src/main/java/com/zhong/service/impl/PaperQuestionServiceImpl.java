package com.zhong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhong.pojo.po.PaperQuestion;
import com.zhong.service.PaperQuestionService;
import com.zhong.mapper.PaperQuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author wangpeng
* @description 针对表【t_paper_question(试卷题目表)】的数据库操作Service实现
* @createDate 2022-04-02 15:48:39
*/
@Service
public class PaperQuestionServiceImpl extends ServiceImpl<PaperQuestionMapper, PaperQuestion>
    implements PaperQuestionService{

}




