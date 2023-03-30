package ru.nshi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.nshi.service.MessageService;
import ru.nshi.service.TestPrototypeService;

@Configuration
public class SampleConfiguration {

    //    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TestPrototypeService testPrototypeService(
        MessageService messageService,
        @Value("${test.prototype-val:def value}") String value) {
        TestPrototypeService svc = new TestPrototypeService(messageService);
        svc.setPrototypeValue(value);
        return svc;
    }

}
