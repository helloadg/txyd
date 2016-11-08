package com.txyd.database.create.mapper;

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

public class FreemarkerCreateBaseMapper extends FreemarkerCreateBase {
	
	public static boolean  create(List<DatabaseBean> listDb,JavaConfigBean jcb,Configuration configuration) throws Exception {
		long startTime=System.currentTimeMillis();
		if(listDb==null||listDb.size()==0||jcb==null||configuration==null){
			return false;
		}
		String BaseMapper="BaseMapper";
		{
			String Base="Base";
			{
				String basePackageBaseMapper=jcb.getBasePackageBaseMapper().trim().toLowerCase();
				if(basePackageBaseMapper!=null&&!basePackageBaseMapper.trim().equals("")){
					basePackageBaseMapper=basePackageBaseMapper.substring(basePackageBaseMapper.lastIndexOf(".")+1);
					Base=StringUtil.toUpperCaseOfFirstChar(basePackageBaseMapper);
				}
			}
			
			String Mapper="Mapper";
			{
				String baseMapperPackage=jcb.getBasePackageMapper().trim().toLowerCase();
				if(baseMapperPackage!=null&&!baseMapperPackage.trim().equals("")){
					baseMapperPackage=baseMapperPackage.substring(baseMapperPackage.lastIndexOf(".")+1);
					Mapper=StringUtil.toUpperCaseOfFirstChar(baseMapperPackage);
				}
			}
			
			BaseMapper=Base+Mapper;
		}
		
		Map<String, Object> context = new HashMap<>();
		context.put("jcb", jcb);
	    context.put("StringUtil", new StringUtil());
		context.put("BaseMapper",BaseMapper);
	    
	    
	    Template template = configuration.getTemplate("ftl/mapperType/javaMapperBase.ftl");
        StringWriter writer = new StringWriter();
        template.process(context, writer);
        
//        System.out.println(writer.toString());
        

		Map<String, String> fileMap=new HashMap<>();
        String fileName=BaseMapper;
        String fileContent=writer.toString();
        System.out.println("创建："+fileName+".java");
        fileMap.put(fileName, fileContent);
        

		long endTime=System.currentTimeMillis();
		//创建javabean的model文件
		boolean createFile=FreemarkerCreateBase.createFile(jcb, fileMap, FreemarkerCreateBase.FileType.baseMapper);
		System.out.println("创建"+BaseMapper+"耗时："+(endTime-startTime)+"ms");
		return createFile;		
		
		
	}
	

	

}
