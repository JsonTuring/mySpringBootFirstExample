package com.imooc.action;

import com.imooc.frame.JBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by JSON on 2018/05/10.
 */
@Controller
public class IndexController extends JBaseController {

    @RequestMapping(value="/")
    public String home() {
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
}
