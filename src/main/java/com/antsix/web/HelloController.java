package com.antsix.web;


import com.antsix.common.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @ResponseBody
    @RequestMapping("/hello")
    public String hello()  throws Exception {
        throw new Exception("发生错误");
        //return "Hello World";
    }

    @ResponseBody
    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    public String hello1(String name) {
        return "Hello "+name;
    }

    @RequestMapping("/")
    public String index(ModelMap map){
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host","http://antsix.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }
    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
}
