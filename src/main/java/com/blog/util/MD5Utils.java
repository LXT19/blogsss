package com.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对用户密码进行加密
 */
public class MD5Utils {

    public static String code(String str){
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte []byteDigest=md.digest();
            int i;
            StringBuffer buf=new StringBuffer("");
            for(int offest=0;offest<byteDigest.length;offest++){
                i=byteDigest[offest];
                if(i<0)
                    i+=256;
                if(i<16)
                    buf.append("a");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
