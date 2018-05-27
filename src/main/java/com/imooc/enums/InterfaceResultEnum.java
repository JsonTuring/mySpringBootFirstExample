package com.imooc.enums;

/**
 * Created by JSON on 2018/05/01.
 */
public enum InterfaceResultEnum {
    UNKONW_ERROR(-1,"系统错误,错误内容请查看系统日志"),
    SUCCESS(0,"成功"),
    HISYPBM(9,"HIS药品编码不能为空"),
    LOGIN_ERROR(1,"用户名或密码错误"),
    EMAIL_USED(2,"该邮箱已被注册"),
    EMAIL_NOT_REGISTER(201,"该邮箱地址未注册"),
    LINK_OUTDATED(202,"该链接已过期，请重新请求"),
    USERNAME_USED(3,"该登录名称已存在"),
    EMAIL_NOT_NULL(4,"email不允许为空"),
    USER_NOT_NULL(5,"用户名不允许为空"),
    PASSWORD_NOT_NULL(6,"密码不允许为空"),
    ;

    private Integer code;

    private String msg;

    InterfaceResultEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
