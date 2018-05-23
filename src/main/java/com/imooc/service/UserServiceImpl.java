package com.imooc.service;

import com.imooc.action.UserController;
import com.imooc.dao.UserDao;
import com.imooc.domain.InterfaceResult;
import com.imooc.enums.InterfaceResultEnum;
import com.imooc.frame.utils.InterfaceResultUtil;
import com.imooc.frame.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    UserDao userDao;

    @Override
    public Map getUserInfo(Map map) {
        Map resultMap = userDao.getUserInfo(map);
        return resultMap;
    }

    @Override
    public InterfaceResult register(Map map) {
        try {
            //email不许为空
            if(StringUtil.isEmpty(map.get("email"))){
                return InterfaceResultUtil.error(InterfaceResultEnum.EMAIL_NOT_NULL);
            }
            //用户名不许为空
            if(StringUtil.isEmpty(map.get("username"))){
                return InterfaceResultUtil.error(InterfaceResultEnum.USER_NOT_NULL);
            }
            //密码不许为空
            if(StringUtil.isEmpty(map.get("password"))){
                return InterfaceResultUtil.error(InterfaceResultEnum.PASSWORD_NOT_NULL);
            }

            //判断邮箱是否已被注册
            Map userMap = userDao.findByEmail(map.get("email").toString());
            if (null != userMap) {
                return InterfaceResultUtil.error(InterfaceResultEnum.EMAIL_USED);
            }
            //判断用户名是否已被注册
            Map currUserMap = userDao.findByUserName(map.get("username").toString());
            if (null != currUserMap) {
                return InterfaceResultUtil.error(InterfaceResultEnum.USERNAME_USED);
            }
            //保存用户注册信息
            Date currDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("当前时间：" + sdf.format(currDate));
            map.put("createtime",sdf.format(currDate));
            map.put("id", UUID.randomUUID().toString());
            userDao.saveRegister(map);
            //注册成功，返回成功信息及登录页面路径
            return InterfaceResultUtil.success(InterfaceResultEnum.SUCCESS,"/login");
        } catch (Exception e){
            logger.error(e.toString());
            return InterfaceResultUtil.error(InterfaceResultEnum.UNKONW_ERROR);
        }
    }
}