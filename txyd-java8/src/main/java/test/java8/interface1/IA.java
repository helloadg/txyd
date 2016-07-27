package test.java8.interface1;

/**
 * Created by Administrator on 2016/6/22.
 */
public interface IA {
    default void  test() {
        System.out.println("IA.test");
    }
}
