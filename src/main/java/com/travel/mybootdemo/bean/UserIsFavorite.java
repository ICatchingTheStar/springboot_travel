package com.travel.mybootdemo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-08-50
 * @Description: 用于处理用户收藏时判断的对象
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "当前登录用户")
public class UserIsFavorite {
    @ApiModelProperty(value = "当前登录用户的id")
    private int uid;    //当前登录的用户id
    @ApiModelProperty(value = "当前登录用户的昵称")
    private String name;//当前登录的用户昵称
}
