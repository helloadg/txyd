package test.spring.component.scan;

public class A {
	private B b;
	private C c;

	public A() {
		System.out.println("creating bean A: " + this);
	}


	public void setB(B b) {
		System.out.println("setting A.b with " + b);
		this.b = b;
	}


	public void setC(C c) {
		System.out.println("setting A.c with " + c);
		this.c = c;
	}


}
