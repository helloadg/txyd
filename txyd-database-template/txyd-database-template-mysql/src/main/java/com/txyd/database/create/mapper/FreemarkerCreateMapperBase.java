package com.txyd.database.create.mapper;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.create.FreemarkerCreateBase;
import com.txyd.database.create.VelocityCreateBase;
import com.txyd.database.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerCreateMapperBase extends FreemarkerCreateBase {
	
	public static boolean  create(List<DatabaseBean> listDb,JavaConfigBean jcb,Configuration configuration) throws Exception {
		long startTime=System.currentTimeMillis();
		if(listDb==null||listDb.size()==0||jcb==null||configuration==null){
			return false;
		}
		String baseMapper="BaseMapper";
		String baseMapperPackage=jcb.getBasePackageMapper().trim().toLowerCase();
		if(baseMapperPackage!=null&&!baseMapperPackage.trim().equals("")){
			baseMapperPackage=baseMapperPackage.substring(baseMapperPackage.lastIndexOf(".")+1);
			baseMapperPackage=StringUtil.toUpperCaseOfFirstChar(baseMapperPackage);	
			baseMapper="Base"+baseMapperPackage;
		}
		
		
		Map<String, Object> context = new HashMap<String, Object>(); 
		context.put("jcb", jcb);
	    context.put("StringUtil", new StringUtil());
	    
	    
	    Template template = configuration.getTemplate("ftl/mapperType/javaMapperBase.ftl");	        
        StringWriter writer = new StringWriter();
        template.process(context, writer);
        
//        System.out.println(writer.toString());
        

		Map<String, String> fileMap=new HashMap<String, String>();
        String fileName=baseMapper;
        String fileContent=writer.toString();
        System.out.println("创建："+fileName+".java");
        fileMap.put(fileName, fileContent);
        

		long endTime=System.currentTimeMillis();
		//创建javabean的model文件
		boolean createFile=FreemarkerCreateBase.createFile(jcb, fileMap, FreemarkerCreateBase.FileType.baseMapper);
		System.out.println("创建baseMapper耗时："+(endTime-startTime)+"ms");
		return createFile;		
		
		
	}
	

	

}
