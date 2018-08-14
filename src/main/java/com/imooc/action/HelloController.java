package com.imooc.action;

import com.imooc.domain.InterfaceResult;
import com.imooc.service.HelloService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by JSON on 2018/04/22.
 */
@RestController
public class HelloController extends BaseController {

    @Resource
    private HelloService helloService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say(){
        return "";
    }

    @GetMapping(value = "/getdata")
    public Map getdata(HttpServletRequest request, HttpServletResponse response){
        initMap(request);
        Map<String,Object> resultMap = helloService.getData(map);

        return resultMap;
    }
    @GetMapping(value = "/getInterfaceData")
    public InterfaceResult getInterfaceData(HttpServletRequest request,HttpServletResponse response){
        initMap(request);
        InterfaceResult ires = helloService.getInterfaceData(map);

        return ires;
    }

}
