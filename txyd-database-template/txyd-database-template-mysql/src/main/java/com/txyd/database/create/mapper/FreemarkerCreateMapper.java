package com.txyd.database.create.mapper;

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

public class FreemarkerCreateMapper extends FreemarkerCreateBase {
	
	
	public static boolean create(List<DatabaseBean> listDb, JavaConfigBean jcb, Configuration configuration) throws Exception {
		long startTime = System.currentTimeMillis();
		if (listDb == null || listDb.size() == 0 || jcb == null || configuration == null) {
			return false;
		}
		String BaseMapper = "BaseMapper";
		String BaseNoKeyMapper = "BaseNoKeyMapper";
		{
			String Base = "Base";
			{
				String basePackageBaseMapper = jcb.getBasePackageBaseMapper().trim().toLowerCase();
				if (basePackageBaseMapper != null && !basePackageBaseMapper.trim().equals("")) {
					basePackageBaseMapper = basePackageBaseMapper.substring(basePackageBaseMapper.lastIndexOf(".") + 1);
					Base = StringUtil.toUpperCaseOfFirstChar(basePackageBaseMapper);
				}
			}
			
			String Mapper = "Mapper";
			{
				String baseMapperPackage = jcb.getBasePackageMapper().trim().toLowerCase();
				if (baseMapperPackage != null && !baseMapperPackage.trim().equals("")) {
					baseMapperPackage = baseMapperPackage.substring(baseMapperPackage.lastIndexOf(".") + 1);
					Mapper = StringUtil.toUpperCaseOfFirstChar(baseMapperPackage);
				}
			}
			
			BaseMapper = Base + Mapper;
			BaseNoKeyMapper = Base + "NoKey" + Mapper;
		}
		
		Map<String, String> fileMap = new HashMap<String, String>();
		for (TableBean tb : listDb.get(0).getListTable()) {
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
			context.put("ModelUtil", new ModelUtil());
			context.put("BaseMapper", BaseMapper);
			context.put("BaseNoKeyMapper", BaseNoKeyMapper);
			context.put("javabeanModelClassNameKey", javabeanModelClassNameKey);
			context.put("importModelClassNameKey", importModelClassNameKey);
			context.put("hasPrimaryKey", tb.getPrimaryKeyNum() != null && tb.getPrimaryKeyNum() > 0 ? true : false);
			
			context.put("columnBeanList", tb.getListColumn());
			
			Template template = configuration.getTemplate("ftl/mapperType/javaMapper.ftl");
			StringWriter writer = new StringWriter();
			template.process(context, writer);

//	        System.out.println(writer.toString());
			
			String fileName = tb.getJavabeanMapperClassName();
			String fileContent = writer.toString();
			
			System.out.println("创建：" + fileName + ".java");
			
			fileMap.put(fileName, fileContent);
		}
		
		long endTime = System.currentTimeMillis();
		//创建javabean的model文件
		boolean createFile = FreemarkerCreateBase.createFile(jcb, fileMap, FreemarkerCreateBase.FileType.mapper);
		System.out.println("创建mapper耗时：" + (endTime - startTime) + "ms");
		return createFile;
		
	}
	
	
}
