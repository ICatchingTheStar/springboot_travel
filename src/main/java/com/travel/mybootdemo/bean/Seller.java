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
@TableName("tab_seller")
@ApiModel(description = "商家实体")
public class Seller {
    @TableId("sid")
    @ApiModelProperty(value = "商家id")
    private int sid;//商家id
    @TableField("sname")
    @ApiModelProperty(value = "商家名称")
    private String sname;//商家名称
    @TableField("consphone")
    @ApiModelProperty(value = "商家联系电话")
    private String consphone;//商家电话
    @TableField("address")
    @ApiModelProperty(value = "商家地址")
    private String address;//商家地址
}
