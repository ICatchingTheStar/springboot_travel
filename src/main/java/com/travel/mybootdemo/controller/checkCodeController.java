package com.travel.mybootdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-09-23
 * @Description: 生成验证码工具
 */
@Controller
@Api(tags = "验证码控制器")
public class checkCodeController {
    /**
     * 生成验证码
     * @param session
     * @param response
     */
    @GetMapping("/checkCode")
    @ApiOperation(value = "验证码",notes = "生产验证码")
    public void checkCode(HttpSession session,
                          HttpServletResponse response){
        //通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width=80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();//获取画笔
        g.setColor(Color.GRAY);//设置画笔颜色为灰色
        g.fillRect(0,0,width,height);//填充图片

        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i =1; i <= 4; i++){
            int index = r.nextInt(size);//产生0到size-1的随机值
            char c = base.charAt(index);//在base字符串中获取下标为index的字符
            sb.append(c);//将c放入到StringBuffer中去
        }
        String getCheckCode = sb.toString();

        String checkCode = getCheckCode;//产生4个随机验证码, 12Ey
        session.setAttribute("CHECKCODE_SERVER",checkCode);//将验证码放入HttpSession中

        g.setColor(Color.YELLOW);//设置画笔颜色为黄色
        g.setFont(new Font("黑体", Font.BOLD,24));//设置字体的小大
        g.drawString(checkCode,15,25);//向图片上写入验证码

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        try {
            ImageIO.write(image,"PNG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
