package com.travel.mybootdemo.utils;


import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-09-21
 * @Description: 产生UUID 随机的唯一字符串工具类
 */
@Service
public class UuidCodeUtils {
    private UuidCodeUtils(){}
    public String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
