package com.imooc.service;

import com.imooc.domain.InterfaceResult;

import java.util.Map;

/**
 * @author zhuguoxiang
 * @date 2018/04/27
 */
public interface UserService {

    public InterfaceResult register(Map map);

    public Map login(Map map);

    public InterfaceResult sendForgotPasswordEmail(Map map);

    public InterfaceResult setNewPassword(Map map);
}