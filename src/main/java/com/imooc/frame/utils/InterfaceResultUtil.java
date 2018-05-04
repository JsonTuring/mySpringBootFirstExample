package com.imooc.frame.utils;

import com.imooc.domain.InterfaceResult;
import com.imooc.enums.InterfaceResultEnum;

/**
 * Created by JSON on 2018/04/30.
 */
public class InterfaceResultUtil {

    public static InterfaceResult success(InterfaceResultEnum ienum, Object object){
        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.setCode(ienum.getCode());
        interfaceResult.setMsg(ienum.getMsg());
        interfaceResult.setData(object);
        return interfaceResult;
    }

    public static InterfaceResult success(InterfaceResultEnum ienum){
        return success(ienum,null);
    }

    public static InterfaceResult error(InterfaceResultEnum ienum){
        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult.setCode(ienum.getCode());
        interfaceResult.setMsg(ienum.getMsg());
        return interfaceResult;
    }


}
