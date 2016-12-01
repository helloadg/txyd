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

public class FreemarkerCreateJavaBaseServiceImpl extends FreemarkerCreateBase {
	
	public static boolean create(List<DatabaseBean> listDb, JavaConfigBean jcb, Configuration configuration) throws Exception {
		long startTime = System.currentTimeMillis();
		if (listDb == null || listDb.size() == 0 || jcb == null || configuration == null) {
			return false;
		}
		String BaseService = "BaseService";
		String BaseNoKeyService = "BaseNoKeyService";
		String BaseServiceImpl = "BaseServiceImpl";
		String BaseNoKeyServiceImpl = "BaseNoKeyServiceImpl";
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
			String Impl = "Impl";
			{
				if (jcb.getBasePackageServiceImpl() != null && !jcb.getBasePackageServiceImpl().trim().equals("")) {
					String basePackageServiceImpl = jcb.getBasePackageServiceImpl().trim().toLowerCase();
					basePackageServiceImpl = basePackageServiceImpl.substring(basePackageServiceImpl.lastIndexOf(".") + 1);
					Impl = StringUtil.getClassPrefixFromPackage(basePackageServiceImpl);
				}
			}
			BaseService = Base + Service;
			BaseNoKeyService = Base + "NoKey"  + Service;
			BaseNoKeyServiceImpl = Base + "NoKey" + Service + Impl;
			BaseServiceImpl = Base + Service + Impl;
		}
		String BaseMapper = "BaseMapper";
		String BaseNoKeyMapper = "BaseNoKeyMapper";
		String baseMapper = "baseMapper";
		String baseNoKeyMapper = "baseNoKeyMapper";
		{
			String Base = "Base";
			String base = "base";
			{
				String basePackageBaseMapper = jcb.getBasePackageBaseMapper().trim().toLowerCase();
				if (basePackageBaseMapper != null && !basePackageBaseMapper.trim().equals("")) {
					basePackageBaseMapper = basePackageBaseMapper.substring(basePackageBaseMapper.lastIndexOf(".") + 1);
					base = basePackageBaseMapper;
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
			baseMapper = base + Mapper;
			baseNoKeyMapper = base + "NoKey" + Mapper;
			
		}
		
		Map<String, Object> context = new HashMap<>();
		context.put("StringUtil", new StringUtil());
		context.put("jcb", jcb);
		context.put("BaseService", BaseService);
		context.put("BaseNoKeyService", BaseNoKeyService);
		context.put("BaseServiceImpl", BaseServiceImpl);
		context.put("BaseNoKeyServiceImpl", BaseNoKeyServiceImpl);
		context.put("BaseMapper", BaseMapper);
		context.put("BaseNoKeyMapper", BaseNoKeyMapper);
		context.put("baseMapper", baseMapper);
		context.put("baseNoKeyMapper", baseNoKeyMapper);
		Template template = configuration.getTemplate("ftl/mapperType/javaServiceBaseImpl.ftl");
		StringWriter writer = new StringWriter();
		template.process(context, writer);
		
		
		Map<String, String> fileMap = new HashMap<String, String>();
		
		String fileName = BaseServiceImpl;
		String fileContent = writer.toString();
		System.out.println("创建：" + fileName + ".java");
		fileMap.put(fileName, fileContent);
		
		long endTime = System.currentTimeMillis();
		//创建javabean的baseServiceImpl文件
		boolean createFile = FreemarkerCreateBase.createFile(jcb, fileMap, FreemarkerCreateBase.FileType.baseServiceImpl);
		System.out.println("创建{fileName}耗时：{time}ms".replace("{fileName}", fileName).replace("{time}", (endTime - startTime) + ""));
		return createFile;
		
		
	}
	
	
}
