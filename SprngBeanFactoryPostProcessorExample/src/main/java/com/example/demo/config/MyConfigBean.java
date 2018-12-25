package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import com.example.demo.bean.MyBean;

/* 빈 정의를 수정하려는 클래스는 스프링의 BeanFactoryPostProcessor 인터페이스를 구현하면 된다.
 * BeanFactoryPostProcessor는 스프링 컨테이너가 빈 정의를 로드한 후, 빈 인스턴스를 생성하기 전에 수행된다
 * 
 * BeanFactoryPostProcessor는 애플리케이션 컨텍스트 xml 파일에 정의된 다른 빈보다 먼저 생성되므로,
 * 다른 빈을 수정할 수 있는 기회를 얻을 수 있다.
 * */
public class MyConfigBean implements BeanFactoryPostProcessor 
{
	/* ConfigurableListableBeanFactory의 getBean() 메소드를 호출해서 빈 인스턴스 생성이 가능하지만
	 * postProcessBeanFactory에서 Bean을 생성하는 것은 바람직하지 않다.
	 * 
	 * 또한 postProcessBeanFactory 메서드 안에서 생성된 빈 인스턴스에 대해서는 BeanPostProcessor가 실행 되지 않는다.
	 * */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException 
	{
		// TODO Auto-generated method stub
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		
		genericBeanDefinition.setBeanClass(MyBean.class);
		genericBeanDefinition.getPropertyValues().add("strProp", "my string property");
		
	    ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("myBeanName", genericBeanDefinition);
	}
}
