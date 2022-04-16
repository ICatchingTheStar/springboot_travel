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
@TableName("tab_category")
@ApiModel(description = "分类实体")
public class Category {
    @ApiModelProperty(value = "分类id")
    @TableId("cid")
    private int cid;//分类id
    @ApiModelProperty(value = "分类名称")
    @TableField("cname")
    private String cname;//分类名称
}
