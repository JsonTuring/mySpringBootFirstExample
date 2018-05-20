package com.imooc.enums;

/**
 * Created by JSON on 2018/05/01.
 */
public enum InterfaceResultEnum {
    UNKONW_ERROR(-1,"系统错误,错误内容请查看系统日志"),
    SUCCESS(0,"成功"),
    HISYPBM(9,"HIS药品编码不能为空"),
    LOGIN_ERROR(1,"用户名或密码错误"),
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
