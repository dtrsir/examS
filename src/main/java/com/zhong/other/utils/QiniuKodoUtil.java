package com.zhong.other.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.*;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//七牛云存储工具类
@Component
public class QiniuKodoUtil {
    /**
     * 构造一个带指定 Region 对象的配置类，因为我的是华南机房，所以为Region.region2()
     */
    Configuration cfg = new Configuration(Region.region2());
    @Value("${qiniu.kodo.accessKey}")
    String accessKey;
    @Value("${qiniu.kodo.secretKey}")
    String secretKey;
    @Value("${qiniu.kodo.bucket}")
    String bucket;//存储空间名称
    @Value("${qiniu.kodo.domain}")
    String domain;//访问域名：此处使用七牛云提供的测试域名一个月后回收

    //文件名前缀
    String prefix = "";
    //每次迭代的长度限制，最大1000，推荐值 1000
    int limit = 1000;
    //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
    String delimiter = "";

    //列举空间文件列表
    public void listSpaceFiles() {
        Auth auth = Auth.create(accessKey, secretKey);//使用key创建一个用户对象
        BucketManager bucketManager = new BucketManager(auth, cfg);//创建空间管理对象，使用用户和地区配置类连接到存储空间
        //创建文件迭代器
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);

        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            //控制台遍历输出所有文件
            for (FileInfo item : items) {
                String size = item.fsize / 1024 / 1024 + "Mb";
                System.out.println("fileName:" + item.key + "\tsize:" + size + "\ttype:" + item.mimeType);
            }
        }
    }

    /**
     * 上传本地文件
     * @param localFilePath 上传的文件路径
     */


    public void upload(String localFilePath) {
        UploadManager uploadManager = new UploadManager(cfg);//创建华南机房的上传管理对象
        /**
         *  如果是Windows情况下，格式是 D:\\qiniu\\test.png
         * 以文件最低级目录名作为文件名
         */
        String[] strings = localFilePath.split("\\\\");//获取路径中的每个文件夹
        String key = strings[strings.length - 1];//获取文件名
        Auth auth = Auth.create(accessKey, secretKey);//使用key创建作者
        String upToken = auth.uploadToken(bucket);//通过作者对象创建上传令牌

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);//执行上传操作，需要传递令牌文件名和路径
            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);//gson转换json对象为java对象，转换类型为DefaultPutRet
            JSONObject jsonObject = JSON.parseObject(response.bodyString());
            DefaultPutRet putRet = jsonObject.toJavaObject(DefaultPutRet.class);
            System.out.println(putRet.key);//输出上传成功之后的文件名
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    /**
     * 获取下载文件的链接
     *
     * @param fileName 文件名称
     * @return 下载文件的链接
     */
    public String getFileUrl(String fileName) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        String finalUrl = String.format("%s/%s", "http://" + domain, encodedFileName);
        System.out.println(finalUrl);
        return finalUrl;
    }

    /**
     * 批量删除空间中的文件
     *
     * @param fileList 文件名称列表
     */
    public void deleteFile(String[] fileList) {
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            //单次批量请求的文件数量不得超过1000
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, fileList);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < fileList.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = fileList[i];
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.data.error);
                }
            }
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
    }
}
