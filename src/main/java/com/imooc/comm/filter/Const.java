package com.imooc.comm.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Const {
    public static String BASE_PATH;
    public static String LOGIN_SESSION_KEY = "login_user";
    public static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";
    @Autowired(required = true)
    public void setBasePath(@Value("${system.base.path}")String basePath) {
        Const.BASE_PATH = basePath;
    }
}