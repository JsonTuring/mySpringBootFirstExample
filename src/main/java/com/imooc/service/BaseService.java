package com.imooc.service;

import com.imooc.comm.filter.Const;
import com.imooc.frame.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuguoxiang
 * @date 2018/05/24
 */
public class BaseService {
    private final static Logger logger = LoggerFactory.getLogger(BaseService.class);
    protected String getEncryptedPwd(String password){
        try{
            String pwd = MD5Util.encrypt(password+ Const.PASSWORD_KEY);
            System.out.println(pwd);
            return pwd;
        }catch(Exception e){
            logger.error("密码加密异常：",e);
        }
        return null;
    }
}