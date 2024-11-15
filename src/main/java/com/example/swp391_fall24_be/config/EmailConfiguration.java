package com.example.swp391_fall24_be.config;

import freemarker.cache.FileTemplateLoader;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.File;
import java.io.IOException;


@SpringBootConfiguration
public class EmailConfiguration {

//    public freemarker.template.Configuration getConfiguration() throws IOException {
//        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templateBasePath));
//        freemarker.template.Configuration config = new freemarker.template.Configuration();
//        config.setTemplateLoader(fileTemplateLoader);
//        return config;
//    }

    @Bean
    @Primary
    public FreeMarkerConfigurationFactoryBean factoryBean(){
        FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
        factoryBean.setTemplateLoaderPath("classpath:/mail-templates/");
        factoryBean.setDefaultEncoding("UTF-8");

        return factoryBean;
    }
}
