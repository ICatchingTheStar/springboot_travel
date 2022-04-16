package com.travel.mybootdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-09-18
 * @Description: 发送邮箱工具类
 */
@Service
public class MailUtils {
    //    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    @Autowired
    private JavaMailSender mailSender;
    //    发件人
    private static final String SENDER = "wstimt@163.com";

    /**
     * 发送普通邮件
     * @param to        收件人
     * @param subject   主题名称
     * @param content   邮件内容
     */
//    public void sendSimpleMailMessge(String to,String subject,String content){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(SENDER);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(content);
//        mailSender.send(message);
//    }

    /**
     * 发送 HTML 邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */

    public void sendMimeMessge(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println(e+"\n发送邮箱时异常！");
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件路径
     */

//    public void sendMimeMessge(String to, String subject, String content, String filePath) {
//        MimeMessage message = mailSender.createMimeMessage();
//        try {
//            //true表示需要创建一个multipart message
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(SENDER);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(content, true);
//
//            FileSystemResource file = new FileSystemResource(new File(filePath));
//            String fileName = file.getFilename();
//            helper.addAttachment(fileName, file);
//
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            logger.error("发送带附件的MimeMessge时发生异常！", e);
//        }
//    }

    /**
     * 发送带静态文件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param rscIdMap 需要替换的静态文件
     */

//    public void sendMimeMessge(String to, String subject, String content, Map<String, String> rscIdMap) {
//        MimeMessage message = mailSender.createMimeMessage();
//        try {
//            //true表示需要创建一个multipart message
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(SENDER);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(content, true);
//
//            for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
//                FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
//                helper.addInline(entry.getKey(), file);
//            }
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            logger.error("发送带静态文件的MimeMessge时发生异常！", e);
//        }
//    }
}
