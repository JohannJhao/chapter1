package com.antsix.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 为什么使用 @RestController 注解，访问 localhost:8090，得到的返回结果是字符串 index，而不是index.html页面？
 *      因为@RestController注解默认返回json格式。
 *
 * @Controller：修饰class，用来创建处理http请求的对象
 * @RestController：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。
 * @RequestMapping：配置url映射
 *
 */
@Controller
@ApiIgnore
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    /**
     * curl 访问带有密码的URL
     * curl -u admin localhost:8090 会提示输入密码。
     *
     * curl 发送POST请求，并携带参数。（用于修改loggers/com.antsix的日志级别）
     * curl -H "Content-Type:application/json" -X POST --data "{\"configuredLevel\":\"DEBUG\"}" localhost:8090/loggers/com.antsix
     *
     */

    @RequestMapping("/")
    public String index(ModelMap map){
        logger.debug("#### HelloController DEBUG : DEBUG ");
        logger.info("#### HelloController INFO : INFO ");
        logger.error("#### HelloController ERROR : ERROR ");
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host","http://antsix.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

}
