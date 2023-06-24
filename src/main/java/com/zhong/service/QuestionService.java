package com.zhong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhong.pojo.dto.QueryPageQuestionDTO;
import com.zhong.pojo.dto.QueryQuestionDTO;
import com.zhong.pojo.dto.SaveQuestionDTO;
import com.zhong.pojo.po.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhong.pojo.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author zhong
* @description 针对表【t_question(题目表)】的数据库操作Service
*/
public interface QuestionService extends IService<Question> {


    Page<QuestionVO> getQuestionVOPage(QueryPageQuestionDTO queryPageQuestionDTO);

    boolean saveQuestionAndAnswer(SaveQuestionDTO saveQuestionDTO);

    void deleteQuestion(Question question);

    List<QuestionVO> getRandomQuestion(Integer repoId, Integer chapterId, Integer quantity, Integer typeId, List<Integer> excludeIdList);

    String exportExcel(QueryQuestionDTO queryQuestionDTO, HttpServletRequest req) throws Exception;

    /**
     * 上传excel表格
     * @param path 路径
     */
    void importExcel(String path);

}
