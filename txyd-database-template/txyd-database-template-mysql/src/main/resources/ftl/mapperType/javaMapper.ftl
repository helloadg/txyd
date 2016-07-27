<#-- basemapper名称 -->
<#assign BaseMapper = "BaseMapper" />
<#assign basePackageMapper = jcb.basePackageMapper?string?lower_case  />
<#if (basePackageMapper??) && !(basePackageMapper?string?trim?length == 0) >
	<#assign basePackageMapper = basePackageMapper?string?trim />
	<#assign point = "." />
	<#assign lastIndexOfPoint = basePackageMapper?string?last_index_of(point) +1  />
	<#assign lastWord = basePackageMapper?string?substring(lastIndexOfPoint) />
	<#assign BaseMapper = "Base" +  StringUtil.toUpperCaseOfFirstChar( lastWord ) />
</#if>
<#-- javabeanModelClassNameKey名称 -->
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
<#-- 类文件的package模版 -->
package ${jcb.basePackageMapper?string?lower_case};

<#-- 类文件的import模版 -->
import ${jcb.getBasePackageModel()}.${tableBean.javabeanModelClassName};
<#if (tableBean.getPrimaryKeyNum()>1) >
import ${jcb.getBasePackageModel()}.${javabeanModelClassNameKey};
</#if>
<#-- 类文件的class注释模版 -->
/**		
 * 数据库类型：${tableBean.databaseType}
 * 表所属schema：${tableBean.tableSchema}
 * 表所属用户：${tableBean.tableOwner}
 * 表名称：${tableBean.tableName}
 * 表注释：${tableBean.comments}
 * 类型：${tableBean.tableType}
 * @author：${jcb.author}
 */
public interface ${tableBean.javabeanMapperClassName}  extends ${BaseMapper} <${tableBean.javabeanModelClassName},${javabeanModelClassNameKey}> {

}
