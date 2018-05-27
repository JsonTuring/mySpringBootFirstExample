package com.imooc.action;

import com.imooc.comm.filter.Const;
import com.imooc.domain.InterfaceResult;
import com.imooc.enums.InterfaceResultEnum;
import com.imooc.frame.utils.InterfaceResultUtil;
import com.imooc.frame.utils.StringUtil;
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
public class UserController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public InterfaceResult login(HttpServletRequest request, HttpServletResponse response) {
        try {
            //格式化请求入参
            initMap(request);
            //用户名不许为空
            if(StringUtil.isEmpty(map.get("username"))){
                return InterfaceResultUtil.error(InterfaceResultEnum.USER_NOT_NULL);
            }
            //密码不许为空
            if(StringUtil.isEmpty(map.get("password"))){
                return InterfaceResultUtil.error(InterfaceResultEnum.PASSWORD_NOT_NULL);
            }
            //判断登录
            Map resultMap = userService.login(map);
            if(resultMap == null){
                //登录失败，返回失败信息
                return InterfaceResultUtil.error(InterfaceResultEnum.LOGIN_ERROR);
            }
            //登录成功，session中记录当前用户信息，返回成功信息及成功页面路径
            request.getSession().setAttribute(Const.LOGIN_SESSION_KEY,
                    resultMap);
            return InterfaceResultUtil.success(InterfaceResultEnum.SUCCESS,"/");
        }catch (Exception e){
            logger.error("用户登录错误："+e.toString());
            return InterfaceResultUtil.error(InterfaceResultEnum.UNKONW_ERROR);
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public InterfaceResult register(HttpServletRequest request, HttpServletResponse response) {
        //格式化请求入参
        initMap(request);
        InterfaceResult result = userService.register(map);
        return result;

    }

    @RequestMapping(value = "/sendForgotPasswordEmail", method = RequestMethod.POST)
    public InterfaceResult sendForgotPasswordEmail(HttpServletRequest request, HttpServletResponse response) {
        //格式化请求入参
        initMap(request);
        InterfaceResult result = userService.sendForgotPasswordEmail(map);
        return result;
    }

    @RequestMapping(value = "/setNewPassword", method = RequestMethod.POST)
    public InterfaceResult setNewPassword(HttpServletRequest request, HttpServletResponse response) {
        //格式化请求入参
        initMap(request);
        InterfaceResult result = userService.setNewPassword(map);
        return result;
    }
}
