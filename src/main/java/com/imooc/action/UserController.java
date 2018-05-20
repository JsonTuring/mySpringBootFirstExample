package com.imooc.action;

import com.imooc.comm.filter.Const;
import com.imooc.domain.InterfaceResult;
import com.imooc.enums.InterfaceResultEnum;
import com.imooc.frame.JBaseController;
import com.imooc.frame.utils.InterfaceResultUtil;
import com.imooc.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by JSON on 2018/05/12.
 */

@RestController
@RequestMapping("/user")
public class UserController extends JBaseController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public InterfaceResult login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("当前请求用户："+request.getParameter("username").toString()+request.getParameter("password"));
        initMap(request);
        Map resultMap = userService.getUserInfo(map);
        if(resultMap == null){
            return InterfaceResultUtil.error(InterfaceResultEnum.LOGIN_ERROR);
        }
        request.getSession().setAttribute(Const.LOGIN_SESSION_KEY,request.getParameter("username").toString());
        System.out.println("登录sesion-id:"+request.getSession().getId());
        return InterfaceResultUtil.success(InterfaceResultEnum.SUCCESS,"/");



    }
}
