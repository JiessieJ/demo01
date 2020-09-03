package com.jiessie.test01.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * Created by huangmingjie on 2017/10/20.
 */
public class StringUtil {
    
    private final static String ENCODING_UTF_8 = "UTF-8";
    
    public final static String md5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes(ENCODING_UTF_8);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getLocalIp(String address){
        if(StringUtils.isNotEmpty(address)){
            return address.substring(address.indexOf("//") + 2, address.lastIndexOf(":"));
        }
        return null;
    }
}
