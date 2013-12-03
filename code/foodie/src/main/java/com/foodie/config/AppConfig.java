package com.foodie.config;

import com.foodie.repository.PMF;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jdo.JdoTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.ArrayList;
import java.util.List;

@ComponentScan(basePackages = "com.foodie")
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
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

    @Bean
    public PersistenceManager persistenceManager() {
        return PMF.get().getPersistenceManager();
    }

    @Bean
    public PersistenceManagerFactory getPersistenceManagerFactory() {
        return PMF.get();
    }

    @Bean
    public JdoTransactionManager getJdoTransactionManager() {
        JdoTransactionManager jdoTransactionManager = new JdoTransactionManager();
        jdoTransactionManager.setPersistenceManagerFactory(getPersistenceManagerFactory());
        return jdoTransactionManager;
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
