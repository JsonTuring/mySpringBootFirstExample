package com.imooc.frame.utils;

/**
 * @author zhuguoxiang
 * @date 2018/05/26
 */
public class MessageUtil {
    public static String getMessage(String template, String keys, String path) {
//        int count = 0;
//        for (String key : keys) {
//            template = template.replace("{" + count++ + "}", key);
//        }
        String newPath = template.replace("REPLACE_PATH",keys);
        String newReturnPath = newPath.replace("REPLACE_NEWPWD_PATH",path);
        return newReturnPath;
    }
}