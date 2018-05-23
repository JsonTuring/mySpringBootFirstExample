package com.imooc.service;

import com.imooc.domain.InterfaceResult;

import java.util.Map;

public interface UserService {
    public Map getUserInfo(Map map);
    public InterfaceResult register(Map map);
}