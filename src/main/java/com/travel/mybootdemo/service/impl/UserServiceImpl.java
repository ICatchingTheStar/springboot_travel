package com.travel.mybootdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mybootdemo.bean.User;
import com.travel.mybootdemo.bean.UserIsFavorite;
import com.travel.mybootdemo.mapper.UserMapper;
import com.travel.mybootdemo.service.UserService;
import com.travel.mybootdemo.utils.MailUtils;
import com.travel.mybootdemo.utils.UuidCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-12-48
 * @Description: 操作用户表的数据
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private UuidCodeUtils uuidCodeUtils;
    @Autowired
    private MailUtils mailUtils;

    /**
     * 进行用户的登录处理
     * @param username
     * @param password
     * @return
     */
    @Override
    public UserIsFavorite login(String username, String password) {
        //根据传递的用户名和密码进行查询
        User one = userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("password", password));
        //如果有对象且激活状态正确
        if ( one != null && one.getStatus().equals("Y")){
            //前台需要登录用户的名字以及id进行其他操作，其他字段不需要
            //在此处new一个空对象，把需要的值存入，然后返回这个空对象
            UserIsFavorite user = new UserIsFavorite();
            user.setUid(one.getUid());
            user.setName(one.getName());
            return user;
        }
        //否则直接返回空
        return null;
    }

    /**
     * 进行用户的注册操作
     * @param user
     * @return
     */
    @Override
    public Boolean regist(User user) {
        User selectOne = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (selectOne != null){
            return false;
        }
        //调用工具类，设置唯一激活码
        user.setCode(uuidCodeUtils.getUuid());
        user.setStatus("N");//设置激活状态，该状态只有激活后才改变成Y
        userMapper.insertRegister(user);//把设置好的对象存入数据库

        //发送激活邮件
        //激活链接属于项目内部链接，而腾讯邮箱属于外包应用，所以暂时无法实现跳转。若有真正的服务器地址，可以把邮件链接改成自己的服务器地址，就可以实现跳转
        String content = "<a href='/activeUser?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";//设置邮件内容
        mailUtils.sendMimeMessge(user.getEmail(),"黑马旅游网激活邮件",content);//使用邮件工具类发送邮件
        return true;
    }

    /**
     * 用户注册成功后续的 激活操作
     * @param code
     * @return
     */
    @Override
    public boolean active(String code){
        //根据传入的激活码，查询是否存在此用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("code", code));
        //存在
        if (user != null){
            userMapper.updateStatus(code);//更改用户的激活状态为"Y"
            return true;
        }else {
            //没有
            return false;
        }
    }
}
