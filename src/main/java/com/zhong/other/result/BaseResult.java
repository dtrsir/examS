package com.zhong.other.result;

import lombok.Data;

/**
 * @Author: zhong
 * @Description: 通用结果类
 * 200：表示成功
 * 500：表示错误，错误信息不重要，前端自己定义
 * 501：表示错误，错误信息在msg中
 * 502：表示错误，错误信息在data中
 * 555：异常抛出信息
 */
@Data
public class BaseResult {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 时间戳
     */
    private Long timestamp;

    public BaseResult() {
        timestamp = System.currentTimeMillis();
    }

    /**
     * 成功
     * @return code=200;msg=成功
     */
    public static BaseResult success() {
        BaseResult result = new BaseResult();
        result.setStatus(200);
        result.setMessage("成功");
        return result;
    }

    /**
     * 成功，message
     * @param message 自定义msg
     * @return code=200;msg
     */
    public static BaseResult successMsg(String message) {
        BaseResult result = new BaseResult();
        result.setStatus(200);
        result.setMessage(message);
        return result;
    }

    /**
     * 成功，data
     * @param data 数据
     * @return
     */
    public static BaseResult successData(Object data) {
        BaseResult result = new BaseResult();
        result.setStatus(200);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    /**
     * 成功，message和data
     * @param message 自定义msg
     * @param data 返回给前端的数据
     * @return BaseResult
     */
    public static BaseResult successMsgAndData(String message, Object data) {
        BaseResult result = new BaseResult();
        result.setStatus(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 失败
     * @return code=500;msg=失败
     */
    public static BaseResult error() {
        BaseResult result = new BaseResult();
        result.setStatus(500);
        result.setMessage("失败");
        return result;
    }

    /**
     * 错误，message
     * @param message 自定义msg
     * @return code=501;msg
     */
    public static BaseResult errorMsg(String message) {
        BaseResult result = new BaseResult();
        result.setStatus(501);
        result.setMessage(message);
        return result;
    }

    /**
     * 错误，message和data
     * @param data 错误数据
     * @return
     */
    public static BaseResult errorMsgAndData(String message, Object data) {
        BaseResult result = new BaseResult();
        result.setStatus(502);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 错误，exception
     * @param e 异常
     * @return 返回异常信息
     */
    public static BaseResult errorException(Exception e) {
        BaseResult result = new BaseResult();
        result.setStatus(555);
        result.setMessage(e.getMessage());
        return result;
    }

    /**
     * 布尔类型结果
     * @param flag
     * @return
     */
    public static BaseResult boolResult(boolean flag) {
        BaseResult result = new BaseResult();
        if (flag) {
            result.setStatus(200);
            result.setMessage("成功");
        } else {
            result.setStatus(500);
            result.setMessage("失败");
        }
        return result;
    }


}
