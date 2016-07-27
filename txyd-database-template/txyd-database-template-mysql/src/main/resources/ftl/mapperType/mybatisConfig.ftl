<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">


<!--mybatis配置文件-->
<configuration>
  <settings>
      <setting name="lazyLoadingEnabled" value="false"/>
      <setting name="multipleResultSetsEnabled" value="true"/>
      <setting name="useColumnLabel" value="true"/>
      <setting name="useGeneratedKeys" value="true"/>
      <setting name="autoMappingBehavior" value="PARTIAL"/>
      <setting name="defaultExecutorType" value="SIMPLE"/>
      <setting name="defaultStatementTimeout" value="20"/>
      <setting name="cacheEnabled" value="false"/>
      <setting name="defaultExecutorType" value="REUSE"/>
  </settings>
  <typeAliases>
<#list tableList as  table>
	<#assign alias = StringUtil.toLowerCaseOfFirstChar(table.javabeanModelClassName) >
	<#assign type = jcb.basePackageModel+"."+table.javabeanModelClassName >
	<typeAlias alias="${alias}" type="${type}" />
</#list>
  </typeAliases>
  <mappers>
	<!--the base xml of sql with  add and delete and update and select    -->
<#list tableList as  table>
	<#assign baseMapper = "BaseMapper" >
	<#if (jcb.xmlBaseMapper??)&&(jcb.xmlBaseMapper?string?trim  != "")>
		<#assign baseMapper = jcb.xmlBaseMapper?string?trim >
	</#if>
	<#assign outModel = jcb.getXmlBasePath()?string?lower_case?replace('.', '/') >
	<#assign outModelPath =  outModel+"/"+ table.getJavabeanName()+ baseMapper >
	<#assign outModelPath =  StringUtil.removeDoubleSlash(outModelPath) >
	<#assign resource =  outModelPath+".xml" >
	<mapper resource="${resource}"/>
</#list>
	<!--the extends xml of sql with something  defined by youself  -->
<#list tableList as  table>
	<#assign mapper = "Mapper" >
	<#if (jcb.xmlMapper??)&&(jcb.xmlMapper?string?trim  != "")>
		<#assign mapper = StringUtil.getJavabeanClassName(jcb.xmlMapper?string?trim) >
	</#if>
	<#assign outModel = jcb.xmlMapper?string?lower_case?replace('.', '/') >
	<#assign outModelPath =  outModel+"/"+ table.getJavabeanName()+ mapper >
	<#assign outModelPath =  StringUtil.removeDoubleSlash(outModelPath) >
	<#assign resource =  outModelPath+".xml" >
	<mapper resource="${resource}"/>
</#list>
  </mappers>
</configuration>