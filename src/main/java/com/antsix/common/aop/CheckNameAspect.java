package com.antsix.common.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Order(7)
@Component
public class CheckNameAspect {

    private Logger logger = Logger.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.antsix.web.HelloController.hello1(..))")
    public void checkName(){}

    @Before("checkName()")
    public void checkHelloName(JoinPoint joinPoint) throws Exception{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("[ IP ] : "+request.getRemoteAddr()+" Check Name !!! "+"[ ARGS ] : "+ Arrays.toString(joinPoint.getArgs()));
//        if(!Arrays.toString(joinPoint.getArgs()).contains("ZHAO")){
//            logger.error(" ####### Exception !!! ");
//            throw new Exception("参数值发生错误");
//        }else {
//            logger.error("[ Check Name ] : Pass ");
//        }
    }

    @AfterReturning(returning = "ret",pointcut = "checkName()")
    public void doAfter(Object ret){
        logger.info("Order(7) [ AfterReturning ] : "+ret);
    }
}
