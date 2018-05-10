package com.imooc.frame;

import com.imooc.frame.utils.MapUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by JSON on 2018/04/27.
 */
public class JBaseController{

    public Map<String, Object> map;

    public JBaseController() {
    }

    public void initMap(HttpServletRequest request) {
        this.map = MapUtil.doParameterMap(true, request);
    }
}
