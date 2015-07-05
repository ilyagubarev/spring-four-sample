package com.ilyagubarev.samples.springfour.storage.server.context;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspectBean {

    @Pointcut("within(com.ilyagubarev.samples.springfour.storage.server.context.controllers..*)")
    public void inControllers() {

    }

    @Pointcut("execution(public * *(..))")
    public void apiMethod() {

    }

    @After("apiMethod() && inControllers()")
    public void afterRestApiExecution() {
        System.out.format("api method executed in REST controllers");
    }
}
