package com.imooc.frame.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * @author zhuguoxiang
 * @date 2018/04/27
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

    public static Map<String, Object> keysLowerCase(Map<String, Object> map) {
        HashMap m = new HashMap();
        if(map == null) {
            m = null;
        } else {
            Iterator var2 = map.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry entry = (Map.Entry)var2.next();
                m.put(((String)entry.getKey()).toLowerCase(), entry.getValue());
            }
        }

        return m;
    }

    public static List<Map<String, Object>> keysLowerCase(List<Map<String, Object>> list) {
        if(list != null) {
            Iterator var1 = list.iterator();

            while(var1.hasNext()) {
                Map m = (Map)var1.next();
                new HashMap();
                Map tmp = keysLowerCase(m);
                m.clear();
                m.putAll(tmp);
            }
        }

        return list;
    }

    public static Map<String, Object> keysUpperCase(Map<String, Object> map) {
        HashMap m = new HashMap();
        if(map == null) {
            m = null;
        } else {
            Iterator var2 = map.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry entry = (Map.Entry)var2.next();
                m.put(((String)entry.getKey()).toUpperCase(), entry.getValue());
            }
        }

        return m;
    }

    public static List<Map<String, Object>> keysUpperCase(List<Map<String, Object>> list) {
        if(list != null && list.size() != 0) {
            Iterator var1 = list.iterator();

            while(var1.hasNext()) {
                Map m = (Map)var1.next();
                new HashMap();
                Map tmp = keysUpperCase(m);
                m.clear();
                m.putAll(tmp);
            }
        }

        return list;
    }

}
