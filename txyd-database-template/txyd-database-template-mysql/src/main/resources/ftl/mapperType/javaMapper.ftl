<#-- 类文件的package模版 -->
package ${jcb.basePackageMapper?string?lower_case};

<#-- 类文件的import模版 -->
<#if (hasPrimaryKey) >
import ${jcb.basePackageBaseMapper}.${BaseMapper};
import ${jcb.basePackageBaseMapper}.${BaseKeyMapper};
import ${jcb.basePackageModel}.${tableBean.javabeanModelClassName};
	<#if (javabeanModelClassNameKey?string?length >0 )>
${importModelClassNameKey}
	</#if>
<#else >
import ${jcb.basePackageBaseMapper}.${BaseMapper};
import ${jcb.basePackageModel}.${tableBean.javabeanModelClassName};
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
<#if (hasPrimaryKey) >
public interface ${tableBean.javabeanMapperClassName}  extends ${BaseMapper} <${tableBean.javabeanModelClassName}> ,${BaseKeyMapper} <${tableBean.javabeanModelClassName},${javabeanModelClassNameKey}>  {

}
<#else>
public interface ${tableBean.javabeanMapperClassName}  extends ${BaseMapper} <${tableBean.javabeanModelClassName}> {

}
</#if>

