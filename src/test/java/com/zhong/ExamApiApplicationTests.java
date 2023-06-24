package com.zhong;

import com.zhong.other.utils.QiniuKodoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
class ExamApiApplicationTests {

    @Autowired
    private QiniuKodoUtil qiniuKodoUtil;

    @Test
    void contextLoads() {
        try {
            qiniuKodoUtil.getFileUrl("招聘名单.txt");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
