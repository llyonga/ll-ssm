package com.io.ssm.framework.utils;


import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Base64;

/**
 * @description:  部分加密/解密工具类（主要用于登录加密用的）
 * @author: llyong
 * @date: 2018/8/30
 * @time: 23:57
 * @version: 1.0
 */
public class CryptographyUtil {

    /**
     * base64加密
     * @param str
     * @return
     */
    public static String encBase64(String str) throws Exception{
        return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
    }

    /**
     * base64加密
     * @param srcByte
     * @return
     * @throws Exception
     */
    public static String encBase64(byte[] srcByte) throws Exception{
        return Base64.getEncoder().encodeToString(srcByte);
    }

    /**
     * base64解密
     * @param str
     * @return
     */
    public static byte[] decBase64(String str) {
        return Base64.getDecoder().decode(str);
    }

    /**
     * Md5加密
     * @param str   源数据
     * @param salt  盐值
     * @return
     */
    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }

    /**
     * 用于登录加密（sha-256）
     * 盐值为username得到
     *
     * @param username
     * @param password
     * @param hashIterations
     * @return
     */
    public static String sha256(String username,String password,int hashIterations) {
        Object salt = ByteSource.Util.bytes(username);
        return new Sha256Hash(password, salt, hashIterations).toHex();
    }

    /**
     * 简单散列加密算法
     * @param hashAlgorithmName
     * @param credentials
     * @param password
     * @param hashIterations
     * @return
     */
    public static String simpleHash(String hashAlgorithmName,String credentials,String password,int hashIterations) {
        Object salt = ByteSource.Util.bytes(hashAlgorithmName);
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toHex();
    }
}
