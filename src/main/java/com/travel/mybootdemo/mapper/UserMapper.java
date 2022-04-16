package com.travel.mybootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.travel.mybootdemo.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-12-47
 * @Description:
 */

public interface UserMapper extends BaseMapper<User> {
    void insertRegister(@Param("user") User user);
    void updateStatus(String code);
}
