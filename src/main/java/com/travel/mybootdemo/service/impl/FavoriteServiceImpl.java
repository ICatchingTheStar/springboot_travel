package com.travel.mybootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mybootdemo.bean.Favorite;
import com.travel.mybootdemo.mapper.FavoriteMapper;
import com.travel.mybootdemo.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-12-24
 * @Description: 操作数据库的收藏表
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {
    @Autowired
    FavoriteMapper favoriteMapper;

    /**
     * 用户收藏某旅游路线时的业务逻辑处理
     * @param rid
     * @param uid
     */
    @Override
    public void addFavorite(int rid, int uid) {
        Date date = new Date();
        favoriteMapper.addFavorite(rid,date,uid);
    }
}
