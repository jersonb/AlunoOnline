package com.alunoonline.api.configs;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SwaggerConfiguration implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(final ApplicationPreparedEvent event) {
        var environment = event.getApplicationContext().getEnvironment();
        var props = new Properties();
        props.put("springdoc.swagger-ui.path", "/");
        environment.getPropertySources().addFirst(new PropertiesPropertySource("programmatically", props));
    }

}
