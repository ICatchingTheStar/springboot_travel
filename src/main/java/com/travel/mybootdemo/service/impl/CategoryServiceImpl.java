package com.travel.mybootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mybootdemo.bean.Category;
import com.travel.mybootdemo.mapper.CategoryMapper;
import com.travel.mybootdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-09-40
 * @Description: 操作数据库的分类表
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
