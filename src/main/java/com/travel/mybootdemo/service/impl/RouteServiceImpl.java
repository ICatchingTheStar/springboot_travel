package com.travel.mybootdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mybootdemo.bean.Favorite;
import com.travel.mybootdemo.bean.Route;
import com.travel.mybootdemo.bean.RouteImg;
import com.travel.mybootdemo.bean.Seller;
import com.travel.mybootdemo.mapper.FavoriteMapper;
import com.travel.mybootdemo.mapper.RouteImgMapper;
import com.travel.mybootdemo.mapper.RouteMapper;
import com.travel.mybootdemo.mapper.SellerMapper;
import com.travel.mybootdemo.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-10-17
 * @Description: 操作数据库的旅游路线表
 */
@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements RouteService {
    @Autowired
    RouteMapper routeMapper;
    @Autowired
    SellerMapper sellerMapper;
    @Autowired
    RouteImgMapper routeImgMapper;
    @Autowired
    FavoriteMapper favoriteMapper;

    /**
     * 获取到数据库中某一旅游路线的详情
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        //根据用户点击的商品编号，查询该旅游路线信息
        Route route = routeMapper.selectById(rid);
        //旅游路线信息中有商家编号，根据商家编号查询商家信息
        Seller seller = sellerMapper.selectById(route.getSid());
        //旅游路线信息中还有 旅游图片，一并查出来存好
        List<RouteImg> routeImgs = routeImgMapper.selectList(new QueryWrapper<RouteImg>().eq("rid", rid));

        //把商家信息和图片信息存入 route对象
        route.setSeller(seller);
        route.setRouteImgList(routeImgs);

        //返回处理好的route对象
        return route;
    }

    /**
     * 判断用户是否收藏过该路线
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public Boolean findFavorite(int rid, int uid) {
        //根据旅游路线rid和用户的uid，查询收藏表中是否有对应的收藏记录
        Favorite favorite = favoriteMapper.selectOne(new QueryWrapper<Favorite>().eq("rid", rid).eq("uid", uid));
        //如果有记录
        if (favorite!=null){
            return true;
        }
        //没有则说明还没有收藏过，返回false
        return false;
    }

    /**
     * 每次被收藏时，count+1
     * @param rid
     */
    @Override
    public void addCount(int rid) {
        routeMapper.updateCount(rid);
    }


}
