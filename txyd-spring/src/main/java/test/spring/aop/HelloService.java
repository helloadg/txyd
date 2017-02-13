package test.spring.aop;

public class HelloService implements IHello {

	@Override
	public void sayHello() {
		System.out.println("-----Hello World!-----");		
	}
}
