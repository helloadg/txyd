package com.txyd.database.create.xml;

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
import com.txyd.database.bean.TableBean;
import com.txyd.database.create.VelocityCreateBase;
import com.txyd.database.sql.KeyWords;
import com.txyd.database.utils.StringUtil;

public class VelocityCreateMybatisXml extends VelocityCreateBase {
	
	public static boolean  create(List<DatabaseBean> listDb,JavaConfigBean jcb,Properties properties){
		long startTime=System.currentTimeMillis();
		if(listDb==null||listDb.size()==0||jcb==null||properties==null){
			return false;
		}
		Map<String, String> fileMap=new HashMap<String, String>();
		for(TableBean tb:listDb.get(0).getListTable()){
			VelocityEngine velocityEngine = new VelocityEngine();  
	        velocityEngine.init(properties); 
	        
	        StringWriter writer = new StringWriter();  
	        VelocityContext context = new VelocityContext();
	        context.put("jcb", jcb);
	        context.put("StringUtil", new StringUtil());
	        context.put("tableBean", tb);
	        context.put("KeyWords", new KeyWords());
	        Template template = velocityEngine.getTemplate("vm/mapperType/mybatisMappeXml.vm");  
	        template.merge(context, writer);
//	        System.out.println(writer.toString());
	        
			String baseMapper=(jcb.getXmlBaseMapper()==null||jcb.getXmlBaseMapper().trim().equals(""))?"BaseMapper":jcb.getXmlBaseMapper().trim();
			String fileName=tb.getJavabeanName()+baseMapper;
	        String fileContent=VelocityCreateBase.removeSpaceLine(writer.toString());
	        System.out.println("创建："+fileName+".xml");
	        fileMap.put(fileName, fileContent);
		}
		
		long endTime=System.currentTimeMillis();
		//创建javabean的mybatisXml文件
		boolean createFile=VelocityCreateBase.createFile(jcb, fileMap, VelocityCreateBase.FileType.mybatisXml);
		System.out.println("创建mybatisXml耗时："+(endTime-startTime)+"ms");
		return createFile;
		
	}
	

	

}
