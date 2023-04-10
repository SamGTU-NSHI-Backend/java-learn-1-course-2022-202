package ru.nshi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.nshi.controller.AuthorController;
import ru.nshi.repository.AuthorRepository;
import ru.nshi.service.TestPrototypeService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {
    @Autowired
    AuthorRepository repository;

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

    @PostConstruct
    void post() {
        for (int i = 1; i < 5; i++) {
            repository.create(new AuthorController.CreateAuthor("John " + i));
        }
    }
}
