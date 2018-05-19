package com.imooc.action;

import com.imooc.comm.filter.Const;
import com.imooc.frame.JBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JSON on 2018/05/12.
 */
@Controller
@RequestMapping("/user")
public class UserController extends JBaseController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("当前请求用户："+request.getParameter("username").toString()+request.getParameter("password"));
        request.getSession().setAttribute(Const.LOGIN_SESSION_KEY,request.getParameter("username").toString());
        System.out.println("登录sesion-id:"+request.getSession().getId());
        return "/home";
    }
}
