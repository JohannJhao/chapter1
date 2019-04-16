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
// @Order(i)注解来标识切面的优先级。i的值越小，优先级越高
// @Before 中，i 值越小，则越先执行； @After 中，i 值越小，则越靠后执行
@Order(5)
@Component
public class WebLogAspect {

    private Logger logger = Logger.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.antsix.web..*.*(..))")
    public void webLog(){}

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * @Before在切入点开始处切入内容
     * @After在切入点结尾处切入内容
     * @AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     * @Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
     * @AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
     *
     * @param joinPoint
     */

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //接到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        startTime.set(System.currentTimeMillis());

        //记录内容
        logger.info("[ URL ] : "+request.getRequestURL().toString());
        logger.info("[ HTTP_METHOD ] : "+request.getMethod());
        logger.info("[ IP ] : "+request.getRemoteAddr());
        logger.info("[ ROMOTE_USER ] : "+request.getRemoteUser());
        logger.info("[ CLASS_METHOD ] : "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        logger.info("[ ARGS ] : "+ Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturn(Object ret){
        //处理完请求，返回内容
        logger.info("[ Response ] : "+ret);
        logger.info("[ Spend time ] : "+(System.currentTimeMillis()-startTime.get()));
    }

}
