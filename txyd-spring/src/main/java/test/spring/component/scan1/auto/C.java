package test.spring.component.scan1.auto;

import org.springframework.stereotype.Component;

@Component
public class C {
	public C() {
		System.out.println("creating bean C: " + this);
	}
}
