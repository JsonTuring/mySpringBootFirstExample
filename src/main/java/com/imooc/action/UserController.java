package com.imooc.action;

import com.imooc.comm.filter.Const;
import com.imooc.domain.InterfaceResult;
import com.imooc.enums.InterfaceResultEnum;
import com.imooc.frame.JBaseController;
import com.imooc.frame.utils.InterfaceResultUtil;
import com.imooc.service.HelloServiceImpl;
import com.imooc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zhuguoxiang
 * @date 2018/05/23
 */
@RestController
@RequestMapping("/user")
public class UserController extends JBaseController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public InterfaceResult login(HttpServletRequest request, HttpServletResponse response) {
        //格式化请求入参
        initMap(request);
        //判断登录
        Map resultMap = userService.getUserInfo(map);
        if(resultMap == null){
            //登录失败，返回失败信息
            return InterfaceResultUtil.error(InterfaceResultEnum.LOGIN_ERROR);
        }
        //登录成功，session中记录当前用户信息，返回成功信息及成功页面路径
        request.getSession().setAttribute(Const.LOGIN_SESSION_KEY,
                request.getParameter("username").toString());
        return InterfaceResultUtil.success(InterfaceResultEnum.SUCCESS,"/");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public InterfaceResult register(HttpServletRequest request, HttpServletResponse response) {
        //格式化请求入参
        initMap(request);
        InterfaceResult result = userService.register(map);
        return result;

    }

}
