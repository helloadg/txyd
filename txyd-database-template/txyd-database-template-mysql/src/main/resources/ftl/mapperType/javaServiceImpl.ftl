<#-- 类文件的package模版 -->
package ${jcb.basePackageServiceImpl?string?lower_case};

<#assign baseServiceClassName = "BaseService" />
<#assign basePackageService = jcb.basePackageService?lower_case/>

<#if (basePackageService??) && ! (basePackageService?string?trim?length == 0)    >
	<#assign basePackageService = basePackageService?string?trim />
	<#assign point =  "." />
	<#assign lastIndexOfPoint =  basePackageService?string?trim?last_index_of(point) />
	<#assign lastIndexOfPoint = lastIndexOfPoint +1  />
	<#assign lastWord = basePackageService?string?substring(lastIndexOfPoint)  />
	<#assign baseServiceClassName = "Base"+StringUtil.toUpperCaseOfFirstChar(lastWord)  />	
</#if>

<#assign baseServiceImplClassName = baseServiceClassName + "Impl" />

<#assign javabeanModelClassNameKey = "" />
<#if (tableBean.getPrimaryKeyNum() gt 1)   >
	<#assign modelKey = "Key" />
	<#if (jcb.getModelKey()??) && ! (jcb.getModelKey()?string?trim?length == 0) >
		<#assign modelKey = StringUtil.getJavabeanClassName(jcb.getModelKey()?string?trim) />
	</#if>
	<#assign javabeanModelClassNameKey = tableBean.getJavabeanModelClassName()+modelKey />
<#else>
	<#list tableBean.getListColumn() as columnBean >
		<#if (columnBean.isPrimaryKey) >
			<#assign javabeanModelClassNameKey = ModelUtil.getColumnJavaDataType(columnBean,jcb) />
			<#assign javabeanModelClassNameKey = javabeanModelClassNameKey?string?replace("java.lang.", "") />
		</#if>
	</#list>
</#if>

<#-- 类文件的import模版 -->
import org.springframework.stereotype.Service;
import ${jcb.getBasePackageModel()}.${tableBean.javabeanModelClassName};
import ${jcb.getBasePackageService()}.${tableBean.getJavabeanServiceClassName()};
<#if (tableBean.getPrimaryKeyNum()>1) >
import ${jcb.getBasePackageModel()}.${javabeanModelClassNameKey};
</#if>

@Service
public class ${tableBean.getJavabeanServiceImplClassName()} extends ${baseServiceImplClassName} <${tableBean.javabeanModelClassName},${javabeanModelClassNameKey}>   implements ${tableBean.getJavabeanServiceClassName()}{
	
}
