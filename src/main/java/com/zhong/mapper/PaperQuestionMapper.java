package com.zhong.mapper;

import com.zhong.pojo.po.PaperQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhong
 * @description 针对表【t_paper_question(试卷题目表)】的数据库操作Mapper
 * @Entity com.zhong.pojo.po.PaperQuestion
 */
@Repository
public interface PaperQuestionMapper extends BaseMapper<PaperQuestion> {

}




