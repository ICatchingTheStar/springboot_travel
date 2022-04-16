package com.travel.mybootdemo.controller;

import com.travel.mybootdemo.bean.LoginUser;
import com.travel.mybootdemo.bean.User;
import com.travel.mybootdemo.bean.UserIsFavorite;
import com.travel.mybootdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-10-30
 * @Description: 和用户操作相关的控制器
 */
@Controller
@Api(tags = "用户控制器")
public class userController {
    @Autowired
    UserService userService;

    /**
     * 注册页面
     * @return
     */
    @GetMapping("/register")
    @ApiOperation(value = "注册页面",notes = "返回注册页面")
    public String registerPage(){
        return "register";
    }

    /**
     * 处理用户注册请求，检验验证码和用户名后完成数据库的数据添加和发送邮件功能
     * @param user      用户注册的用户信息
     * @param model
     * @param session
     * @param check     验证码
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "进行注册",notes = "处理用户注册请求，检验验证码和用户名后完成数据库的数据添加和发送邮件功能")
    public String doRegister(User user,
                             Model model,
                             HttpSession session,
                             @RequestParam("check") String check){
        //拿到后台验证码，立刻删除session中的后台验证码，保持验证码唯一性
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        //如果验证码不正确
        if (!StringUtils.hasLength(check) || !checkcode_server.equalsIgnoreCase(check)){
            model.addAttribute("msg","验证码错误！");
            return "register";//无需再执行后面的操作
        }
        //验证码正确，检验用户名是否重复
        boolean flag = userService.regist(user);
        if (flag){
            //注册成功
            return "redirect:/registerOk";
        }else {
            //注册失败
            model.addAttribute("msg","用户名重复，注册失败！");
            return "register";
        }
    }

    /**
     * 注册成功页面
     * @return
     */
    @GetMapping("/registerOk")
    @ApiOperation(value = "注册成功",notes = "注册成功")
    public String registerOK(){
        return "register_ok";
    }

    /**
     * 用户点击邮件中的链接后，设置用户注册的信息为"成功激活"状态
     * @param code  激活码，数据唯一
     * @param model
     * @return
     */
    @GetMapping("/activeUser")
    @ApiOperation(value = "激活账号",notes = "用户点击邮件中的链接后，设置用户注册的信息为\'成功激活\'状态")
    public String activeUser(@RequestParam("code") String code,
                             Model model){
        if (code != null){
            //使用active方法完成激活
            boolean flag = userService.active(code);
            //激活成功
            if (flag){
                model.addAttribute("msg","激活成功！请登录。");
                return "login";
            }
            //失败则什么也不做
        }
        model.addAttribute("msg","激活失败!");
        return "register";
    }

    /**
     * 登录页
     * @return
     */
    @GetMapping("/login")
    @ApiOperation(value = "登录页面",notes = "返回登录页面")
    public String loginPage(){
        return "login";
    }

    /**
     * 处理登录请求，效验验证码和数据库一致性后，往session中添加用户登录状态
     * @param loginUser     前台填写的登录数据
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/loginUser")
    @ApiOperation(value = "用户登录",notes = "处理登录请求，效验验证码和数据库一致性后，往session中添加用户登录状态")
    public String doLogin(LoginUser loginUser,
                          HttpSession session,
                          Model model){
        if (loginUser==null){
            model.addAttribute("msg","请输入账户密码");
            return "login";
        }

        //拿到后台的验证码后立刻删除掉，保证验证码唯一性
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        //如果前台验证码和后台不一致
        if (!StringUtils.hasLength(loginUser.getCheck()) || !checkcode_server.equalsIgnoreCase(loginUser.getCheck())){
            model.addAttribute("msg","验证码错误！");
            return "login";//无需再执行后续操作，节省性能
        }
        //验证码正确，调用login方法完成用户查询
        UserIsFavorite user = userService.login(loginUser.getUsername(),loginUser.getPassword());
        //若用户存在则保存登录状态session
        if (user != null){
            session.setAttribute("loginUser",user);
            return "redirect:/index";
        }else {
            //否则发送错误信息
            model.addAttribute("msg","账号密码错误！");
            return "login";
        }
    }


}
