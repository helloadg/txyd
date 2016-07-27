package test.java8.interface1;

/**
 * Created by Administrator on 2016/6/22.
 */
public class TestInterfaceIB implements IB {
    @Override
    public void test() {
        IB.super.test();
//        IA.super.test();
    }

    public static void main(String[] args){
        TestInterfaceIB testInterface = new TestInterfaceIB();
        testInterface.test();

    }
}
