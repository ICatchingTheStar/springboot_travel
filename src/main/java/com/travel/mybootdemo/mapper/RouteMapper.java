package com.travel.mybootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.travel.mybootdemo.bean.Route;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-10-16
 * @Description:
 */

public interface RouteMapper extends BaseMapper<Route> {
    void updateCount(int rid);
}
