package com.ilyagubarev.samples.springfour.common.server.kernel.context;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public abstract class ComponentBean implements BeanNameAware,
        DisposableBean, InitializingBean {

    private String name;

    public String getBeanName() {
        return name;
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
