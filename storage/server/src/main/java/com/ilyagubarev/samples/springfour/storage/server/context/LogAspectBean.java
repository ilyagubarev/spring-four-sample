package com.ilyagubarev.samples.springfour.storage.server.context;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspectBean {

    // execution()

    @Pointcut("within(com.ilyagubarev.samples.springfour.storage.server.context.controllers..*)")
    public void inControllers() {

    }

    @Pointcut("execution(public * *(..))")
    public void apiMethod() {

    }

    @After("apiMethod() && inControllers()")
    public void afterBeanInitialization() {
        System.out.format("Bean was created!!!!!!!!!!!!!!!!");
    }
}
