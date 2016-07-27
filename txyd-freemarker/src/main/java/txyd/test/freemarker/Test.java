package txyd.test.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Test {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		configuration.setTemplateLoader(new ClassTemplateLoader(Test.class, "/"));
		try {
			List<User> list=new ArrayList<>();
			for(int i=0;i<3;i++){
				User user=new User();
				user.setId(i);
				user.setName("姓名"+i);
				list.add(user);				
			}
			Template template = configuration.getTemplate("test.ftl");
			StringWriter writer = new StringWriter();
			
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("list", list);
			context.put("message", "FreeMarker");
			context.put("now", new Date());

			template.process(context, writer);
			System.out.println(writer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
