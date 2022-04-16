package com.travel.mybootdemo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-08-49
 * @Description: 用于封装用户登录时输入的数据对象
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "登录用户的实体")
public class LoginUser {
    @ApiModelProperty(value = "登录用户的账户名")
    private String username;//账户
    @ApiModelProperty(value = "登录用户的密码")
    private String password;//密码
    @ApiModelProperty(value = "登录用户的验证码")
    private String check;//验证码
}
