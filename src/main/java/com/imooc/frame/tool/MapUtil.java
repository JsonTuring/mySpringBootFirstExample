package com.imooc.frame.tool;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by JSON on 2018/04/27.
 */
public class MapUtil {

    public MapUtil(){
    }

    public static Map<String, Object> doParameterMap(boolean isChangeArrayCast, HttpServletRequest request) {
        Map parameter = request.getParameterMap();
        HashMap param = new HashMap();
        if(parameter != null) {
            Set set = parameter.entrySet();
            Iterator var5 = set.iterator();

            while(true) {
                Map.Entry e;
                String[] obj;
                do {
                    do {
                        if(!var5.hasNext()) {
                            return param;
                        }

                        e = (Map.Entry)var5.next();
                        obj = (String[])e.getValue();
                        if(obj instanceof String[]) {
                            String[] str = (String[])obj;

                            for(int i = 0; i < str.length; ++i) {
                                str[i] = str[i].trim();
                                str[i] = str[i].replaceAll("\r\n", "");
                                str[i] = str[i].replaceAll("\u007f", "");
                                str[i] = str[i].replaceAll("\u001c\u007f", "");
                                str[i] = str[i].replaceAll("\t", "");
                                str[i] = str[i].replaceAll("\t", "");
                                str[i] = str[i].replaceAll("\u001c", "");
                            }
                        }
                    } while(obj == null);
                } while(obj != null && obj.length <= 0);

                if(isChangeArrayCast) {
                    if(obj.length == 1) {
                        param.put(e.getKey(), obj[0]);
                    } else if(obj.length >= 2) {
                        param.put(e.getKey(), obj);
                    }
                } else {
                    param.put(e.getKey(), obj);
                }
            }
        } else {
            return param;
        }
    }

}
