package com.zhong.pojo.vo;

import com.zhong.pojo.po.Option;
import com.zhong.pojo.po.Question;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目表VO
 * @TableName t_question
 */
@Data
public class QuestionVO extends Question implements Serializable {
    /**
     * 选项
     */
    private List<Option> options;

    /**
     * 题库名
     */
    private String repoName;

    /**
     * 章节名
     */
    private String chapterName;
}
