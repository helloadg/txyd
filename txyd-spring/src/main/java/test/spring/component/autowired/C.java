package test.spring.component.autowired;

import org.springframework.stereotype.Component;

@Component
public class C {
	private static final String packageName;
	static{
		packageName=C.class.getPackage().getName();
	}
	public C() {
		System.out.println("creating bean "+packageName+".C: " + this);
	}
}
