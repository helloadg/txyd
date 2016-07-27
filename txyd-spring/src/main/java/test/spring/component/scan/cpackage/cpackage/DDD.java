package test.spring.component.scan.cpackage.cpackage;

import org.springframework.stereotype.Component;

@Component
public class DDD {
	public DDD() {
		System.out.println("creating bean DDD: " + this);
	}
}
