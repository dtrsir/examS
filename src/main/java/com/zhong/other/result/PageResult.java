package com.zhong.other.result;

import lombok.Data;

/**
 * @Author: zhong
 * @Description: 分页结果类
 */
@Data
public class PageResult extends BaseResult{

    /**
     * 记录数量
     */
    private Long count;

    /**
     * 成功，count和data
      * @param count 记录数量
     * @param data 数据
     * @return
     */
    public static PageResult success(Long count, Object data) {
        PageResult result = new PageResult();
        result.setStatus(200);
        result.setMessage("成功");
        result.setCount(count);
        result.setData(data);
        return result;
    }

    /**
     * 成功，message，count，data
     * @param message 自定义msg
     * @param data 数据
     * @param count 记录数
     * @return
     */
    public static PageResult successMsg(String message, Long count, Object data) {
        PageResult result = new PageResult();
        result.setStatus(200);
        result.setMessage(message);
        result.setCount(count);
        result.setData(data);
        return result;
    }

}
