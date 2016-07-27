package test.spring.component.autowired;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
/**
 * @Service用于标注业务层组件
 * @Controller用于标注控制层组件（如struts中的action）
 * @Repository用于标注数据访问组件，即DAO组件
 * @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
 * 		getBean的默认名称是类名（头字母小写），
 * 		如果想自定义，可以@Service(“aaaaa”)这样来指定。
 * 		这种bean默认是“singleton”的，如果想改变，可以使用@Scope(“prototype”)来改变。
 * 		当接口存在两个实现类的时候必须使用@Qualifier指定注入哪个实现类，否则可以省略，只写@Autowired。
 * 
 * 
 * @author Administrator
 *
 */
@Component
public class A {
	private B b;
	private C c;
	private static final String packageName;
	static{
		packageName=A.class.getPackage().getName();
	}
	public A() {
		System.out.println("creating bean "+packageName+".A: " + this);
	}

	/**
	 * @Autowired 默认按类型装配，默认情况下必须要求依赖对象必须存在，如果要允许null值，可以设置它的required属性为false，
	 * 		例如：@Autowired(required=false) ，如果我们想使用名称装配可以结合@Qualifier注解进行使用，如下：
	 * 		@Autowired() 
	 * 		@Qualifier("baseDao")
	 * 		private BaseDao baseDao;
	 * @Resource，默认安装名称进行装配，名称可以通过name属性进行指定，如果没有指定name属性，当注解写在字段上时，默认取字段名进行安装名称查找，如果注解写在setter方法上默认取属性名进行装配。
	 * 		当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
	 * 		@Resource(name="baseDao")
	 * 		private BaseDao baseDao;
	 */
	
	{
		
//		@Autowired(required=true)
//		@Qualifier("b")
	}
	{
//		@Resource(name="b")
	}
	{
//		@Resource
	}
	@Resource(name="bb")
	public void setB(B b) {
		System.out.println("setting "+packageName+"A.b with " + b);
		this.b = b;
	}


	@Autowired(required=false)
	@Qualifier("c")
	public void setC(C c) {
		System.out.println("setting "+packageName+"A.c with " + c);
		this.c = c;
	}


}
