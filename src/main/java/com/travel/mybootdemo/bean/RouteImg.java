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
 * @CreateTime: 2021-08-14-08-44
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tab_route_img")
@ApiModel(description = "旅游路线图片的实体")
public class RouteImg {
    @TableId("rgid")
    @ApiModelProperty(value = "商品图片id")
    private int rgid;//商品图片id
    @TableField("rid")
    @ApiModelProperty(value = "旅游路线id")
    private int rid;//旅游商品id
    @TableField("bigPic")
    @ApiModelProperty(value = "旅游图片大图")
    private String bigPic;//详情商品大图
    @TableField("smallPic")
    @ApiModelProperty(value = "旅游图片小图")
    private String smallPic;//详情商品小图
}
