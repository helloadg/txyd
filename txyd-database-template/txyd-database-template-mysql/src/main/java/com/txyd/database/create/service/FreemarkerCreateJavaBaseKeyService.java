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

public class FreemarkerCreateJavaBaseKeyService extends FreemarkerCreateBase {
	
	public static boolean create(List<DatabaseBean> listDb, JavaConfigBean jcb, Configuration configuration) throws Exception {
		long startTime = System.currentTimeMillis();
		if (listDb == null || listDb.size() == 0 || jcb == null || configuration == null) {
			return false;
		}
		
		String BaseKeyService = "BaseKeyService";
		{
			String Base = "Base";
			{
				if (jcb.getBasePackageBaseService() != null && !jcb.getBasePackageBaseService().trim().equals("")) {
					String basePackageServiceBase = jcb.getBasePackageBaseService().trim().toLowerCase();
					basePackageServiceBase = basePackageServiceBase.substring(basePackageServiceBase.lastIndexOf(".") + 1);
					Base = StringUtil.getClassPrefixFromPackage(basePackageServiceBase);
				}
			}
			String Service = "Service";
			{
				if (jcb.getBasePackageService() != null && !jcb.getBasePackageService().trim().equals("")) {
					String basePackageService = jcb.getBasePackageService().trim().toLowerCase();
					basePackageService = basePackageService.substring(basePackageService.lastIndexOf(".") + 1);
					Service = StringUtil.getClassPrefixFromPackage(basePackageService);
				}
			}
			BaseKeyService = Base + "Key" + Service;
			
			
		}
		
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("StringUtil", new StringUtil());
		context.put("jcb", jcb);
		context.put("BaseKeyService", BaseKeyService);
		
		Template template = configuration.getTemplate("ftl/mapperType/javaServiceBaseKey.ftl");
		StringWriter writer = new StringWriter();
		template.process(context, writer);

//      System.out.println(writer.toString());
		
		Map<String, String> fileMap = new HashMap<String, String>();
		
		String fileName = BaseKeyService;
		String fileContent = writer.toString();
		System.out.println("创建：" + fileName + ".java");
		fileMap.put(fileName, fileContent);
		
		long endTime = System.currentTimeMillis();
		//创建javabean的baseService文件
		boolean createFile = FreemarkerCreateBase.createFile(jcb, fileMap, FileType.baseService);
		System.out.println("创建{fileName}耗时：{time}ms".replace("{fileName}", fileName).replace("{time}", (endTime - startTime) + ""));
		return createFile;
		
	}
	
	
}
