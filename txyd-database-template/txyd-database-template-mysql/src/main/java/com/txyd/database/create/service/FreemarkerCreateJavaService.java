package com.txyd.database.create.service;

import com.txyd.database.bean.ColumnBean;
import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.bean.TableBean;
import com.txyd.database.create.FreemarkerCreateBase;
import com.txyd.database.utils.ModelUtil;
import com.txyd.database.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreemarkerCreateJavaService extends FreemarkerCreateBase {
	
	public static boolean create(List<DatabaseBean> listDb, JavaConfigBean jcb, Configuration configuration) throws Exception {
		long startTime = System.currentTimeMillis();
		if (listDb == null || listDb.size() == 0 || jcb == null || configuration == null) {
			return false;
		}
		Map<String, String> fileMap = new HashMap<String, String>();
		for (TableBean tb : listDb.get(0).getListTable()) {
			
			String BaseService = "BaseService";
			String BaseNoKeyService = "BaseNoKeyService";
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
				BaseService = Base + Service;
				BaseNoKeyService = Base + "NoKey" + Service;
				
			}
			String javabeanModelClassNameKey = "";
			String importModelClassNameKey = "import {package}.{className};";
			{
				if (tb.getPrimaryKeyNum() > 1) {
					String modelKey = "Key";
					if (jcb.getModelKey() != null && jcb.getModelKey().length() > 0) {
						modelKey = StringUtil.getJavabeanClassName(jcb.getModelKey().trim());
					}
					javabeanModelClassNameKey = tb.getJavabeanModelClassName() + modelKey;
					importModelClassNameKey = importModelClassNameKey
							.replace("{package}", jcb.getBasePackageModel())
							.replace("{className}", javabeanModelClassNameKey);
				} else if (tb.getPrimaryKeyNum() == 1) {
					for (ColumnBean columnBean : tb.getListColumn()) {
						if (columnBean.getIsPrimaryKey() != null && columnBean.getIsPrimaryKey().equals(true)) {
							javabeanModelClassNameKey = ModelUtil.getColumnJavaDataType(columnBean, jcb);
							if (javabeanModelClassNameKey.startsWith("java.lang.")) {
								importModelClassNameKey = "";
								javabeanModelClassNameKey = javabeanModelClassNameKey.replace("java.lang.", "");
							} else {
								importModelClassNameKey = "import "+javabeanModelClassNameKey+";";
								javabeanModelClassNameKey = javabeanModelClassNameKey.substring(javabeanModelClassNameKey.lastIndexOf(".") + 1);
							}
						}
					}
				}
			}
			
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("StringUtil", new StringUtil());
			context.put("jcb", jcb);
			context.put("tableBean", tb);
			context.put("BaseService", BaseService);
			context.put("BaseNoKeyService", BaseNoKeyService);
			context.put("ModelUtil", new ModelUtil());
			context.put("javabeanModelClassNameKey", javabeanModelClassNameKey);
			context.put("importModelClassNameKey", importModelClassNameKey);
			context.put("hasPrimaryKey", tb.getPrimaryKeyNum() != null && tb.getPrimaryKeyNum() > 0 ? true : false);
			
			Template template = configuration.getTemplate("ftl/mapperType/javaService.ftl");
			StringWriter writer = new StringWriter();
			template.process(context, writer);

//	        System.out.println(writer.toString());
			
			String fileName = tb.getJavabeanServiceClassName();
			String fileContent = writer.toString();
			System.out.println("创建：" + fileName + ".java");
			fileMap.put(fileName, fileContent);
		}
		
		
		long endTime = System.currentTimeMillis();
		//创建javabean的service文件
		boolean createFile = FreemarkerCreateBase.createFile(jcb, fileMap, FreemarkerCreateBase.FileType.service);
		System.out.println("创建service耗时：{time}ms".replace("{time}", (endTime - startTime) + ""));
		return createFile;
		
	}
	
	
}
