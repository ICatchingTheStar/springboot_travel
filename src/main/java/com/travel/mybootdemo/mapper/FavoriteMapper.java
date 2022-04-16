package com.travel.mybootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.travel.mybootdemo.bean.Favorite;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-11-16
 * @Description:
 */

public interface FavoriteMapper extends BaseMapper<Favorite> {
    void addFavorite(@Param("rid") int rid, @Param("date") Date date, @Param("uid") int uid);
}
