package com.travel.mybootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.mybootdemo.bean.Route;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-10-17
 * @Description:
 */

public interface RouteService extends IService<Route> {
    Route findOne(int rid);
    Boolean findFavorite(int rid,int uid);
    void addCount(int rid);
}
