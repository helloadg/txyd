package com.txyd.database.create.javabean;

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
import com.txyd.database.utils.StringUtil;

public class VelocityCreateJavabean extends VelocityCreateBase {
	
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
	        context.put("StringUtil", new StringUtil());
	        context.put("jcb", jcb);
	        context.put("tableBean", tb);
	        Set<String> importSet=new HashSet<>();
	        for(ColumnBean cb: tb.getListColumn()){
	        	if(!cb.getJavabeanFieldDataType().contains("java.lang.")
	        			&& !(cb.getIsPrimaryKey()&&tb.getPrimaryKeyNum()>1)){
	        		importSet.add(cb.getJavabeanFieldDataType());
	        	}
	        	
	        }
	        context.put("importSet", importSet);
	        context.put("columnBeanList", tb.getListColumn());
	        Template template = velocityEngine.getTemplate("vm/mapperType/javaBean.vm");  
	        template.merge(context, writer);
//	        System.out.println(writer.toString());
	        String fileName=tb.getJavabeanModelClassName();
	        String fileContent=VelocityCreateBase.removeSpaceLine(writer.toString());
	        System.out.println("创建："+fileName+".java");
	        fileMap.put(fileName, fileContent);
		}

		long endTime=System.currentTimeMillis();
		//创建javabean的model文件
		boolean createFile=VelocityCreateBase.createFile(jcb, fileMap, VelocityCreateBase.FileType.model);
		System.out.println("创建javabean耗时："+(endTime-startTime)+"ms");
		return createFile;
		
	}
	

	

}
