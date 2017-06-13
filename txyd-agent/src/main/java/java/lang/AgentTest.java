package java.lang;
/**
 * 出错：java.lang.SecurityException: Prohibited package name: java.lang
 * 包名不能以java开头
 */

/**
 * Created by Administrator on 2017/6/13.
 */
public class AgentTest {
	public static void main(String[] args) {
		Class<Void> TYPE = (Class<Void>) Class.getPrimitiveClass("void");
		System.out.println(Class.getPrimitiveClass("int"));
	}
}
