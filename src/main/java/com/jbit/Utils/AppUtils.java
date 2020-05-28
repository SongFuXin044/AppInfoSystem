package com.jbit.Utils;

import org.springframework.util.DigestUtils;

/**
 * 项目工具类
 */
public class AppUtils {
    /**
     * MD5加密
     * @param str
     * @return
     */
    public static String encoderByMd5(String str) {
        if (str == null) {
            return null;
        }
        try {
            return DigestUtils.md5DigestAsHex(str.getBytes());//Spring处理
        } catch (Exception e) {
            return null;
        }

//        byte[] digest = null; //笨方法
//        try {
//            MessageDigest md5 = MessageDigest.getInstance("md5");
//            digest  = md5.digest(str.getBytes("utf-8"));
//            return new BigInteger(1, digest).toString(16);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
