package com.txyd.database.create.service;

import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.create.FreemarkerCreateBase;
import com.txyd.database.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreemarkerCreateJavaBaseServiceBaseImpl extends FreemarkerCreateBase {
	
	public static boolean create(List<DatabaseBean> listDb, JavaConfigBean jcb, Configuration configuration) throws Exception {
		long startTime = System.currentTimeMillis();
		if (listDb == null || listDb.size() == 0 || jcb == null || configuration == null) {
			return false;
		}
		String BaseImpl = "BaseImpl";
		{
			String Base = "Base";
			{
				if (jcb.getBasePackageBaseService() != null && !jcb.getBasePackageBaseService().trim().equals("")) {
					String basePackageServiceBase = jcb.getBasePackageBaseService().trim().toLowerCase();
					basePackageServiceBase = basePackageServiceBase.substring(basePackageServiceBase.lastIndexOf(".") + 1);
					Base = StringUtil.getClassPrefixFromPackage(basePackageServiceBase);
				}
			}
			
			String Impl = "Impl";
			{
				if (jcb.getBasePackageServiceImpl() != null && !jcb.getBasePackageServiceImpl().trim().equals("")) {
					String basePackageServiceImpl = jcb.getBasePackageServiceImpl().trim().toLowerCase();
					basePackageServiceImpl = basePackageServiceImpl.substring(basePackageServiceImpl.lastIndexOf(".") + 1);
					Impl = StringUtil.getClassPrefixFromPackage(basePackageServiceImpl);
				}
			}
			BaseImpl = Base + Impl;
		}
		
		
		Map<String, Object> context = new HashMap<>();
		context.put("StringUtil", new StringUtil());
		context.put("jcb", jcb);
		context.put("BaseImpl", BaseImpl);
		
		Template template = configuration.getTemplate("ftl/mapperType/javaServiceBaseImplBase.ftl");
		StringWriter writer = new StringWriter();
		template.process(context, writer);
		
		
		Map<String, String> fileMap = new HashMap<String, String>();
		
		String fileName = BaseImpl;
		String fileContent = writer.toString();
		System.out.println("创建：" + fileName + ".java");
		fileMap.put(fileName, fileContent);
		
		long endTime = System.currentTimeMillis();
		//创建javabean的baseServiceImpl文件
		boolean createFile = FreemarkerCreateBase.createFile(jcb, fileMap, FileType.baseServiceBaseImpl);
		System.out.println("创建{fileName}耗时：{time}ms".replace("{fileName}", fileName).replace("{time}", (endTime - startTime) + ""));
		return createFile;
		
		
	}
	
	
}
