package com.imooc.service;

import com.imooc.dao.HelloDao;
import com.imooc.domain.InterfaceResult;
import com.imooc.enums.InterfaceResultEnum;
import com.imooc.frame.utils.InterfaceResultUtil;
import com.imooc.frame.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by JSON on 2018/04/27.
 */
@Service
public class HelloServiceImpl implements HelloService {
    private final static Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Resource
    private HelloDao helloDao;
    @Override
    public Map getData(Map map) {
        List<Map> listMap =  helloDao.getData(map);
        Map<Object, Object> resultMap = new HashMap<>();
        resultMap.put("rows", listMap);
        return resultMap;
    }

    @Override
    public InterfaceResult getInterfaceData(Map map) {
        InterfaceResult interfaceResult;
        try {
            if(StringUtil.isEmpty(map.get("hisypbm"))){
                interfaceResult = InterfaceResultUtil.error(InterfaceResultEnum.HISYPBM);
                return interfaceResult;
            }
            List<Map> listMap =  helloDao.getData(map);
            interfaceResult = InterfaceResultUtil.success(InterfaceResultEnum.SUCCESS,listMap);
        }
        catch (Exception e){
            //记录ERROR级别日志 TRACE < DEBUG < INFO < WARN < ERROR
            logger.error(e.toString());
            interfaceResult = InterfaceResultUtil.error(InterfaceResultEnum.UNKONW_ERROR);
        }
        return interfaceResult;
    }
}
