package com.txyd.database.create.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.txyd.database.bean.ColumnBean;
import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.bean.TableBean;
import com.txyd.database.create.VelocityCreateBase;
import com.txyd.database.create.VelocityCreateBase.FileType;
import com.txyd.database.utils.ModelUtil;
import com.txyd.database.utils.StringUtil;

public class VelocityCreateJavaServiceImpl extends VelocityCreateBase {
	
	public static boolean  create(List<DatabaseBean> listDb,JavaConfigBean jcb,Properties properties){
		long startTime=System.currentTimeMillis();
		if(listDb==null||listDb.size()==0||jcb==null||properties==null){
			return false;
		}
		Map<String, String> fileMap=new HashMap<String, String>();
		for(TableBean tb:listDb.get(0).getListTable()){
			VelocityEngine velocityEngine = new VelocityEngine();  
	        velocityEngine.init(properties); 
	        
	        StringWriter writer = new StringWriter();  
	        VelocityContext context = new VelocityContext();
	        context.put("jcb", jcb);
	        context.put("StringUtil", new StringUtil());
	        context.put("tableBean", tb);
	        context.put("ModelUtil", new ModelUtil());          
	        Template template = velocityEngine.getTemplate("vm/mapperType/javaServiceImpl.vm");  
	        template.merge(context, writer);
//	        System.out.println(writer.toString());
	        
	        String fileName=tb.getJavabeanServiceClassName();
	        String fileContent=VelocityCreateBase.removeSpaceLine(writer.toString());
	        System.out.println("创建："+fileName+".java");
	        fileMap.put(fileName, fileContent);
		}
		
		long endTime=System.currentTimeMillis();
		//创建javabean的serviceImpl文件
		boolean createFile=VelocityCreateBase.createFile(jcb, fileMap, VelocityCreateBase.FileType.serviceImpl);
		System.out.println("创建serviceImpl耗时："+(endTime-startTime)+"ms");
		return createFile;	
		
	}
	

	

}
