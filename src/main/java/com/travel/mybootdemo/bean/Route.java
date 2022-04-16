package com.travel.mybootdemo.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-08-44
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tab_route")
@ApiModel(description = "旅游路线的实体")
public class Route {
    @TableId("rid")
    @ApiModelProperty(value = "线路id")
    private int rid;//线路id，必输
    @TableField("rname")
    @ApiModelProperty(value = "线路名")
    private String rname;//线路名称，必输
    @TableField("price")
    @ApiModelProperty(value = "价格")
    private double price;//价格，必输
    @TableField("routeIntroduce")
    @ApiModelProperty(value = "该线路的介绍")
    private String routeIntroduce;//线路介绍
    @TableField("rflag")
    @ApiModelProperty(value = "上架标记")
    private String rflag;   //是否上架，必输，0代表没有上架，1代表是上架
    @TableField("rdate")
    @ApiModelProperty(value = "上架时间")
    private String rdate;   //上架时间
    @TableField("isThemeTour")
    @ApiModelProperty(value = "是否主题旅游")
    private String isThemeTour;//是否主题旅游，必输，0代表不是，1代表是
    @TableField("count")
    @ApiModelProperty(value = "被收藏的次数")
    private int count;//收藏数量
    @TableField("cid")
    @ApiModelProperty(value = "所属分类")
    private int cid;//所属分类，必输
    @TableField("rimage")
    @ApiModelProperty(value = "相关图片")
    private String rimage;//缩略图
    @TableField("sid")
    @ApiModelProperty(value = "所属商家")
    private int sid;//所属商家
    @TableField("sourceId")
    @ApiModelProperty(value = "抓取数据的来源id")
    private String sourceId;//抓取数据的来源id

    //以下是不存在与数据库表中的字段，更改exist标签为false
    @TableField(exist = false)
    @ApiModelProperty(value = "分类，后台所需数据字段，不在数据库中")
    private Category category;//所属分类
    @TableField(exist = false)
    @ApiModelProperty(value = "商家，后台所需数据字段，不在数据库中")
    private Seller seller;//所属商家
    @TableField(exist = false)
    @ApiModelProperty(value = "图片列表，后台所需数据字段，不在数据库中")
    private List<RouteImg> routeImgList;//商品详情图片列表
}
