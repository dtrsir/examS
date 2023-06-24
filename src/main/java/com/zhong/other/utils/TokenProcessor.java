package com.zhong.other.utils;


import java.util.Base64.Encoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 * 生成Token的工具类。使用单例模式
 */
public class TokenProcessor {

    private TokenProcessor() {
    }

    private static final TokenProcessor instance = new TokenProcessor();

    public static TokenProcessor getInstance() {
        return instance;
    }

    /**
     * 生成Token
     *
     * @return Token
     */
    public String makeToken() {
        //当前系统时间+随机数
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            //md5加密原始token
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(token.getBytes());
            //加密的token编码base64
            Encoder encoder = Base64.getEncoder();
            return new String(encoder.encode(md5));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
