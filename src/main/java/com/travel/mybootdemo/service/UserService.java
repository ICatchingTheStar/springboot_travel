package com.travel.mybootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.mybootdemo.bean.LoginUser;
import com.travel.mybootdemo.bean.User;
import com.travel.mybootdemo.bean.UserIsFavorite;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-12-47
 * @Description:
 */

public interface UserService extends IService<User> {
    UserIsFavorite login(String username,String password);
    Boolean regist(User user);
    boolean active(String code);
}
