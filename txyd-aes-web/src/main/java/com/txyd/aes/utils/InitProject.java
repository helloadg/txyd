package com.txyd.aes.utils;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * spring容器加载完成后，执行该类的方法
 * @author Administrator
 *
 */
public class InitProject implements ApplicationListener<ContextRefreshedEvent> {

	/**
	 * 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
	 * 但是这个时候，会存在一个问题，在web 项目中（spring mvc），系统会存在两个容器:
	 * 一个是root application context ；
	 * 另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
	 * 这种情况下，就会造成onApplicationEvent方法被执行两次。
	 * 为了避免上面提到的问题，我们可以只在root application context初始化完成后调用逻辑代码，其他的容器的初始化完成，则不做任何处理，修改后代码
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null ){//root application context 没有parent，他就是老大.
			String[] str=event.getApplicationContext().getBeanDefinitionNames();
			System.out.println("启动中………………");
			System.out.println("spring容器管理的类,输出如下：");
			for(String s:str){
				System.out.println(s);
			}
			System.out.println("spring容器管理的类,输出结束");
			// TODO Auto-generated method stub
		}
		
		
	}

}
