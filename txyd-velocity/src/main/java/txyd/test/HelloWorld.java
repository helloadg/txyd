package txyd.test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class HelloWorld {
	public static void main(String[] args) throws Exception {  
		Properties p = new Properties(){
			private static final long serialVersionUID = 1L;
			{
				load(HelloWorld.class.getResourceAsStream("/velocity/velocity.properties"));
			}			
		};
        VelocityEngine ve = new VelocityEngine();  
        ve.init(p);  
        
        VelocityContext context = new VelocityContext();
        {
            {
            	Date date=new Date();
            	context.put("date", date);
            }
            {
            	User user=new User();
            	user.setId(999);
            	user.setName("姓名：999");
            	context.put("user", user);
            }
        	{
        		List<String> list=new ArrayList<>();
            	for(int i=0;i<5;i++){
            		list.add("name"+i);
            	}
            	context.put("list", list);
        	}
        	{
        		List<User> list=new ArrayList<>();
            	for(int i=0;i<5;i++){
            		final int id=i;
            		list.add(new User(){{
            			setId(id);
            			setName("姓名:"+id);
            		}});
            	}
            	context.put("listBean", list);
        	}
        	{
        		Map<String, Object> map=new HashMap<>();
            	for(int i=0;i<5;i++){
            		map.put("name:"+i, "value:"+i);
            	}
            	context.put("hashMap", map);
        	}
        }
        

        StringWriter writer = new StringWriter();  
        Template template = ve.getTemplate("vm/index.vm");  
        template.merge(context, writer);
        
        System.out.println(writer.toString());  
        
        
      
    }
}
