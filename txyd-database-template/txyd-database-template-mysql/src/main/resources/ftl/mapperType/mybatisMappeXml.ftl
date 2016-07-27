<#---  命名空间  -->
<#assign javabeanNameWithPackage = jcb.getBasePackageModel()+"."+tableBean.getJavabeanModelClassName() >
<#assign namespace = "" >
<#if (jcb.getGenerateMybatisType()?string?trim?lower_case == "dao"
		|| jcb.getGenerateMybatisType()?string?trim?lower_case == "" )>
	<#assign namespace = javabeanNameWithPackage >
<#elseif (jcb.getGenerateMybatisType()?string?trim?lower_case == "mapper")>
	<#assign namespace = jcb.getBasePackageMapper()+"."+tableBean.getJavabeanMapperClassName() >
</#if>
<#--- xml 文件   -->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!-- base mybatis file for sql defined by self  -->
<mapper namespace="${namespace}">

</mapper>
