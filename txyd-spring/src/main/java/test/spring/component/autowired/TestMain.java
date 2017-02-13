package test.spring.component.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TestMain {
	private static ApplicationContext ctx= new ClassPathXmlApplicationContext("spring-component-scan-schema.xml");

	@Autowired
	private A a;
    public static void main(String[] args) {
        System.out.println();
    }
}
