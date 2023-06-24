package com.zhong.controller.system;

import com.zhong.other.result.BaseResult;
import com.zhong.other.utils.MyData;
import com.zhong.service.MyUploadService;
import com.zhong.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @Author: zhong
 * @Description:
 */
@Controller
@RequestMapping("/system/upload")
public class SystemUploadController {

    @Autowired
    QuestionService questionService;

    @Autowired
    MyUploadService myUploadService;

    /**
     * 上传图片
     * @param req
     * @return
     */
    @RequestMapping("/uploadImage")
    @ResponseBody
    public BaseResult uploadImage(HttpServletRequest req){
        // 上传到oss
        String resPath = myUploadService.uploadOSS(req, "pictures").get("resPath");
        if (resPath != null && resPath.length() != 0) {
            return BaseResult.successMsgAndData("上传图片成功", resPath);
        } else {
            return BaseResult.errorMsg("上传图片失败");
        }
    }

    /**
     * 上传Excel表格
     * @param req
     * @return
     */
    @RequestMapping("/uploadExcel")
    @ResponseBody
    public BaseResult uploadExcel(HttpServletRequest req){
        // 上传到本地
        String fileName = myUploadService.uploadLocal(req, "excel").get("fileName");
        String path = MyData.resourcePath + "/excel/" + fileName;
        questionService.importExcel(path);
        // 删除该文件
        File file = new File(path);
        file.delete();
        return BaseResult.boolResult(true);
    }
}
