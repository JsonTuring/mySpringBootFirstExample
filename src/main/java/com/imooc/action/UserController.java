package com.imooc.action;

import com.imooc.frame.JBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JSON on 2018/05/12.
 */
@RestController
@RequestMapping("/user")
public class UserController extends JBaseController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getAttribute("username").toString()+request.getAttribute("password"));
        return "/home";
    }
}
