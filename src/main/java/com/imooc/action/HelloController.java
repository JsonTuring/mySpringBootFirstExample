package com.imooc.action;

import com.imooc.GirlProperties;
import com.imooc.domain.InterfaceResult;
import com.imooc.frame.JBaseController;
import com.imooc.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JSON on 2018/04/22.
 */
@RestController
public class HelloController extends JBaseController{
    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private GirlProperties girlProperties;

    @Resource
    private HelloService helloService;

//    @Value("${cupSize}")
//    private String cupSize;
//
//    @Value("${age}")
//    private Integer age;
//
//    @Value("${content}")
//    private String content;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say(){
        return girlProperties.getCupSize();
    }
    @RequestMapping(value = "/hellojson", method = RequestMethod.GET)
    public GirlProperties getJson(){
        return girlProperties;
    }

    @GetMapping(value = "/getdata")
    public Map getdata(HttpServletRequest request){
        initMap(request);
        Map<String,Object> resultMap = helloService.getData(map);
        return resultMap;
    }
    @GetMapping(value = "/getInterfaceData")
    public InterfaceResult getInterfaceData(HttpServletRequest request){
        initMap(request);
        InterfaceResult ires = helloService.getInterfaceData(map);
        return ires;
    }
}
