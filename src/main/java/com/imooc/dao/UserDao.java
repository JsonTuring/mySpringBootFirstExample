package com.imooc.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhuguoxiang
 * @date 2018/04/27
 */

@Component
public interface UserDao {

    Map findByEmail(@Param("email") String email);

    Map findByUserName(@Param("username") String userName);

    void saveRegister(Map map);

    Map getUserInfoByUsernameAndPwd(@Param("username") String userName, @Param("password") String password);

    void setNewPassword(@Param("newpassword") String newpassword, @Param("email") String email);

    void setOutDateAndValidataCode(@Param("outdate") String outdate, @Param("validatecode") String secretkey, @Param("email") String email);
}