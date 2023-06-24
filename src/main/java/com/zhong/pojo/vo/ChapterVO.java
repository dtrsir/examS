package com.zhong.pojo.vo;

import com.zhong.pojo.po.Chapter;
import lombok.Data;

/**
 * @Author: 王鹏
 * @Date: 2022/05/04/11:48
 * @Description: 章节的VO，多一个题目数量字段
 */
@Data
public class ChapterVO extends Chapter {

    /**
     * 题目数量
     */
    private Long questionCount;

}
