package com.zhong.pojo.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 考试表
 * @TableName t_exam
 */
@Data
public class ExamVO implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 试卷id
     */
    private Integer paperId;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 考试名称
     */
    private String name;

    /**
     * 考试时长（分钟）
     */
    private Integer duration;

    /**
     * 是否限制考试时间
     */
    private Integer limitTime;

    /**
     * 考试开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 考试结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 考前注意事项
     */
    private String notice;

    /**
     * 考试权限
     */
    private Integer privilege;

    /**
     * 考试密码
     */
    private String password;

    /**
     * 允许切屏次数
     */
    private Integer switchScreenCount;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
