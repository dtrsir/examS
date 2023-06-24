package com.zhong.pojo.dto;

import com.zhong.pojo.enums.TypeEnum;
import lombok.Data;

/**
 * @Author: 王鹏
 * @Date: 2022/03/21/0:29
 * @Description:
 */
@Data
public class QueryQuestionDTO {

    private Integer repoId;

    private Integer chapterId;

    private TypeEnum type;

    private String content;

}
