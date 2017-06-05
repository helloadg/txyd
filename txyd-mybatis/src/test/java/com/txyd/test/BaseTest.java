package com.txyd.test;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/4/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-main-test.xml"})
public class BaseTest implements ApplicationContextAware {
	
	protected ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@Test
	public void testGetApplicationContext(){
		StringBuilder str = new StringBuilder("");
		Stream.<Integer>iterate(0,e->++e).limit(5).forEach(e->{
			str.append("##########################################\n");
		});
		System.out.println(this.applicationContext);
		Assert.assertNotNull(this.applicationContext);
		System.out.println(str.toString());
		System.out.println(Arrays.asList(this.applicationContext.getBeanDefinitionNames()));
		System.out.println(str.toString());
	}
	
	protected String getLine(){
		StringBuilder sb =new StringBuilder();
		Stream.iterate(0,e->++e).limit(10).forEach(e->{
			sb.append("############################################################\n");
		});
		return sb.toString();
	}
}
