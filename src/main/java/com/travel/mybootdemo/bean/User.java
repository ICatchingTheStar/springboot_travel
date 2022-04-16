package com.travel.mybootdemo.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-08-43
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tab_user")
@ApiModel(description = "用户实体")
public class User {
    @TableId("uid")
    @ApiModelProperty(value = "用户id")
    private int uid;//用户id
    @TableField("username")
    @ApiModelProperty(value = "用户昵称，账户")
    private String username;//用户名，账号
    @TableField("password")
    @ApiModelProperty(value = "密码")
    private String password;//密码
    @TableField("name")
    @ApiModelProperty(value = "真实姓名")
    private String name;//真实姓名
    @TableField("birthday")
    @ApiModelProperty(value = "生日")
    private String birthday;//出生日期
    @TableField("sex")
    @ApiModelProperty(value = "性别")
    private String sex;//男或女
    @TableField("telephone")
    @ApiModelProperty(value = "联系电话")
    private String telephone;//手机号
    @TableField("email")
    @ApiModelProperty(value = "邮箱")
    private String email;//邮箱
    @TableField("status")
    @ApiModelProperty(value = "激活状态")
    private String status;//激活状态，Y代表激活，N代表未激活
    @TableField("code")
    @ApiModelProperty(value = "激活码，要求唯一")
    private String code;//激活码（要求唯一）
}
