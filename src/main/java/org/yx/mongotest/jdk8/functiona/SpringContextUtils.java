package org.yx.mongotest.jdk8.functiona;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring context
 *
 * Created by Lynn·Rowe on 2016/6/14.
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

	private static ApplicationContext context;



	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}



	public static ApplicationContext getContext() {
		return context;
	}



	public static <T> T getBean(Class<T> c) {
		return context.getBean(c);
	}



	public static <T> T getBean(String name, Class<T> type) {
		return context.getBean(name, type);
	}
}
