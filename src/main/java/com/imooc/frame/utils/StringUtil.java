package com.imooc.frame.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JSON on 2018/05/01.
 */
public class StringUtil {
    public StringUtil() {
    }

    public static String getValueFromUrlStr(String sUrlStr, String sKey) {
        String sValue = "";
        String[] sParaStr = sUrlStr.split("&");
        if(sParaStr.length > 0) {
            String[] var4 = sParaStr;
            int var5 = sParaStr.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String sPara = var4[var6];
                String[] sStr = sPara.split("=");
                if(sStr.length > 1 && sStr[0].equals(sKey)) {
                    sValue = sStr[1];
                    break;
                }
            }
        }

        return sValue;
    }


    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isEmpty(Object object) {
        return object == null || "".equals(object);
    }
}
