package test.spring.component.scan;

import org.springframework.beans.factory.annotation.Autowired;

public class A2 {
	private B b;

	private C c;

	public A2() {
		System.out.println("creating bean A2: " + this);
	}


	@Autowired
	public void setB(B b) {
		System.out.println("setting A.b with " + b);
		this.b = b;
	}

	@Autowired
	public void setC(C c) {
		System.out.println("setting A.c with " + c);
		this.c = c;
	}


}
