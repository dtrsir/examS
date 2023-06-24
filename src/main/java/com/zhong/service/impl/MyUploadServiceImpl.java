package com.zhong.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.zhong.other.utils.MyData;
import com.zhong.service.MyUploadService;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: zhong
 * @Description: 文件上传类，上传七牛云oss
 */
//@Service
//public class MyUploadServiceImpl implements MyUploadService {
//
//    @Value("${aliyun.endpoint}")
//    private String endpoint;
//    @Value("${aliyun.accessKeyId}")
//    private String accessKeyId;
//    @Value("${aliyun.accessKeySecret}")
//    private String accessKeySecret;
//    @Value("${aliyun.bucket}")
//    private String bucket;
//    @Value("${aliyun.rootPath}")
//    private String rootPath;
//
//    /**
//     * 上传到阿里云OSS
//     * @param inputStream 输入流
//     * @param dirName 文件夹名
//     * @param fileName 文件名
//     * @return
//     * @throws FileNotFoundException
//     */
//    public String aliyunUpdate(InputStream inputStream, String dirName, String fileName) {
//        // 创建OSSClient实例
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
//        ossClient.putObject(bucket, rootPath + "/" + dirName + "/" + fileName, inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//
//        return "https://" + bucket + ".oss-cn-hangzhou.aliyuncs.com/" + rootPath + "/" + dirName + "/" + fileName;
//    }
//
//    @Override
//    public Map<String, String> uploadOSS(HttpServletRequest req, String dirName) {
//        String resPath = null;  // 结果地址
//        String fileName = null; // 文件名
//        //先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
//        if (ServletFileUpload.isMultipartContent(req)) {
//            // 创建 FileItemFactory 工厂实现类
//            FileItemFactory fileItemFactory = new DiskFileItemFactory();
//            // 创建用于解析上传数据的工具类 ServletFileUpload 类
//            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
//            try {
//                // 解析上传的数据，得到每一个表单项 FileItem
//                List<FileItem> list = servletFileUpload.parseRequest(new ServletRequestContext(req));
//                // 循环判断，每一个表单项，是普通类型，还是上传的文件
//                for (FileItem fileItem : list) {
//                    if ( !fileItem.isFormField()) { // 是上传的文件
//
//                        fileName = fileItem.getName();
//                        // 加个时间戳防止重名
//                        String newFileName = System.currentTimeMillis() + fileName;
//
//                        //获取输入流
//                        InputStream inputStream = fileItem.getInputStream();
//                        resPath = aliyunUpdate(inputStream, dirName, newFileName);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        Map<String,String> resMap = new HashMap<>();
//        resMap.put("resPath", resPath);
//        resMap.put("fileName", fileName);
//        return resMap;
//    }
//
//    @Override
//    public Map<String, String> uploadLocal(HttpServletRequest req, String dirName) {
//        String resPath = null;  // 返回网络路径
//        String newFileName = null;
//        try {
//            String localDir = MyData.resourcePath + "/" + dirName;   //本地目录
//            // 如果结果目录不存在，则创建目录
//            File resDirFile = new File(localDir);
//            if(!resDirFile.exists()) {
//                boolean flag = resDirFile.mkdirs();
//                if(!flag) throw new RuntimeException("创建目录失败");
//            }
//            //先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
//            if (ServletFileUpload.isMultipartContent(req)) {
//                // 创建 FileItemFactory 工厂实现类
//                FileItemFactory fileItemFactory = new DiskFileItemFactory();
//                // 创建用于解析上传数据的工具类 ServletFileUpload 类
//                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
//                // 解析上传的数据，得到每一个表单项 FileItem
//                List<FileItem> list = servletFileUpload.parseRequest(new ServletRequestContext(req));
//                // 循环判断，每一个表单项，是普通类型，还是上传的文件
//                for (FileItem fileItem : list) {
//                    if ( !fileItem.isFormField()) { // 是上传的文件
//                        // 加个时间戳防止重名
//                        newFileName = System.currentTimeMillis() + fileItem.getName();
//                        // 写文件
//                        File file = new File(localDir + "/" + newFileName);
//                        fileItem.write(file);
//                        // 返回值
//                        resPath = "http://" + MyData.address + ":" + MyData.port + "/exam/" + dirName + "/" + newFileName;
//                    }
//                }
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Map<String, String> resMap = new HashMap<>();
//        resMap.put("resPath", resPath);
//        resMap.put("fileName", newFileName);
//        return resMap;
//    }
//}


@Service
public class MyUploadServiceImpl implements MyUploadService {

    /**
     * 读取存储空间配置
     */
    @Value("${qiniu.kodo.accessKey}")
    private String accessKey;
    @Value("${qiniu.kodo.secretKey}")
    private String secretKey;
    @Value("${qiniu.kodo.bucket}")
    private String bucket;
    @Value("${qiniu.kodo.rootPath}")
    private String rootPath;
    @Value("${qiniu.kodo.domain}")
    private String domain;

    /**
     * 上传到七牛云
     *
     * @param inputStream 输入流
     * @param dirName     文件夹名
     * @param fileName    文件名
     * @return 文件的网络地址
     * @throws com.qiniu.common.QiniuException
     */
    public String qiniuUpload(InputStream inputStream, String dirName, String fileName) throws QiniuException {
        // 创建上传凭证
        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(bucket);

        // 设置存储路径
        String key = rootPath + "/" + dirName + "/" + fileName;

        // 创建上传管理器
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);

        // 执行上传操作
        Response response = uploadManager.put(inputStream, key, uploadToken, null, null);

        // 解析上传结果
        String bodyString = response.bodyString();
        JSONObject jsonObject = JSON.parseObject(bodyString);
        String fileKey = jsonObject.getString("key");

        // 拼接文件的网络地址
        String fileUrl = domain + "/" + fileKey;

        return fileUrl;
    }

    @Override
    public Map<String, String> uploadOSS(HttpServletRequest req, String dirName) {
        String resPath = null;  // 结果地址
        String fileName = null; // 文件名
        //先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
        if (ServletFileUpload.isMultipartContent(req)) {
            // 创建 FileItemFactory 工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 创建用于解析上传数据的工具类 ServletFileUpload 类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                // 解析上传的数据，得到每一个表单项 FileItem
                List<FileItem> list = servletFileUpload.parseRequest(new ServletRequestContext(req));
                // 循环判断，每一个表单项，是普通类型，还是上传的文件
                for (FileItem fileItem : list) {
                    if (!fileItem.isFormField()) { // 是上传的文件

                        fileName = fileItem.getName();
                        // 加个时间戳防止重名
                        String newFileName = System.currentTimeMillis() + fileName;

                        //获取输入流
                        InputStream inputStream = fileItem.getInputStream();
                        resPath = qiniuUpload(inputStream, dirName, newFileName);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, String> resMap = new HashMap<>();
        resMap.put("resPath", resPath);
        resMap.put("fileName", fileName);
        return resMap;
    }

    @Override
    public Map<String, String> uploadLocal(HttpServletRequest req, String dirName) {
        String resPath = null;  // 返回网络路径
        String newFileName = null;
        try {
            String localDir = MyData.resourcePath + "/" + dirName;   //本地目录
            // 如果结果目录不存在，则创建目录
            File resDirFile = new File(localDir);
            if (!resDirFile.exists()) {
                boolean flag = resDirFile.mkdirs();
                if (!flag) throw new RuntimeException("创建目录失败");
            }
            //先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
            if (ServletFileUpload.isMultipartContent(req)) {
                // 创建 FileItemFactory 工厂实现类
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                // 创建用于解析上传数据的工具类 ServletFileUpload 类
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
                // 解析上传的数据，得到每一个表单项 FileItem
                List<FileItem> list = servletFileUpload.parseRequest(new ServletRequestContext(req));
                // 循环判断，每一个表单项，是普通类型，还是上传的文件
                for (FileItem fileItem : list) {
                    if (!fileItem.isFormField()) { // 是上传的文件
                        // 加个时间戳防止重名
                        newFileName = System.currentTimeMillis() + fileItem.getName();
                        // 写文件
                        File file = new File(localDir + "/" + newFileName);
                        fileItem.write(file);
                        // 返回值
                        resPath = "http://" + MyData.address + ":" + MyData.port + "/exam/" + dirName + "/" + newFileName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> resMap = new HashMap<>();
        resMap.put("resPath", resPath);
        resMap.put("fileName", newFileName);
        return resMap;
    }
}

