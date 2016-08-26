package com.txyd.database.create.xml;

import com.txyd.database.bean.ColumnBean;
import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.bean.TableBean;
import com.txyd.database.create.FreemarkerCreateBase;
import com.txyd.database.sql.KeyWords;
import com.txyd.database.utils.StringUtil;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.BeanUtils;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreemarkerCreateBaseMybatisXml extends FreemarkerCreateBase {
	
	public static boolean  create(List<DatabaseBean> listDb,JavaConfigBean jcb,Configuration configuration) throws Exception{
		long startTime=System.currentTimeMillis();
		if(listDb==null||listDb.size()==0||jcb==null||configuration==null){
			return false;
		}
		Map<String, String> fileMap=new HashMap<String, String>();
		for(TableBean tb:listDb.get(0).getListTable()){
			TableBean tbWithoutExtra=new TableBean();
			BeanUtils.copyProperties(tb, tbWithoutExtra);
			if(tb.getListColumn()!=null && tb.getListColumn().size()>0){
				List<ColumnBean> columnBeanList=new ArrayList<>();
				for(ColumnBean cb:tb.getListColumn()){
					if(!(cb.getIsPrimaryKey()&&cb.getExtra()!=null&&!cb.getExtra().trim().isEmpty())){
						columnBeanList.add(cb);
					}					
				}
				tbWithoutExtra.setListColumn(columnBeanList);
			}
			
	        BeansWrapper wrapper = (BeansWrapper) configuration.getObjectWrapper();  
	        
			Map<String, Object> context = new HashMap<String, Object>();
	        context.put("StringUtil", new StringUtil());
	        context.put("jcb", jcb);
	        context.put("tableBean", tb);
	        context.put("tableBeanWithoutExtra", tbWithoutExtra);
	        context.put("KeyWords", new KeyWords());
	        context.put("static", wrapper.getStaticModels());  
//	        context.put("KeyWords", (TemplateHashModel)wrapper.getStaticModels().get("com.txyd.database.sql.KeyWords"));
	        Template template = configuration.getTemplate("ftl/mapperType/mybatisBaseMappeXml.ftl");	        
	        StringWriter writer = new StringWriter();
	        template.process(context, writer);
	        
//	        System.out.println(writer.toString());
	        
			String baseMapper=(jcb.getXmlBaseMapper()==null||jcb.getXmlBaseMapper().trim().equals(""))?"BaseMapper":jcb.getXmlBaseMapper().trim();
			String fileName=tb.getJavabeanName()+baseMapper;
	        String fileContent=writer.toString();
	        System.out.println("创建："+fileName+".xml");
	        fileMap.put(fileName, fileContent);
		}
		
		long endTime=System.currentTimeMillis();
		//创建javabean的mybatisXml文件
		boolean createFile=FreemarkerCreateBase.createFile(jcb, fileMap, FreemarkerCreateBase.FileType.baseMybatisXml);
		System.out.println("创建mybatisXml耗时："+(endTime-startTime)+"ms");
		return createFile;
		
	}
	

	

}
