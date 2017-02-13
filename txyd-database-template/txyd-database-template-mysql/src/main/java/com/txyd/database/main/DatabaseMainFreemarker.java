package com.txyd.database.main;


import com.txyd.database.bean.ColumnBean;
import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.bean.SqlDataType;
import com.txyd.database.bean.TableBean;
import com.txyd.database.create.javabean.FreemarkerCreateJavabean;
import com.txyd.database.create.javabean.FreemarkerCreateJavabeanBase;
import com.txyd.database.create.javabean.FreemarkerCreateJavabeanKey;
import com.txyd.database.create.mapper.FreemarkerCreateBaseMapper;
import com.txyd.database.create.mapper.FreemarkerCreateBaseNoKeyMapper;
import com.txyd.database.create.mapper.FreemarkerCreateMapper;
import com.txyd.database.create.service.FreemarkerCreateJavaBaseNoKeyService;
import com.txyd.database.create.service.FreemarkerCreateJavaBaseNoKeyServiceImpl;
import com.txyd.database.create.service.FreemarkerCreateJavaBaseService;
import com.txyd.database.create.service.FreemarkerCreateJavaBaseServiceBaseImpl;
import com.txyd.database.create.service.FreemarkerCreateJavaBaseServiceImpl;
import com.txyd.database.create.service.FreemarkerCreateJavaService;
import com.txyd.database.create.service.FreemarkerCreateJavaServiceImpl;
import com.txyd.database.create.xml.FreemarkerCreateBaseMybatisXml;
import com.txyd.database.create.xml.FreemarkerCreateMybatisConfigXml;
import com.txyd.database.create.xml.FreemarkerCreateMybatisXml;
import com.txyd.database.inter.DatabaseType;
import com.txyd.database.service.ColumnService;
import com.txyd.database.service.DatabaseService;
import com.txyd.database.service.TableService;
import com.txyd.database.utils.StringUtil;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import txyd.util.DateTime;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-main.xml"})
public class DatabaseMainFreemarker {
	@Autowired
	private DatabaseService databaseService;
	@Autowired
	private TableService tableService;
	@Autowired
	private ColumnService columnService;
	
	@Autowired
	private JavaConfigBean javaConfigBean;
	
	
	/**
	 * @param jcb
	 * @return
	 * @Description 获得数据库的信息->表的信息->列的信息
	 * @author
	 */
	private List<DatabaseBean> getFromDatabase(JavaConfigBean jcb) {
		long startTime = System.currentTimeMillis();
		System.out.println("读取数据开始:" + DateTime.toString(null, null));
		
		DatabaseBean database = new DatabaseBean();
		database.setSchemaName(jcb.getDatabaseSchema());//schema
		List<DatabaseBean> listDb = databaseService.select(new DatabaseBean() {{
			setSchemaName(jcb.getDatabaseSchema());
		}});
		if (listDb == null || listDb.size() == 0) {
			System.out.println("未查找到数据库{databaseName}".replace("{databaseName}", jcb.getDatabaseSchema()));
			return null;
		}
		listDb = listDb.stream().map(db -> {
			List<TableBean> listTb = this.tableService.select(new TableBean() {{
				setTableSchema(db.getSchemaName());
			}});
			if (listTb != null) {
				db.setListTable(listTb);
				{
					listTb = listTb.stream().map(tb -> {
						List<ColumnBean> listCb = this.columnService.select(new ColumnBean() {{
							setTableSchema(tb.getTableSchema());
							setTableName(tb.getTableName());
						}});
						tb.setListColumn(listCb);
						return tb;
					}).collect(Collectors.toList());
				}
				{
//					listTb.forEach(tb -> {
//						List<ColumnBean> listCb=this.columnService.select(new ColumnBean(){{
//							setTableSchema(tb.getTableSchema());
//							setTableName(tb.getTableName());
//						}});
//						tb.setListColumn(listCb);
//					});
				}
			}
			return db;
		}).collect(Collectors.toList());
		
		long endTime = System.currentTimeMillis();
		System.out.println("读取数据结束:" + DateTime.toString(null, null));
		System.out.println("读取数据用时:" + (endTime - startTime));
		return listDb;
	}
	
	/**
	 * @param listDb
	 * @param jcb
	 * @return
	 * @Description 设置数据库的信息->表的信息->列的信息的javabean以及annotatiion
	 * @author
	 */
	private static List<DatabaseBean> setDatabase(List<DatabaseBean> listDb, JavaConfigBean jcb) {
		long startTime = System.currentTimeMillis();
		System.out.println("格式化数据开始:" + DateTime.toString(null, null));
		
		if (listDb == null || listDb.size() == 0) {
			System.out.println("数据库相关的信息为空:schema:" + jcb.getDatabaseSchema());
			return null;
		}
		listDb.stream().filter(db -> db.getListTable() == null || db.getListTable().isEmpty()).forEach(db -> {
			System.out.println("数据库{databaseName}没有表".replace("{databaseName}", jcb.getDatabaseSchema()));
		});
		listDb.stream().filter(db -> db.getListTable() != null && !db.getListTable().isEmpty()).forEach(db -> {
			
			db.getListTable().forEach(table -> {
				table.setDatabaseType(DatabaseType.valueOf(jcb.getDatabaseType()));
				table.setTableOwner(jcb.getDatabaseOwner());
				//表名所对应的javabean名
				String tableName = table.getTableName();
				{
					//根据配置，去掉表名的前缀
					Set<String> tablePrefixesSet = jcb.getTablePrefixes();
					if (tablePrefixesSet != null && tablePrefixesSet.size() > 0) {
						for (String prefixe : tablePrefixesSet) {
							if (tableName.toLowerCase().startsWith(prefixe.toLowerCase())) {
								tableName = tableName.substring(prefixe.length());
								break;//前缀只去除一次
							}
						}
					}
				}
				
				
				String javabeanName = StringUtil.getJavabeanClassName(tableName);
				table.setJavabeanName(javabeanName);
				//表名所对应的javabean的param类名；
				table.setJavabeanParamClassName(javabeanName + StringUtil.getClassPrefixFromPackage(jcb.getBasePackageParam().toLowerCase()));
				//表名所对应的javabean的model类名；
				table.setJavabeanModelClassName(javabeanName + StringUtil.getClassPrefixFromPackage(jcb.getBasePackageModel().toLowerCase()));
				table.setJavabeanKeyClassName(javabeanName + "Key");
				table.setJavabeanBaseModelClassName("Base" + StringUtil.getClassPrefixFromPackage(jcb.getBasePackageModel().toLowerCase()));
				//表名所对应的javabean的service类名；
				table.setJavabeanServiceClassName(javabeanName + StringUtil.getClassPrefixFromPackage(jcb.getBasePackageService().toLowerCase()));
				//表名所对应的javabean的service的实现类名；
				table.setJavabeanServiceImplClassName(table.getJavabeanServiceClassName() + StringUtil.getClassPrefixFromPackage(jcb.getBasePackageServiceImpl().toLowerCase()));
				//表名所对应的javabean的mapper类名；
				table.setJavabeanMapperClassName(javabeanName + StringUtil.getClassPrefixFromPackage(jcb.getBasePackageMapper().toLowerCase()));
				
				//表名所对应的javabean的dao类名；
				table.setJavabeanDaoClassName(javabeanName + StringUtil.getClassPrefixFromPackage(jcb.getBasePackageDao().toLowerCase()));
				//表名所对应的javabean的dao的实现类名；
				table.setJavabeanDaoImplClassName(table.getJavabeanDaoClassName() + StringUtil.getClassPrefixFromPackage(jcb.getBasePackageDaoImpl().toLowerCase()));
//				//表所对应的annotation
//				table.setJavabeanAnnotation(AnnotationUtil.getAnnotation(table,jcb));
				if (table.getListColumn() == null || table.getListColumn().size() == 0) {
					System.out.println("数据库{databaseName}的表{tableName}没有列属性"
							.replace("{databaseName}", db.getDatabaseName())
							.replace("{tableName}", table.getTableName()));
				} else {
					table.getListColumn().forEach(column -> {
						column.setDatabaseType(DatabaseType.valueOf(jcb.getDatabaseType()));
						column.setTableOwner(jcb.getDatabaseOwner());
						
						//列名所对应的javabean的field名//(先小写)
						column.setJavabeanFieldName(StringUtil.getJavabeanFieldName(column.getColumnName()));
						//列名所对应的javabean的field数据类型比如"java.lang.String"
						String javabeanFieldDataType = SqlDataType.getJavaType(column.getDataType());
						column.setJavabeanFieldDataType(javabeanFieldDataType);
						String javabeanFieldDataTypeSimple = javabeanFieldDataType;
						if (javabeanFieldDataType.contains(".")) {
							javabeanFieldDataTypeSimple = javabeanFieldDataType.substring(javabeanFieldDataType.lastIndexOf(".") + 1);
						}
						column.setJavabeanFieldDataTypeSimple(javabeanFieldDataTypeSimple);
						column.setJavabeanFieldDataTypeIsNum(StringUtil.isNumber(javabeanFieldDataType));
						column.setJavabeanFieldDataTypeIsOfLang(StringUtil.isOfLang(javabeanFieldDataType));
						column.setJavabeanFieldDataTypeIsPrimitive(StringUtil.isPrimitive(javabeanFieldDataType));
					});
				}
			});
			
		});
		
		long endTime = System.currentTimeMillis();
		System.out.println("格式化数据结束:" + DateTime.toString(null, null));
		System.out.println("格式化数据用时:" + (endTime - startTime));
		
		return listDb;
	}
	
	@Test
	public void test() throws Exception {
		List<DatabaseBean> listDb = this.getFromDatabase(this.javaConfigBean);
		listDb = DatabaseMainFreemarker.setDatabase(listDb, this.javaConfigBean);
		
		Configuration properties = new Configuration();
		properties.setObjectWrapper(new DefaultObjectWrapper());
		properties.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/"));
		{
			
			long startTime = System.currentTimeMillis();
			FreemarkerCreateJavabean.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateJavabeanBase.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateJavabeanKey.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateBaseNoKeyMapper.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateBaseMapper.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateMapper.create(listDb, this.javaConfigBean, properties);
			
			FreemarkerCreateJavaBaseNoKeyService.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateJavaBaseService.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateJavaService.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateJavaBaseServiceImpl.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateJavaBaseNoKeyServiceImpl.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateJavaServiceImpl.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateJavaBaseServiceBaseImpl.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateMybatisConfigXml.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateBaseMybatisXml.create(listDb, this.javaConfigBean, properties);
			FreemarkerCreateMybatisXml.create(listDb, this.javaConfigBean, properties);
			
			long endTime = System.currentTimeMillis();
			System.out.println("总耗时：" + (endTime - startTime) + "ms");
		}
	}
}
