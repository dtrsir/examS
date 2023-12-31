package com.zhong.pojo.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 试卷表
 * @TableName t_paper
 */
@TableName(value ="t_paper")
@Data
public class Paper implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 试卷名称
     */
    private String name;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 题目数量
     */
    private Integer questionCount;

    /**
     * 总分
     */
    private BigDecimal totalScore;

    /**
     * 及格分
     */
    private BigDecimal qualifyScore;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill= FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}