package com.example.demo.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.bean.MyBean;
import com.example.demo.config.MyConfig;

public class BeanFactoryPostProcessorExample 
{
	public static void main (String[] args) 
	{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        
//        MyBean bean = context.getBean(MyBean.class);
        MyBean bean = (MyBean) context.getBean("myBeanName");
        
        bean.doSomething();
        
    }
}
