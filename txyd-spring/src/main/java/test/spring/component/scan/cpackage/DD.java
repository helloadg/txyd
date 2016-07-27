package test.spring.component.scan.cpackage;

import org.springframework.stereotype.Component;

@Component
public class DD {
	public DD() {
		System.out.println("creating bean DD: " + this);
	}
}
