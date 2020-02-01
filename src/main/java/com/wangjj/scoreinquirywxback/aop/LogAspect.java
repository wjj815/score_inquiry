package com.wangjj.scoreinquirywxback.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @ClassName : LogAspect
 * @Author : 1090086767
 * @Date : 2019/8/29 20:53
 * @Description : 日志切面
 */
@Slf4j
@Aspect//开启切面
@Component//组件注解
public class LogAspect {

    /**
     * 定义切面:拦截web下的所有类中的所有方法
     */
    @Pointcut("execution(* com.wangjj.scoreinquirywxback.controller.*.*(..))")
    public void controllerLog() {}
    @Pointcut("execution(* com.wangjj.scoreinquirywxback.service.*.*(..))")
    public void serviceLog() {}

    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //获得调用的方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        //封装信息
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        log.info("Request: {}", requestLog);
        log.info("------------doBefore------------");
    }

    @After("controllerLog()")
    public void doAfter() {
        log.info("------------doAfter------------");
    }

    @AfterReturning(returning = "result",pointcut = "controllerLog()")
    public void doAfterReturn(Object result) {
        log.info("------------doAfterReturn------------");
        log.info("doAfterReturn:result={}",result);
    }


    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
