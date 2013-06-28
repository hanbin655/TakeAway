package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import bean.DemoBean;

public class Test {
    public static void main(String args[]){
    	AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();
//        ctx.register(BeanConfig.class);
//        ctx.refresh();
//        DemoBean bean = ctx.getBean(DemoBean.class);
//        System.out.println(bean.getValue());
//        System.out.println(ctx.getBean("helloWorld"));
    }
}