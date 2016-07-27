package test.spring.component.scan1.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {
	@Autowired
	private B b;
	@Autowired
	private C c;
	
	private static final String packageName;
	static{
		packageName=A.class.getPackage().getName();
	}

	public A() {
		System.out.println("creating bean "+packageName+"A: " + this);
	}


	@Autowired
	public void setB(B b) {
		System.out.println("setting "+packageName+"A.b with " + b);
		this.b = b;
	}


	@Autowired
	public void setC(C c) {
		System.out.println("setting "+packageName+"A.c with " + c);
		this.c = c;
	}


}
