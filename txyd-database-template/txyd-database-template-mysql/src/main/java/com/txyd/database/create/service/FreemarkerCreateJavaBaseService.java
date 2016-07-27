package com.txyd.database.create.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.create.FreemarkerCreateBase;
import com.txyd.database.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerCreateJavaBaseService extends FreemarkerCreateBase {
	
	public static boolean  create(List<DatabaseBean> listDb,JavaConfigBean jcb,Configuration configuration) throws Exception{
		long startTime=System.currentTimeMillis();
		if(listDb==null||listDb.size()==0||jcb==null||configuration==null){
			return false;
		}

		String baseServiceClassName="BaseService";
		{
        	if(jcb.getBasePackageService()!=null && !jcb.getBasePackageService().trim().equals("") ){
        		String basePackageService=jcb.getBasePackageService().trim().toLowerCase();
        		baseServiceClassName=basePackageService.substring(basePackageService.lastIndexOf(".")+1);
        		baseServiceClassName="Base"+StringUtil.getClassPrefixFromPackage(baseServiceClassName);
        	}
		}
		
		Map<String, Object> context = new HashMap<String, Object>();
        context.put("StringUtil", new StringUtil());
        context.put("jcb", jcb);
        context.put("baseServiceClassName", baseServiceClassName);
        
        Template template = configuration.getTemplate("ftl/mapperType/javaServiceBase.ftl");	        
        StringWriter writer = new StringWriter();
        template.process(context, writer);
        
//      System.out.println(writer.toString());

		Map<String, String> fileMap=new HashMap<String, String>();
		
        String fileName=baseServiceClassName;
        String fileContent=writer.toString();
        System.out.println("创建："+fileName+".java");
        fileMap.put(fileName, fileContent);

		long endTime=System.currentTimeMillis();
		//创建javabean的baseService文件
		boolean createFile=FreemarkerCreateBase.createFile(jcb, fileMap, FreemarkerCreateBase.FileType.baseService);
		System.out.println("创建baseService耗时："+(endTime-startTime)+"ms");
		return createFile;		
		
	}
	

	

}
