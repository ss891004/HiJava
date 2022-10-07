package com.mp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SBMailTests {
    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    public void testSimpleMail() {
//封装简单的邮件内容
        SimpleMailMessage message = new SimpleMailMessage();
//邮件主题
        message.setSubject("放假通知");
        message.setText("春节放假7天");
//发件人
        message.setFrom("736486962@qq.com");
        message.setTo("mengxuegu666@163.com");
        javaMailSender.send(message);
    }

    //发送复杂邮件带附件和html的邮件
    @Test
    public void testMimeMail() throws MessagingException {
//创建一个发送复杂消息对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//通过消息帮助对象，来设置发送的内容
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
//邮件主题
        messageHelper.setSubject("放假通知");
//第2个参数为true表示是html
        messageHelper.setText("<h2 style='color:red'>春节放假7天</h2>", true);
//上传文件 (文件名，File或IO对象)
        messageHelper.addAttachment("1.jpg", new File("D:\\images\\1.jpg"));
        messageHelper.addAttachment("2.jpg", new File("D:\\images\\2.jpg"));
        messageHelper.addAttachment("3.jpg", new File("D:\\images\\3.jpg"));
//发件人
        messageHelper.setFrom("575726653@qq.com");
        messageHelper.setTo("575726653@qq.com");
        javaMailSender.send(mimeMessage);
    }
}