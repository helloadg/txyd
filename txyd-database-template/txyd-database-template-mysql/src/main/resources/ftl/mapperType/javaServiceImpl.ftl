<#-- 类文件的package模版 -->
package ${jcb.basePackageServiceImpl?string?lower_case};

<#-- 类文件的import模版 -->
import org.springframework.stereotype.Service;
import ${jcb.getBasePackageModel()}.${tableBean.javabeanModelClassName};
import ${jcb.getBasePackageService()}.${tableBean.getJavabeanServiceClassName()};
<#if (hasPrimaryKey) >
${importModelClassNameKey};
</#if>

<#if (hasPrimaryKey) >
@Service
public class ${tableBean.getJavabeanServiceImplClassName()} extends ${BaseWithKeyServiceImpl} <${tableBean.javabeanModelClassName},${javabeanModelClassNameKey}>   implements ${tableBean.getJavabeanServiceClassName()}{

}
<#else >
@Service
public class ${tableBean.getJavabeanServiceImplClassName()} extends ${BaseServiceImpl} <${tableBean.javabeanModelClassName}>   implements ${tableBean.getJavabeanServiceClassName()}{

}
</#if>

