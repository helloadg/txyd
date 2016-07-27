package com.txyd.database.create.javabean;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.create.VelocityCreateBase;
import com.txyd.database.create.VelocityCreateBase.FileType;
import com.txyd.database.utils.StringUtil;

public class VelocityCreateJavabeanBase extends VelocityCreateBase {
	
	public static boolean  create(List<DatabaseBean> listDb,JavaConfigBean jcb,Properties properties){
		long startTime=System.currentTimeMillis();
		if(listDb==null||listDb.size()==0||jcb==null||properties==null){
			return false;
		}
//		String baseModelName="Base"+StringUtil.getClassPrefixFromPackage(jcb.getBasePackageModel().toLowerCase());
		String baseModelName=listDb.get(0).getListTable().get(0).getJavabeanBaseModelClassName();
		
		VelocityEngine velocityEngine = new VelocityEngine();  
        velocityEngine.init(properties); 
        
        StringWriter writer = new StringWriter();  
        VelocityContext context = new VelocityContext();
        context.put("jcb", jcb);
        context.put("StringUtil", new StringUtil());
        context.put("baseModelName", baseModelName);
        Template template = velocityEngine.getTemplate("vm/mapperType/javaBeanBase.vm");  
        template.merge(context, writer);
//        System.out.println(writer.toString());

		Map<String, String> fileMap=new HashMap<String, String>();
        String fileName=baseModelName;
        String fileContent=VelocityCreateBase.removeSpaceLine(writer.toString());
        System.out.println("创建："+fileName+".java");
        fileMap.put(fileName, fileContent);

		long endTime=System.currentTimeMillis();
		//创建javabean的model文件
		boolean createFile=VelocityCreateBase.createFile(jcb, fileMap, VelocityCreateBase.FileType.baseModel);
		System.out.println("创建javabeanBase耗时："+(endTime-startTime)+"ms");
		return createFile;		
		
		
	}
	

	

}
