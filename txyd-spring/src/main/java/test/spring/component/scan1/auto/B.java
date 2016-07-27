package test.spring.component.scan1.auto;

import org.springframework.stereotype.Component;

@Component
public class B {
	public B() {
		System.out.println("creating bean  B: " + this);
	}
}
