package com.txyd.database.create.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.bean.TableBean;
import com.txyd.database.create.FreemarkerCreateBase;
import com.txyd.database.utils.ModelUtil;
import com.txyd.database.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerCreateJavaService extends FreemarkerCreateBase {
	
	public static boolean  create(List<DatabaseBean> listDb,JavaConfigBean jcb,Configuration configuration) throws Exception{
		long startTime=System.currentTimeMillis();
		if(listDb==null||listDb.size()==0||jcb==null||configuration==null){
			return false;
		}
		Map<String, String> fileMap=new HashMap<String, String>();
		for(TableBean tb:listDb.get(0).getListTable()){
			Map<String, Object> context = new HashMap<String, Object>();
	        context.put("StringUtil", new StringUtil());
	        context.put("jcb", jcb);
	        context.put("tableBean", tb);
	        context.put("ModelUtil", new ModelUtil());  
	        
	        Template template = configuration.getTemplate("ftl/mapperType/javaService.ftl");	        
	        StringWriter writer = new StringWriter();
	        template.process(context, writer);

//	        System.out.println(writer.toString());
	        
	        String fileName=tb.getJavabeanServiceClassName();
	        String fileContent=writer.toString();
	        System.out.println("创建："+fileName+".java");
	        fileMap.put(fileName, fileContent);
		}

        
		long endTime=System.currentTimeMillis();
		//创建javabean的service文件
		boolean createFile=FreemarkerCreateBase.createFile(jcb, fileMap, FreemarkerCreateBase.FileType.service);
		System.out.println("创建service耗时："+(endTime-startTime)+"ms");
		return createFile;	
		
	}
	

	

}
