package com.zhong.service;

import com.qiniu.common.QiniuException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Map;

/**
 * @Author: zhong
 * @Description: 文件上传类
 */
public interface MyUploadService {

    /**
     * 上传到七牛云
     *
     * @param inputStream 输入流
     * @param dirName     文件夹名
     * @param fileName    文件名
     * @return 文件的网络地址
     * @throws com.qiniu.common.QiniuException
     */
    public String qiniuUpload(InputStream inputStream, String dirName, String fileName) throws QiniuException;

    /**
     * 上传OSS
     * @param req
     * @param dirName
     * @return
     */
    Map<String,String> uploadOSS(HttpServletRequest req, String dirName);

    /**
     * 上传本地
     * @param req
     * @param dirName
     * @return
     */
    Map<String, String> uploadLocal(HttpServletRequest req, String dirName);

}
