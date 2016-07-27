package test.java8.interface1;

/**
 * Created by Administrator on 2016/6/22.
 */
public class TestInterfaceIAIB implements IA,IB {
    @Override
    public void test() {
        IB.super.test();
//        IA.super.test();
    }

    public static void main(String[] args){
        TestInterfaceIAIB testInterface = new TestInterfaceIAIB();
        testInterface.test();

    }
}
