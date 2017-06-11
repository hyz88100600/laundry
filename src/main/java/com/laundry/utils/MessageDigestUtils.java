package com.laundry.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtils {

	private static String SHA1 = "SHA-1";
	
	 /** 
     * 全局数组 
     */  
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    
    /*
     * 加密
     */
    public static String encryptSHA1(String data) throws NoSuchAlgorithmException {  
        // 创建具有指定算法名称的信息摘要  
        MessageDigest sha1 = MessageDigest.getInstance(SHA1);  
        // 使用指定的字节数组对摘要进行最后更新  
        sha1.update(data.getBytes());  
        // 完成摘要计算  
        byte[] bytes = sha1.digest();  
        // 将得到的字节数组变成字符串返回  
        return byteArrayToHexString(bytes);  
    }  
    
    
	/** 
     * 将一个字节转化成十六进制形式的字符串 
     * @param b 字节数组 
     * @return 字符串 
     */  
    private static String byteToHexString(byte b) {  
        int ret = b;  
        if (ret < 0) {  
            ret += 256;  
        }  
        int m = ret / 16;  
        int n = ret % 16;  
        return hexDigits[m] + hexDigits[n];  
    }  
  
    /** 
     * 转换字节数组为十六进制字符串 
     * @param bytes 字节数组 
     * @return 十六进制字符串 
     */  
    private static String byteArrayToHexString(byte[] bytes) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < bytes.length; i++) {  
            sb.append(byteToHexString(bytes[i]));  
        }  
        return sb.toString();  
    }  
}
