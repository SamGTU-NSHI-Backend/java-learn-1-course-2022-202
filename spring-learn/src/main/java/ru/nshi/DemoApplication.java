package ru.nshi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.nshi.service.TestPrototypeService;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {

        ApplicationContext context =
            SpringApplication.run(DemoApplication.class, args);

        TestPrototypeService beanByType = context.getBean(TestPrototypeService.class);

        TestPrototypeService beanByName = context.getBean("testPrototypeService", TestPrototypeService.class);

        System.out.println(beanByType.getPrototypeValue());

        System.out.println(beanByType == beanByName);

//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName + " = " + context.getBean(beanDefinitionName)
//                .getClass()
//                .getSimpleName());
//        }
    }
}
