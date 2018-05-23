package com.imooc.dao;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface UserDao {
    Map getUserInfo(Map map);

    Map findByEmail(String email);

    Map findByUserName(String userName);

    void saveRegister(Map map);
}