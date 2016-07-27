package test.java8.interface1;

/**
 * Created by Administrator on 2016/6/22.
 */
public class TestInterfaceIA implements IA {
    @Override
    public void test() {
        IA.super.test();
    }

    public static void main(String[] args){
        TestInterfaceIA testInterface = new TestInterfaceIA();
        testInterface.test();

    }
}
