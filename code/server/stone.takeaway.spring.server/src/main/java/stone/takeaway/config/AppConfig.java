package stone.takeaway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@ComponentScan(basePackages = "stone.takeaway")
@Configuration
@EnableWebMvc
// @ImportResource(value = { "WEB-INF/dispatcher-servlet.xml" })
public class AppConfig {

    @Bean
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter re = new RequestMappingHandlerAdapter();
        re.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        return re;
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver irvr = new InternalResourceViewResolver();
        irvr.setPrefix("/WEB-INF/views/");
        irvr.setSuffix(".jsp");
        irvr.setViewClass(JstlView.class);
        return irvr;
    }


}
