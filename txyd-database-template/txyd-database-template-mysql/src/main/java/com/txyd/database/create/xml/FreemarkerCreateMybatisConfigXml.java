package com.txyd.database.create.xml;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.create.FreemarkerCreateBase;
import com.txyd.database.create.VelocityCreateBase;
import com.txyd.database.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerCreateMybatisConfigXml extends FreemarkerCreateBase {
	
	public static boolean  create(List<DatabaseBean> listDb,JavaConfigBean jcb,Configuration configuration) throws Exception{
		long startTime=System.currentTimeMillis();
		if(listDb==null||listDb.size()==0||jcb==null||configuration==null){
			return false;
		}
		
		Map<String, Object> context = new HashMap<String, Object>();
        context.put("StringUtil", new StringUtil());
        context.put("jcb", jcb);
        context.put("tableList", listDb.get(0).getListTable());
        
        Template template = configuration.getTemplate("ftl/mapperType/mybatisConfig.ftl");	        
        StringWriter writer = new StringWriter();
        template.process(context, writer);
        
//        System.out.println(writer.toString())       

		Map<String, String> fileMap=new HashMap<String, String>();
		
        String fileName="mybatis_config";
        String fileContent=writer.toString();
        System.out.println("创建："+fileName+".xml");
        fileMap.put(fileName, fileContent);
        
		long endTime=System.currentTimeMillis();
		//创建javabean的mybatisConfigXml文件
		boolean createFile=FreemarkerCreateBase.createFile(jcb, fileMap, FreemarkerCreateBase.FileType.mybatisConfigXml);
		System.out.println("创建mybatisConfigXml耗时："+(endTime-startTime)+"ms");
		return createFile;
		
		
		
	}
	

	

}
