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
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("tab_favorite")
@ApiModel(description = "我的收藏实体")
public class Favorite {
    @TableId("rid")
    @ApiModelProperty(value = "该收藏路线的id")
    private int rid;//旅游线路对象id
    @TableField("date")
    @ApiModelProperty(value = "收藏时间记录")
    private String date;//收藏时间
    @TableField("uid")
    @ApiModelProperty(value = "收藏的用户id")
    private int uid;//所属用户id
}
