package com.imooc.dao;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface UserDao {
    Map getUserInfo(Map map);
}