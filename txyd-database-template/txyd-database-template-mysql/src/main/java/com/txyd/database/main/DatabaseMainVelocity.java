package com.txyd.database.main;


import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.txyd.database.bean.ColumnBean;
import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.bean.SqlDataType;
import com.txyd.database.bean.TableBean;
import com.txyd.database.create.javabean.VelocityCreateJavabean;
import com.txyd.database.create.javabean.VelocityCreateJavabeanBase;
import com.txyd.database.create.javabean.VelocityCreateJavabeanKey;
import com.txyd.database.create.service.VelocityCreateJavaBaseService;
import com.txyd.database.create.service.VelocityCreateJavaBaseServiceImpl;
import com.txyd.database.create.service.VelocityCreateJavaService;
import com.txyd.database.create.service.VelocityCreateJavaServiceImpl;
import com.txyd.database.create.xml.VelocityCreateMybatisConfigXml;
import com.txyd.database.create.xml.VelocityCreateMybatisXml;
import com.txyd.database.inter.DatabaseType;
import com.txyd.database.service.ColumnService;
import com.txyd.database.service.DatabaseService;
import com.txyd.database.service.TableService;
import com.txyd.database.utils.StringUtil;

import txyd.util.DateTime;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-main.xml"})
public class DatabaseMainVelocity {
	@Autowired(required=true)
	private DatabaseService  databaseService;
	@Autowired(required=true)
	private TableService tableService;
	@Autowired(required=true)
	private ColumnService columnService;

	@Autowired(required=true)
	private JavaConfigBean javaConfigBean ;
	

	/**
	 * 
	 * @Description 获得数据库的信息->表的信息->列的信息
	 * @author     
	 * @param jcb
	 * @return
	 */
	private List<DatabaseBean> getFromDatabase(JavaConfigBean jcb)
	{
		long startTime=System.currentTimeMillis();
		System.out.println("读取数据开始:"+DateTime.toString(null, null));
		
		DatabaseBean database=new DatabaseBean();
		database.setSchemaName(jcb.getDatabaseSchema());//schema
		List<DatabaseBean> listDb=databaseService.select(new DatabaseBean(){{
			setSchemaName(jcb.getDatabaseSchema());
		}});
		if(listDb==null||listDb.size()==0){
			System.out.println("未查找到此"+jcb.getDatabaseSchema());
			return null;
		}
		for(DatabaseBean db:listDb){
			List<TableBean> listTb=this.tableService.select(new TableBean(){{
					setTableSchema(db.getSchemaName());
				}});
			if(listTb!=null){
				db.setListTable(listTb);
				for(TableBean tb:listTb){
					List<ColumnBean> listCb=this.columnService.select(new ColumnBean(){{
							setTableSchema(tb.getTableSchema());
							setTableName(tb.getTableName());
						}});
					tb.setListColumn(listCb);
				}				
			}
		}
		long endTime=System.currentTimeMillis();
		System.out.println("读取数据结束:"+DateTime.toString(null, null));
		System.out.println("读取数据用时:"+(endTime-startTime));		
		return listDb;
	}

	/**
	 * 
	 * @Description 设置数据库的信息->表的信息->列的信息的javabean以及annotatiion
	 * @author     
	 * @param listDb
	 * @param jcb
	 * @return
	 */
	private static List<DatabaseBean> setDatabase(List<DatabaseBean> listDb ,JavaConfigBean jcb)
	{
		long startTime=System.currentTimeMillis();
		System.out.println("格式化数据开始:"+DateTime.toString(null, null));
		
		if(listDb==null||listDb.size()==0){
			System.out.println("数据库相关的信息为空:schema:"+jcb.getDatabaseSchema());
			return null;
		}
		for(DatabaseBean db:listDb)
		{
			if(db.getListTable()==null||db.getListTable().size()==0){
				System.out.println("数据库的没有表：schema:"+jcb.getDatabaseSchema());
				return null;
			}
			
			for(TableBean table:db.getListTable())
			{
				table.setDatabaseType(DatabaseType.valueOf(jcb.getDatabaseType()));
				table.setTableOwner(jcb.getDatabaseOwner());
				//表名所对应的javabean名
				String tableName=table.getTableName();				
				{
					//根据配置，去掉表名的前缀				
					Set<String> tablePrefixesSet=jcb.getTablePrefixes();
					if(tablePrefixesSet!=null&&tablePrefixesSet.size()>0){
						for(String prefixe:tablePrefixesSet)
						{
							if(tableName.toLowerCase().startsWith(prefixe.toLowerCase()))
							{
								tableName=tableName.substring(prefixe.length());
								break;//前缀只去除一次
							}
						}
					}
				}
				
				
				String javabeanName=StringUtil.getJavabeanClassName(tableName);
				table.setJavabeanName(javabeanName);
				//表名所对应的javabean的param类名；
				table.setJavabeanParamClassName(javabeanName+StringUtil.getClassPrefixFromPackage(jcb.getBasePackageParam().toLowerCase()));
				//表名所对应的javabean的model类名；
				table.setJavabeanModelClassName(javabeanName+StringUtil.getClassPrefixFromPackage(jcb.getBasePackageModel().toLowerCase()));
				table.setJavabeanKeyClassName(javabeanName+"Key");
				table.setJavabeanBaseModelClassName("Base"+StringUtil.getClassPrefixFromPackage(jcb.getBasePackageModel().toLowerCase()));
				//表名所对应的javabean的service类名；
				table.setJavabeanServiceClassName(javabeanName+StringUtil.getClassPrefixFromPackage(jcb.getBasePackageService().toLowerCase()));
				//表名所对应的javabean的service的实现类名；
				table.setJavabeanServiceImplClassName(table.getJavabeanServiceClassName()+StringUtil.getClassPrefixFromPackage(jcb.getBasePackageServiceImpl().toLowerCase()));
				//表名所对应的javabean的mapper类名；
				table.setJavabeanMapperClassName(javabeanName+StringUtil.getClassPrefixFromPackage(jcb.getBasePackageMapper().toLowerCase()));
				//表名所对应的javabean的dao类名；
				table.setJavabeanDaoClassName(javabeanName+StringUtil.getClassPrefixFromPackage(jcb.getBasePackageDao().toLowerCase()));
				//表名所对应的javabean的dao的实现类名；
				table.setJavabeanDaoImplClassName(table.getJavabeanDaoClassName()+StringUtil.getClassPrefixFromPackage(jcb.getBasePackageDaoImpl().toLowerCase()));
//				//表所对应的annotation
//				table.setJavabeanAnnotation(AnnotationUtil.getAnnotation(table,jcb));
				if(table.getListColumn()!=null&&table.getListColumn().size()>0)
				{
					for(ColumnBean column:table.getListColumn())
					{
						column.setDatabaseType(DatabaseType.valueOf(jcb.getDatabaseType()));
						column.setTableOwner(jcb.getDatabaseOwner());
						
						//列名所对应的javabean的field名//(先小写)
						column.setJavabeanFieldName(StringUtil.getJavabeanFieldName(column.getColumnName()));
						//列名所对应的javabean的field数据类型比如"java.lang.String"
						String javabeanFieldDataType=SqlDataType.getJavaType(column.getDataType());
						column.setJavabeanFieldDataType(javabeanFieldDataType);
						String javabeanFieldDataTypeSimple=javabeanFieldDataType;
						if(javabeanFieldDataType.contains(".")){
							javabeanFieldDataTypeSimple=javabeanFieldDataType.substring(javabeanFieldDataType.lastIndexOf(".")+1);
						}
						column.setJavabeanFieldDataTypeSimple(javabeanFieldDataTypeSimple);
					}
				}
			}
			
		}

		long endTime=System.currentTimeMillis();
		System.out.println("格式化数据结束:"+DateTime.toString(null, null));
		System.out.println("格式化数据用时:"+(endTime-startTime));
		
		return listDb;
	}
	@Test
	public void test() throws Exception{
		List<DatabaseBean> listDb=this.getFromDatabase(this.javaConfigBean);
		listDb=DatabaseMainVelocity.setDatabase(listDb,this.javaConfigBean);
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream("/prop/velocity.properties"));
		{
			long startTime=System.currentTimeMillis();
			VelocityCreateJavabean.create(listDb, this.javaConfigBean, properties);
			VelocityCreateJavabeanKey.create(listDb,  this.javaConfigBean, properties);
			VelocityCreateJavabeanBase.create(listDb,  this.javaConfigBean, properties);
			VelocityCreateJavaBaseService.create(listDb,  this.javaConfigBean, properties);
			VelocityCreateJavaService.create(listDb,  this.javaConfigBean, properties);
			VelocityCreateJavaBaseServiceImpl.create(listDb,  this.javaConfigBean, properties);
			VelocityCreateJavaServiceImpl.create(listDb,  this.javaConfigBean, properties);
			VelocityCreateMybatisConfigXml.create(listDb,  this.javaConfigBean, properties);
			VelocityCreateMybatisXml.create(listDb,  this.javaConfigBean, properties);
			long endTime=System.currentTimeMillis();
			System.out.println("总耗时："+(endTime-startTime)+"ms");
		}

	

		{
//			InputStream is=this.getClass().getResourceAsStream("/vm/mapperType/mybatisMappeXml.vm");
//			InputStreamReader isr=new InputStreamReader(is, "UTF-8");
//			BufferedReader br=new BufferedReader(isr);
//			String line;
//			while((line=br.readLine()) != null){
//				String lineTrim=line.trim();
//				if(lineTrim.startsWith("#set")
//						||lineTrim.startsWith("#if")
//						||lineTrim.startsWith("#elseif")
//						||lineTrim.startsWith("#end")
//						||lineTrim.startsWith("#foreach")){
//					line=lineTrim;
//					
//				}
//				System.out.println(line);
//			}
			
		}
		
		
	}
}
