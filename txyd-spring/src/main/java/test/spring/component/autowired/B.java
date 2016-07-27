package test.spring.component.autowired;

import org.springframework.stereotype.Component;

@Component("bb")
public class B {
	private static final String packageName;
	static{
		packageName=B.class.getPackage().getName();
	}
	public B() {
		System.out.println("creating bean "+packageName+".B: " + this);
	}
}
