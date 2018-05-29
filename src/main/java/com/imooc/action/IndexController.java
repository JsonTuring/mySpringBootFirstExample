package com.imooc.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * @author zhuguoxiang
 * @date 2018/05/10
 */
@Controller
public class IndexController extends BaseController {
    @RequestMapping(value="/")
    public String home(HttpServletRequest request, Model model) {
        System.out.println("home-sesion-id:"+request.getSession().getId());
        model.addAttribute("user",getUser());
        return "/home";
    }

    @RequestMapping(value="/index")
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/register";
    }

    @RequestMapping(value="/forgotPassword",method=RequestMethod.GET)
    public String forgotPassword() {
        return "user/forgotpassword";
    }

    @RequestMapping(value="/newPassword",method=RequestMethod.GET)
    public String newPassword() {
        return "user/newpassword";
    }

    @RequestMapping(value="/test")
    public String test(HttpServletRequest request, Model model) {
        model.addAttribute("user",getUser());
        return "/test_content/test";
    }
}
