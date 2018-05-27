package com.imooc.action;

import com.imooc.comm.filter.Const;
import com.imooc.frame.utils.MapUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author zhuguoxiang
 * @date 2018/04/27
 */
public class BaseController {
    public Map<String, Object> map;

    public BaseController() {
    }

    public void initMap(HttpServletRequest request) {
        this.map = MapUtil.doParameterMap(true, request);
    }

    protected HttpServletRequest getRequest(){
        return ((ServletRequestAttributes)RequestContextHolder
                .getRequestAttributes()).getRequest();
    }

    protected HttpSession getSession(){
        return getRequest().getSession();
    }

    protected Map getUser(){
        return (Map)getSession().getAttribute(Const.LOGIN_SESSION_KEY);
    }




}
