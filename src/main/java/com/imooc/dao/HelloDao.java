package com.imooc.dao;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by JSON on 2018/04/27.
 */
@Component
public interface HelloDao {
    List<Map> getData(Map map);
}
