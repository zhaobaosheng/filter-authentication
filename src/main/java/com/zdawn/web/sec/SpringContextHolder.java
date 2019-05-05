package com.zdawn.web.sec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringContextHolder implements ApplicationContextAware{
	private static ApplicationContext context = null;

	public static void setContext(ApplicationContext context) {
		SpringContextHolder.context = context;
	}
	
	public static ApplicationContext getContext() {
		return context;
	}
	public static Object getBean(String beanName){
		return getContext().getBean(beanName);
	}

	public void setApplicationContext(ApplicationContext applicationContext){
		SpringContextHolder.context = applicationContext;
	}
}
