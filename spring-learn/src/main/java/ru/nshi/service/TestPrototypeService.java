package ru.nshi.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class TestPrototypeService implements BeanNameAware {

    private String prototypeValue;
    private MessageService messageService;
    private String beanName;

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct");
    }

    public TestPrototypeService() {
        System.out.println("Constructor called");
        messageService = null;
    }

    public TestPrototypeService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String getPrototypeValue() {
        return prototypeValue;
    }

    public void setPrototypeValue(String prototypeValue) {
        this.prototypeValue = prototypeValue;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(name + " is bean's name");
        beanName = name;
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Destroying bean " + beanName);
    }
}
