package com.zhong.pojo.dto;

import com.zhong.pojo.po.Option;
import com.zhong.pojo.po.Question;
import lombok.Data;

/**
 * @Author: 王鹏
 * @Date: 2022/03/27/13:34
 * @Description:
 */
@Data
public class SaveQuestionDTO {

    private Question question;

    private Option[] option;

}
