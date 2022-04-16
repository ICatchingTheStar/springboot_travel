package com.travel.mybootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.mybootdemo.bean.Favorite;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-12-23
 * @Description:
 */

public interface FavoriteService extends IService<Favorite> {
    void addFavorite(int rid,int uid);
}
