package com.imooc.service;

import com.imooc.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public Map getUserInfo(Map map) {
        Map resultMap = userDao.getUserInfo(map);
        return resultMap;
    }
}