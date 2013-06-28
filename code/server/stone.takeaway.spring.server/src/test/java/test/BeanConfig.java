package test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bean.DemoBean;

@Configuration
public class BeanConfig {
		public BeanConfig() {
			
		}
	     @Bean
	     public DemoBean myBean() {
	    	 return new DemoBean();
	     }
	     @Bean(name="helloWorld")
	     public String hi() {
	    	 return "hello jim, this is spring framework3.2";
	     }
}
