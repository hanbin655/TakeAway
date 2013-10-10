package com.foodie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.ArrayList;
import java.util.List;

@ComponentScan(basePackages = "com.foodie")
@Configuration
// @ImportResource(value = { "WEB-INF/dispatcher-servlet.xml" })
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver irvr = new InternalResourceViewResolver();
        irvr.setPrefix("/WEB-INF/views/");
        irvr.setSuffix(".jsp");
        irvr.setViewClass(JstlView.class);
        return irvr;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.addAll(convertersmessageConverters());
    }

    private List<HttpMessageConverter<?>> convertersmessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter convertor = new MappingJackson2HttpMessageConverter();
        converters.add(convertor);
        return converters;
    }
    // @Override
    // public void addArgumentResolvers(List<HandlerMethodArgumentResolver>
    // argumentResolvers) {
    // argumentResolvers.add(new
    // RequestResponseBodyMethodProcessor(convertersmessageConverters()));
    // }
    // @Bean
    // public CharacterEncodingFilter getCharacterEncodingFilter() {
    // CharacterEncodingFilter cef = new CharacterEncodingFilter();
    // cef.setEncoding("UTF-8");
    // return cef;
    // }

}
