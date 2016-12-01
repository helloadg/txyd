<#-- 类文件的package模版 -->
package ${jcb.basePackageService?string?lower_case};

<#-- 类文件的import模版 -->
import ${jcb.getBasePackageModel()}.${tableBean.javabeanModelClassName};
import ${jcb.getBasePackageBaseService()}.${BaseService};
<#-- 类文件的import模版 -->
<#if (tableBean.getPrimaryKeyNum() gt 1) >
${importModelClassNameKey}
</#if>

<#if (hasPrimaryKey)>
public interface ${tableBean.getJavabeanServiceClassName()} extends ${BaseService}< ${tableBean.javabeanModelClassName},${javabeanModelClassNameKey}> {

}
<#else >
public interface ${tableBean.getJavabeanServiceClassName()} extends ${BaseNoKeyService}< ${tableBean.javabeanModelClassName}> {

}
</#if>
