package com.antsix.chapter1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringBootMailSenderTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail(){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("贪玩蓝月<yhzhaohy@163.com>");
        message.setTo("535273959@qq.com");
        message.setSubject("贪玩蓝月新区开服");
        message.setText("大扎好，我系轱天乐，我四渣渣辉，探挽懒月，介四里没有挽过的船新版本，挤需体验三番钟，里造会干我一样，爱象节款游戏");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date sendDate = new Date();
        try {
            sendDate = dateFormat.parse("2019-04-19 11:30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        message.setSentDate(sendDate);
        mailSender.send(message);
    }

    @Test
    public void sendAttachmentMail() throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setFrom("yhzhaohy@163.com");
        messageHelper.setTo("535273959@qq.com");
        messageHelper.setSubject("带附件");
        messageHelper.setText("邮件包含有附件");

        File file = new File("tanwan.png");

        if(file.exists()){
            System.out.println("sending......");
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            messageHelper.addAttachment("附件1.png",fileSystemResource);
        }
        mailSender.send(mimeMessage);
    }

    /**
     * 使用163邮箱，会被网易拒绝
     * @throws Exception
     */
    @Test
    public void sendInlineMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("535273959@qq.com");
        helper.setTo("yhzhaohy@163.com");
        helper.setSubject("嵌入资源");
        helper.setText("<html><body>贪玩蓝月<img src=\"cid:tanwan\" ></body></html>", true);

        //这里需要注意的是addInline函数中资源名称weixin需要与正文中cid:weixin对应起来
        FileSystemResource file = new FileSystemResource(new File("tanwan.png"));
        helper.addInline("tanwan", file);

        mailSender.send(mimeMessage);
    }

}
