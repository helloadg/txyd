package com.txyd.database.bean;

import java.io.Serializable;
import java.util.LinkedHashSet;
public class JavaConfigBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 编译配置文件
	 */
	private String comment;
	/**
	 * 编译类型：dao：有dao层（默认为此值）；mapper：无dao层；
	 */
	private String generateMybatisType;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 生成的文件所在的路径
	 */
	private String outRoot;
	/**
	 * 文件
	 */
	private String unicode;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * java文件所在的根目录
	 */
	private String javaPathRoot;
	/**
	 *  数据库类型
	 */
	private String databaseType;
	/**
	 * 数据库所属用户 
	 */
	private String databaseOwner;
	/**
	 * 数据库schema
	 * oracle：数据库所属用户
	 * mysql：数据库库名
	 */
	private String databaseSchema;
	/**
	 * 基础包名 
	 */
	private String basePackage;
	/**
	 * 基础包名 ：dao 
	 */
	private String basePackageMapper;
	/**
	 *  基础包名 ：dao
	 */
	private String basePackageDao;
	/**
	 * 基础包名 ：dao实现包
	 */
	private String basePackageDaoImpl;
	/**
	 *  基础包名 ：model
	 */
	private String basePackageModel;
	/**
	 *  基础包名 ：param 
	 */
	private String basePackageParam;
	/**
	 * 如果model所对应的表含联合主键，生成的model所对应的主键类名的后缀
	 */
	private String modelKey;
	/**
	 * 基础包名：service
	 */
	private String basePackageService;
	/**
	 * 基础包名：service实现包
	 */
	private String basePackageServiceImpl;
	/**
	 * xml文件名称后缀 
	 */
	private String xmlMapper;
	/**
	 * 基础包名：xml所在路径
	 */
	private String xmlPath;
	/**
	 * xml基础文件名称后缀
	 */
	private String xmlBaseMapper;
	/**
	 * 基础包名：xml基础文件所在路径
	 */
	private String xmlBasePath;
	/**
	 * 基础包名：xml的配置所在路径
	 */
	private String xmlConfigPath;
	/**
	 *  基础包名：注解所在的路径 
	 */
	private String annotationPath;
	/**
	 * 基础包名：enum所在的路径
	 */
	private String enumPath;
	/**
	 * 需要移除的表名前缀,如果有多个使用“|”进行分隔多个前缀,如果有多个，以第一个的权限最高，
	 *  示例值: t_|t_whorksheet|picc_ ,
	 *  如果表名为t_worksheet_client，
	 *  则去除后为worksheet_client
	 */
	private LinkedHashSet<String> tablePrefixes;
	/**
	 * 表别名
	 */
	private String tableAlias;
	/**
	 *  表别名
	 */
	private SqlDataType sqlDataType;
	
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getGenerateMybatisType() {
		return generateMybatisType;
	}
	public void setGenerateMybatisType(String generateMybatisType) {
		this.generateMybatisType = generateMybatisType;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getOutRoot() {
		return outRoot;
	}
	public void setOutRoot(String outRoot) {
		this.outRoot = outRoot;
	}
	public String getUnicode() {
		return unicode;
	}
	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getJavaPathRoot() {
		return javaPathRoot;
	}
	public void setJavaPathRoot(String javaPathRoot) {
		this.javaPathRoot = javaPathRoot;
	}
	public String getDatabaseType() {
		return databaseType;
	}
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
	public String getDatabaseOwner() {
		return databaseOwner;
	}
	public void setDatabaseOwner(String databaseOwner) {
		this.databaseOwner = databaseOwner;
	}
	public String getDatabaseSchema() {
		return databaseSchema;
	}
	public void setDatabaseSchema(String databaseSchema) {
		this.databaseSchema = databaseSchema;
	}
	public String getBasePackage() {
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	public String getBasePackageMapper() {
		return basePackageMapper;
	}
	public void setBasePackageMapper(String basePackageMapper) {
		this.basePackageMapper = basePackageMapper;
	}
	public String getBasePackageDao() {
		return basePackageDao;
	}
	public void setBasePackageDao(String basePackageDao) {
		this.basePackageDao = basePackageDao;
	}
	public String getBasePackageDaoImpl() {
		return basePackageDaoImpl;
	}
	public void setBasePackageDaoImpl(String basePackageDaoImpl) {
		this.basePackageDaoImpl = basePackageDaoImpl;
	}
	public String getBasePackageModel() {
		return basePackageModel;
	}
	public void setBasePackageModel(String basePackageModel) {
		this.basePackageModel = basePackageModel;
	}
	public String getBasePackageParam() {
		return basePackageParam;
	}
	public void setBasePackageParam(String basePackageParam) {
		this.basePackageParam = basePackageParam;
	}
	public String getModelKey() {
		return modelKey;
	}
	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}
	public String getBasePackageService() {
		return basePackageService;
	}
	public void setBasePackageService(String basePackageService) {
		this.basePackageService = basePackageService;
	}
	public String getBasePackageServiceImpl() {
		return basePackageServiceImpl;
	}
	public void setBasePackageServiceImpl(String basePackageServiceImpl) {
		this.basePackageServiceImpl = basePackageServiceImpl;
	}
	public String getXmlMapper() {
		return xmlMapper;
	}
	public void setXmlMapper(String xmlMapper) {
		this.xmlMapper = xmlMapper;
	}
	public String getXmlPath() {
		return xmlPath;
	}
	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	public String getXmlBaseMapper() {
		return xmlBaseMapper;
	}
	public void setXmlBaseMapper(String xmlBaseMapper) {
		this.xmlBaseMapper = xmlBaseMapper;
	}
	public String getXmlBasePath() {
		return xmlBasePath;
	}
	public void setXmlBasePath(String xmlBasePath) {
		this.xmlBasePath = xmlBasePath;
	}
	public String getXmlConfigPath() {
		return xmlConfigPath;
	}
	public void setXmlConfigPath(String xmlConfigPath) {
		this.xmlConfigPath = xmlConfigPath;
	}
	public String getAnnotationPath() {
		return annotationPath;
	}
	public void setAnnotationPath(String annotationPath) {
		this.annotationPath = annotationPath;
	}
	public String getEnumPath() {
		return enumPath;
	}
	public void setEnumPath(String enumPath) {
		this.enumPath = enumPath;
	}
	public LinkedHashSet<String> getTablePrefixes() {
		return tablePrefixes;
	}
	public void setTablePrefixes(LinkedHashSet<String> tablePrefixes) {
		this.tablePrefixes = tablePrefixes;
	}
	public String getTableAlias() {
		return tableAlias;
	}
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}
	public SqlDataType getSqlDataType() {
		return sqlDataType;
	}
	public void setSqlDataType(SqlDataType sqlDataType) {
		this.sqlDataType = sqlDataType;
	}
	
	


	
	

	
}
