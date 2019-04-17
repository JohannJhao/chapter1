package com.antsix.common.aop;


import com.mongodb.BasicDBObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(1)
@Aspect
public class MongoWebLogAspect {

    private Logger logger = Logger.getLogger("mongodb");

    @Pointcut("execution(public * com.antsix.web..*.*(..))")
    public void mongoWebLog(){}


    @Before("mongoWebLog()")
    public void dobefore(JoinPoint joinpoint){
        //获取HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取要记录的日志内容
        //通过logger.info()，输出BasicDBObject对象的信息到mongodb
        BasicDBObject logInfo = getBasicDBObject(request, joinpoint);
        logger.info(logInfo);
    }

    private BasicDBObject getBasicDBObject(HttpServletRequest request, JoinPoint joinpoint){
        //基本信息
        BasicDBObject r = new BasicDBObject();
        r.append("requestURL",request.getRequestURL().toString());
        r.append("requestURI",request.getRequestURI());
        r.append("queryString",request.getQueryString());
        r.append("remoteAddr",request.getRemoteAddr());
        r.append("remoteHost",request.getRemoteHost());
        r.append("remotePort",request.getRemotePort());
        r.append("localAddr",request.getLocalAddr());
        r.append("localName",request.getLocalName());
        r.append("method",request.getMethod());
        r.append("headers",getHeadersInfo(request));
        r.append("parameters",request.getParameterMap());
        r.append("classMethod",joinpoint.getSignature().getDeclaringTypeName()+"."+joinpoint.getSignature().getName());
        r.append("args", Arrays.toString(joinpoint.getArgs()));
        return r;
    }

    /**
     * 获取HttpServletRequest 的 Header信息
     * @param request
     * @return
     */
    public Map<String,String> getHeadersInfo(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()){
            String key = (String)headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key,value);
        }
        return map;
    }
}
