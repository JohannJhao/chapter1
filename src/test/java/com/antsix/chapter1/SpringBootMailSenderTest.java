package com.antsix.chapter1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringBootMailSenderTest {

    @Autowired
    private JavaMailSender mailSender;

    // thymeleaf模板
    @Autowired
    private TemplateEngine thymeleafTemplate;

    // 通过该类获取freemarker模板。
    // Template temp = cfg.getTemplate("test.ftl");
    @Autowired
    private Configuration freemarkerConfig;

    // Velocity 相关
    @Autowired
    private VelocityEngine velocityEngine;
    /**
     *  发送简单的文本邮件
     */
    @Test
    public void sendSimpleMail(){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("贪玩蓝月<*****@163.com>");
        message.setTo("***@qq.com");
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

    /**
     * 发送包含附件的邮件
     * @throws MessagingException
     */
    @Test
    public void sendAttachmentMail() throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setFrom("*****@163.com");
        messageHelper.setTo("***@qq.com");
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
     *
     * 发送嵌有静态资源的邮件
     * 使用163邮箱，会被网易拒绝
     * @throws Exception
     */
    @Test
    public void sendInlineMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("***@qq.com");
        helper.setTo("*****@163.com");
        helper.setSubject("嵌入资源");
        helper.setText("<html><body>贪玩蓝月<img src=\"cid:tanwan\" ></body></html>", true);

        //这里需要注意的是addInline函数中资源名称weixin需要与正文中cid:weixin对应起来
        FileSystemResource file = new FileSystemResource(new File("tanwan.png"));
        helper.addInline("tanwan", file);

        mailSender.send(mimeMessage);
    }

    /**
     * 发送 Thymeleaf 模板邮件
     */
    @Test
    public void sendThymeleafMail() throws MessagingException{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("***@qq.com");
        helper.setTo("*****@163.com");
        helper.setSubject("Thymeleaf模板邮件");

        //添加正文，使用thymeleaf模板
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userName","Jacky");
        paramMap.put("content","德莱联盟");

        Context context = new Context();
        context.setVariables(paramMap);//传参可以直接传入一个Map
        String html = thymeleafTemplate.process("thymeleafMail",context);
        helper.setText(html,true);

        mailSender.send(mimeMessage);
    }

    /**
     *  发送 Freemarker 模板邮件
     */
    @Test
    public void sendFreemarkerMail() throws MessagingException{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("***@qq.com");
        helper.setTo("*****@163.com");
        helper.setSubject("Freemarker模板邮件");

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userName","Jacky");
        paramMap.put("content","德莱联盟");

        try {
            Template freemarkerTemplate = freemarkerConfig.getTemplate("freemarkerMail.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate,paramMap);
            helper.setText(html,true);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
    }

    /**
     *  发送Velocity模板邮件
     *  <p>Velocity模板，新版的SpringBoot已经弃用该模板发送邮件了，故不推荐使用</p>
     */
    @Test
    @Deprecated
    public void sendVelocityMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("***@qq.com");
        helper.setTo("*****@163.com");
        helper.setSubject("Velocity模板邮件");

        Map<String, Object> model = new HashedMap();
        model.put("username", "Jacky");
        model.put("content","德莱联盟");
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "velocityMail.vm", "UTF-8", model);
        helper.setText(text, true);

        mailSender.send(mimeMessage);
    }
}
