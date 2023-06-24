package com.zhong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.zhong.pojo.enums.TypeEnum;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhong.pojo.po.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhong.pojo.vo.QuestionVO;
import org.springframework.stereotype.Repository;

/**
 * @author zhong
 * @description 针对表【t_question(题目表)】的数据库操作Mapper
 * @Entity com.zhong.pojo.po.Question
 */
@Repository
public interface QuestionMapper extends BaseMapper<Question> {

    Page<QuestionVO> getQuestionVOPage(
            Page<QuestionVO> page,
            @Param("repoId") Integer repoId,
            @Param("chapterId") Integer chapterId,
            @Param("type") TypeEnum type,
            @Param("content") String content,
            @Param("list") List<Integer> excludeIdList);

    List<QuestionVO> getQuestionVO(
            @Param("repoId") Integer repoId,
            @Param("chapterId") Integer chapterId,
            @Param("type") TypeEnum type,
            @Param("content") String content);

    List<QuestionVO> selectQuestionsByRandom(
            @Param("repoId") Integer repoId,
            @Param("chapterId") Integer chapterId,
            @Param("quantity") Integer quantity,
            @Param("typeId") Integer typeId,
            @Param("list") List<Integer> excludeIdList);
}
