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

public class FreemarkerCreateJavaBaseServiceImpl extends FreemarkerCreateBase {
	
	public static boolean  create(List<DatabaseBean> listDb,JavaConfigBean jcb,Configuration configuration) throws Exception{
		long startTime=System.currentTimeMillis();
		if(listDb==null||listDb.size()==0||jcb==null||configuration==null){
			return false;
		}

		String baseServiceImplClassName="BaseServiceImpl";
		{
        	if(jcb.getBasePackageService()!=null && !jcb.getBasePackageService().trim().equals("") ){
        		String basePackageService=jcb.getBasePackageService().trim().toLowerCase();
        		baseServiceImplClassName=basePackageService.substring(basePackageService.lastIndexOf(".")+1);
        		baseServiceImplClassName="Base"+StringUtil.getClassPrefixFromPackage(baseServiceImplClassName)+"Impl";
        	}
		}
		
		Map<String, Object> context = new HashMap<String, Object>();
        context.put("StringUtil", new StringUtil());
        context.put("jcb", jcb);
        context.put("baseServiceImplClassName", baseServiceImplClassName);
        Template template = configuration.getTemplate("ftl/mapperType/javaServiceBaseImpl.ftl");	        
        StringWriter writer = new StringWriter();
        template.process(context, writer);


		Map<String, String> fileMap=new HashMap<String, String>();
		
        String fileName=baseServiceImplClassName;
        String fileContent=writer.toString();
        System.out.println("创建："+fileName+".java");
        fileMap.put(fileName, fileContent);
        
		long endTime=System.currentTimeMillis();
		//创建javabean的baseServiceImpl文件
		boolean createFile=FreemarkerCreateBase.createFile(jcb, fileMap, FreemarkerCreateBase.FileType.baseServiceImpl);
		System.out.println("创建baseServiceImpl耗时："+(endTime-startTime)+"ms");
		return createFile;	
		
		
		
	}
	

	

}
