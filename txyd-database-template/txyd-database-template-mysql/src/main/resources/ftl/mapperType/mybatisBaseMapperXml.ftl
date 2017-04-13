<#---  命名空间  -->
<#assign javabeanNameWithPackage = jcb.getBasePackageModel()+"."+tableBean.getJavabeanModelClassName() >
<#assign namespace = "" >
<#if (jcb.getGenerateMybatisType()?string?trim?lower_case == "dao"
		|| jcb.getGenerateMybatisType()?string?trim?lower_case == "" )>
	<#assign namespace = javabeanNameWithPackage >
<#elseif (jcb.getGenerateMybatisType()?string?trim?lower_case == "mapper")>
	<#assign namespace = jcb.getBasePackageMapper()+"."+tableBean.getJavabeanMapperClassName() >
</#if>
<#---  rm  -->
<#assign resultMapId = "rm_" + tableBean.getJavabeanModelClassName() >
<#---  自增id名称  -->
<#assign idExtra = "" >
<#list tableBean.getListColumn() as columnBean  >
	<#if (columnBean.isPrimaryKey?? && columnBean.isPrimaryKey && columnBean.getExtra()?? && !(columnBean.getExtra()?string?trim == "")  ) >
		<#assign idExtra = columnBean.columnName >
	</#if>
</#list>
<#---  主键组合  -->
<#assign ids = "" >
<#if (tableBean.getPrimaryKeyNum() gt 0)>
	<#list tableBean.getListColumn() as columnBean  >
		<#if (columnBean.isPrimaryKey?? && columnBean.isPrimaryKey  ) >
			<#assign columnName = columnBean.columnName >
			<#if KeyWords.contains( columnName?string?upper_case?trim) >
				<#assign columnName = "`"+columnName+"`" >
			</#if>
			<#assign ids = ids + columnName +","  >
		</#if>
	</#list>
	<#assign ids = ids?string?substring(0, ids?string?last_index_of(",")) >
</#if>
<#---  主键组合  -->
<#assign idJavaBeanNamesWithItem = "" >
<#if (tableBean.getPrimaryKeyNum() gt 0)>
	<#list tableBean.getListColumn() as columnBean  >
		<#if (columnBean.isPrimaryKey?? && columnBean.isPrimaryKey  ) >
			<#assign columnJavaBeanName = columnBean.javabeanFieldName >
			<#assign idJavaBeanNamesWithItem = idJavaBeanNamesWithItem + r"#{item." +  columnJavaBeanName + "},"  >
		</#if>
	</#list>
	<#assign idJavaBeanNamesWithItem = idJavaBeanNamesWithItem?string?substring(0, idJavaBeanNamesWithItem?string?last_index_of(",")) >
</#if>
<#--- xml 文件   -->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!-- base mybatis file for sql defined by tool  -->
<mapper namespace="${namespace}">
  <!--database table: (${tableBean.getTableName()}) with javabean : (${javabeanNameWithPackage})-->
  <resultMap id="${resultMapId}" type="${javabeanNameWithPackage}">
<#list tableBean.getListColumn() as columnBean  >
  	<result property="${columnBean.getJavabeanFieldName()}" column="${columnBean.getColumnName()}"/>
</#list> 
  </resultMap>
  
  <!--columns of table-->
  <sql id="columns">
    <![CDATA[
<#list tableBean.getListColumn() ? chunk(5) as columnlist  >
	<#list columnlist as  columnBean >
		<#assign columnName = columnBean.getColumnName() >
		<#if ( KeyWords.contains( columnName?string?upper_case?trim) ) >
			<#assign columnName ="`"+columnName+"`" >
		</#if>
		<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
			<#if (columnBean_index == 0)>
		${columnName}<#rt>
			<#else>
				${columnName}<#t>
			</#if>	
		<#else>
			<#if (columnBean_index == 0)>
		${columnName},<#rt>
			<#else>
				${columnName},<#t>
			</#if>			
		</#if>
	</#list>

</#list>
     ]]>
  </sql>
  
  <!--the 'where' sql of search  -->
  <sql id="where_sql">
<#list tableBean.getListColumn() as columnBean  >
	<#assign columnName = columnBean.columnName >
	<#assign javabeanFieldName = columnBean.javabeanFieldName  >
	<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
		<#assign columnName = "`" + columnName + "`"  >
	</#if>
    <if test="${columnBean.getJavabeanFieldName()} != null">
      and ${columnName} =  ${r"#{"+javabeanFieldName+"}"}
    </if>
</#list>
  </sql>
  
  <!--the 'where' sql with where alias of search  -->
  <sql id="where_with_alias_sql">
<#list tableBean.getListColumn() as columnBean  >
	<#assign columnName = columnBean.columnName >
	<#assign javabeanFieldName = jcb.getWhereAlias()?string?trim + "." +  columnBean.getJavabeanFieldName()   >
	<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
		<#assign columnName = "`" + columnName + "`"  >
	</#if>
    <if test="${javabeanFieldName} != null">
      and ${columnName} =  ${r"#{"+javabeanFieldName+"}"}
    </if>
</#list>
  </sql>
  
  <!-- the 'like' sql for search -->
  <sql id="like_sql">
    <if test="like != null and like.size() gt 0 ">
      <foreach collection="like" index="key" item="value" open="" separator="" close="">
        <choose>
<#list tableBean.getListColumn() as columnBean  >
	<#assign columnName = columnBean.columnName >
	<#assign javabeanFieldName = columnBean.javabeanFieldName  >
	<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
		<#assign columnName = "`" + columnName + "`"  >
	</#if>
          <when test="key!=null and '${javabeanFieldName}'.equalsIgnoreCase(key)">
              and ${columnName} like ${r"#{value}"}
          </when>
</#list>
        </choose>
      </foreach>
    </if>
  </sql>
  
  <!-- the 'sort' sql for search -->
  <sql id="sort_sql">
    <if test="sort != null and sort.size() gt 0 ">
      ORDER BY
      <foreach collection="sort" index="key" item="value" open="" separator="," close="">
        <choose>
<#list tableBean.getListColumn() as columnBean  >
	<#assign columnName = columnBean.columnName >
	<#assign javabeanFieldName = columnBean.javabeanFieldName  >
	<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
		<#assign columnName = "`" + columnName + "`"  >
	</#if>
          <when test="key!=null and '${javabeanFieldName}'.equalsIgnoreCase(key)">
            ${columnName}
          </when>
</#list>
        </choose>
        <choose>
          <when test="'asc'.equalsIgnoreCase(value)">
            asc
          </when>
          <when test="'desc'.equalsIgnoreCase(value)">
            desc
          </when>
          <otherwise>
            asc
          </otherwise>
        </choose>
      </foreach>
    </if>
  </sql>
  
  <!-- the 'set' sql for search -->
  <sql id="update_sql">
<#list tableBean.getListColumn() as columnBean  >
	<#assign columnName = columnBean.columnName >
	<#assign javabeanFieldName = columnBean.javabeanFieldName  >
	<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
		<#assign columnName = "`" + columnName + "`"  >
	</#if>
    <if test="${javabeanFieldName} != null">
      ${columnName} =  ${r"#{"+javabeanFieldName+"}"} ,
    </if>
</#list>
  </sql>
  
  <!-- the 'set' sql with table alias for search -->
  <sql id="update_with_alias_sql">
<#list tableBean.getListColumn() as columnBean  >
	<#assign columnName = columnBean.columnName >
	<#assign javabeanFieldName = jcb.getTableAlias()?string?trim + "." + columnBean.javabeanFieldName  >
	<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
		<#assign columnName = "`" + columnName + "`"  >
	</#if>
    <if test="${javabeanFieldName} != null">
      ${columnName} = ${r"#{"+javabeanFieldName+"}"} ,
    </if>
</#list>
  </sql>

  <!-- update the records by ids sql -->
  <update id="update" parameterType="java.util.Map">
    <trim>
      UPDATE ${tableBean.getTableName()}
  	  <set>
  	    <include refid="update_with_alias_sql"/>
  	  </set>
  	  <where>
  	    <include refid="where_with_alias_sql"/>
      </where>
    </trim>
  </update>

<#if (tableBean.getPrimaryKeyNum() gt  0) >
  <!-- update the table by 'id' sql -->
  <update id="updateById" parameterType="java.util.Map">
    <trim>
      UPDATE ${tableBean.getTableName()}
      <set>
        <include refid="update_with_alias_sql"/>
      </set>
	  <where>
	<#list tableBean.getListColumn() as columnBean  >
		<#assign columnName = columnBean.columnName >
		<#assign javabeanFieldName = columnBean.javabeanFieldName  >
		<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
			<#assign columnName = "`" + columnName + "`"  >
		</#if>
		<#if (columnBean.isPrimaryKey) >
		  and ${columnName} = ${r"#{"+javabeanFieldName+"}"}
		</#if>
	</#list>
	  </where>
    </trim>
  </update>
</#if>

<#if (tableBean.getPrimaryKeyNum() gt 0 )  >
	<#if (tableBean.getPrimaryKeyNum() == 1 )  >
  <!-- update the records by ids sql -->
  <update id="updateByIds" parameterType="java.util.Map">
    <trim>
      UPDATE ${tableBean.getTableName()}
      <set>
	    <include refid="update_with_alias_sql"/>
      </set>
	    WHERE ${ids} in
	  <foreach collection="ids" item="item" open="(" separator="," close=")">
	    ${r"#{item}"}
	  </foreach>
    </trim>
  </update>
	<#else>
  <!-- update the records by ids sql -->
  <update id="updateByIds" parameterType="java.util.Map">
	<trim>
      UPDATE ${tableBean.getTableName()}
      <set>
        <include refid="update_with_alias_sql"/>
	  </set>
        WHERE (${ids}) in
	  <foreach collection="ids" item="item" open="(" separator="," close=")">
         (${idJavaBeanNamesWithItem})
      </foreach>
    </trim>
  </update>
	</#if>
</#if>

<#if (idExtra?? && idExtra !="" )  >
  <!-- insert value sql -->
  <insert id="insert" useGeneratedKeys="true" keyProperty="${idExtra}">
    <trim>
     <![CDATA[
       INSERT INTO ${tableBean.getTableName()}
         (
	 <#list tableBeanWithoutExtra.getListColumn() ? chunk(5) as columnlist  >
	 	<#list columnlist as  columnBean >
	 		<#assign columnName = columnBean.getColumnName() >
	 		<#if ( KeyWords.contains( columnName?string?upper_case?trim) ) >
	 			<#assign columnName ="`"+columnName+"`" >
	 		</#if>
	 		<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
	 			<#if (columnBean_index == 0)>
            ${columnName}<#rt>
	 			<#else>
            ${columnName}<#t>
	 			</#if>
	 		<#else>
	 			<#if (columnBean_index == 0)>
            ${columnName},<#rt>
	 			<#else>
            ${columnName},<#t>
	 			</#if>
	 		</#if>
	 	</#list>

	 </#list>
         )
       VALUES
         (
	 <#list tableBeanWithoutExtra.getListColumn() ? chunk(5) as columnlist  >
	  	<#list columnlist as  columnBean >
	  		<#assign columnJavaBeanName = columnBean.getJavabeanFieldName() >
	  		<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
	  			<#if (columnBean_index == 0)>
            ${r"#{"+columnJavaBeanName+"}"}<#rt>
	  			<#else>
            ${r"#{"+columnJavaBeanName+"}"}<#t>
	  			</#if>
	  		<#else>
	  			<#if (columnBean_index == 0)>
            ${r"#{"+columnJavaBeanName+"}"},<#rt>
	  			<#else>
            ${r"#{"+columnJavaBeanName+"}"},<#t>
	  			</#if>
	  		</#if>
	  	</#list>

	  </#list>
         )
      ]]>
    </trim>
  </insert>
<#else>
  <!-- insert value sql -->
  <insert id="insert" >
    <trim>
      <![CDATA[
       INSERT INTO ${tableBean.getTableName()}
         (
	  <#list tableBean.getListColumn() ? chunk(5) as columnlist  >
	  	<#list columnlist as  columnBean >
	  		<#assign columnName = columnBean.getColumnName() >
	  		<#if ( KeyWords.contains( columnName?string?upper_case?trim) ) >
	  			<#assign columnName ="`"+columnName+"`" >
	  		</#if>
	  		<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
	  			<#if (columnBean_index == 0)>
            ${columnName}<#rt>
	  			<#else>
            ${columnName}<#t>
	  			</#if>
	  		<#else>
	  			<#if (columnBean_index == 0)>
            ${columnName},<#rt>
	  			<#else>
            ${columnName},<#t>
	  			</#if>
	  		</#if>
	  	</#list>

	  </#list>
         )
       VALUES
         (
	  <#list tableBean.getListColumn() ? chunk(5) as columnlist  >
	  	<#list columnlist as  columnBean >
	  		<#assign columnJavaBeanName = columnBean.getJavabeanFieldName() >
	  		<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
	  			<#if (columnBean_index == 0)>
            ${r"#{"+columnJavaBeanName+"}"}<#rt>
	  			<#else>
            ${r"#{"+columnJavaBeanName+"}"}<#t>
	  			</#if>
	  		<#else>
	  			<#if (columnBean_index == 0)>
            ${r"#{"+columnJavaBeanName+"}"},<#rt>
	  			<#else>
            ${r"#{"+columnJavaBeanName+"}"},<#t>
	  			</#if>
	  		</#if>
	  	</#list>

	  </#list>
         )
      ]]>
    </trim>
  </insert>
</#if>

<#if (idExtra?? && idExtra !="" )  >
  <!-- insert into table with not null -->
  <insert id="insertNotNull" useGeneratedKeys="true" keyProperty="${idExtra}">
    INSERT INTO  ${tableBean.getTableName() }
    <trim prefix="(" suffix=")" suffixOverrides=",">
	<#list tableBean.getListColumn() as columnBean  >
		<#assign columnName = columnBean.columnName >
		<#assign javabeanFieldName = columnBean.javabeanFieldName  >
		<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
			<#assign columnName = "`" + columnName + "`"  >
		</#if>
      <if test="${javabeanFieldName} != null">
        ${columnName},
      </if>
	</#list>	
    </trim>
    <trim prefix="  VALUES(" suffix=")" suffixOverrides=",">
	<#list tableBean.getListColumn() as columnBean  >
		<#assign columnName = columnBean.columnName >
		<#assign javabeanFieldName = columnBean.javabeanFieldName  >
		<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
			<#assign columnName = "`" + columnName + "`"  >
		</#if>
      <if test="${javabeanFieldName} != null">
         ${r"#{"+javabeanFieldName+"}"} ,
      </if>
	</#list>	
    </trim>
  </insert>
<#else>
  <!-- insert into table with not null -->
  <insert id="insertNotNull" >
    INSERT INTO  ${tableBean.getTableName() }
    <trim prefix="(" suffix=")" suffixOverrides=",">
	<#list tableBean.getListColumn() as columnBean  >
		<#assign columnName = columnBean.columnName >
		<#assign javabeanFieldName = columnBean.javabeanFieldName  >
		<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
			<#assign columnName = "`" + columnName + "`"  >
		</#if>
      <if test="${javabeanFieldName} != null">
        ${columnName},
      </if>
	</#list>	
    </trim>
    <trim prefix="  VALUES(" suffix=")" suffixOverrides=",">
	<#list tableBean.getListColumn() as columnBean  >
		<#assign columnName = columnBean.columnName >
		<#assign javabeanFieldName = columnBean.javabeanFieldName  >
		<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
			<#assign columnName = "`" + columnName + "`"  >
		</#if>
      <if test="${javabeanFieldName} != null">
         ${r"#{"+javabeanFieldName+"}"} ,
      </if>
	</#list>	
    </trim>
  </insert>
</#if>

<#if (idExtra?? && idExtra !="" )  >
  <!--insert not exists into table -->
  <insert id="insertNotExists" useGeneratedKeys="true" keyProperty="${idExtra}" >
    <trim>
      INSERT INTO ${tableBean.getTableName()}
        <![CDATA[
        (
	<#list tableBeanWithoutExtra.getListColumn() ? chunk(5) as columnlist  >
		<#list columnlist as  columnBean >
			<#assign columnName = columnBean.getColumnName() >
			<#if ( KeyWords.contains( columnName?string?upper_case?trim) ) >
				<#assign columnName ="`"+columnName+"`" >
			</#if>
			<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
				<#if (columnBean_index == 0)>
            ${columnName}<#rt>
				<#else>
            ${columnName}<#t>
				</#if>	
			<#else>
				<#if (columnBean_index == 0)>
            ${columnName},<#rt>
				<#else>
            ${columnName},<#t>
				</#if>			
			</#if>
		</#list>
	
	</#list>
        )
        ]]>
      <foreach collection="map" index="value" item="where" open="" close="" separator=" UNION ALL " >
        <if test="value != null">
          SELECT
	<#list tableBeanWithoutExtra.getListColumn() ? chunk(5) as columnlist  >
		<#list columnlist as  columnBean >
			<#assign columnJavaBeanName = columnBean.getJavabeanFieldName() >
			<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
				<#if (columnBean_index == 0)>
            ${r"#{value."+columnJavaBeanName+"}"}<#rt>
				<#else>
            ${r"#{value."+columnJavaBeanName+"}"}<#t>
				</#if>	
			<#else>
				<#if (columnBean_index == 0)>
            ${r"#{value."+columnJavaBeanName+"}"},<#rt>
				<#else>
            ${r"#{value."+columnJavaBeanName+"}"},<#t>
				</#if>			
			</#if>
		</#list>
	
	</#list>
          FROM dual
        </if>
        <if test="where !=null" >
          WHERE NOT EXISTS
          (
            SELECT 1 FROM ${tableBean.getTableName()}
            <where>
	<#list tableBean.getListColumn() as columnBean  >
		<#assign columnName = columnBean.columnName >
		<#assign javabeanFieldName = columnBean.javabeanFieldName  >
		<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
			<#assign columnName = "`" + columnName + "`"  >
		</#if>
              <if test="${r'where.'+javabeanFieldName} != null">
                AND ${columnName} =  ${r"#{where."+javabeanFieldName+"}"}
              </if>
	</#list>
            </where>
          )
      </if>
    </foreach>
    </trim>
  </insert>
<#else>
  <!--insert not exists into table -->
  <insert id="insertNotExists" >
    <trim>
      INSERT INTO ${tableBean.getTableName()}
        <![CDATA[
        (
	<#list tableBean.getListColumn() ? chunk(5) as columnlist  >
		<#list columnlist as  columnBean >
			<#assign columnName = columnBean.getColumnName() >
			<#if ( KeyWords.contains( columnName?string?upper_case?trim) ) >
				<#assign columnName ="`"+columnName+"`" >
			</#if>
			<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
				<#if (columnBean_index == 0)>
            ${columnName}<#rt>
				<#else>
            ${columnName}<#t>
				</#if>	
			<#else>
				<#if (columnBean_index == 0)>
            ${columnName},<#rt>
				<#else>
            ${columnName},<#t>
				</#if>			
			</#if>
		</#list>
	
	</#list>
        )
         ]]>
      <foreach collection="list" index="value" item="where" open="" close="" separator=" UNION ALL " >
        <if test="value != null">
          SELECT
	<#list tableBeanWithoutExtra.getListColumn() ? chunk(5) as columnlist  >
		<#list columnlist as  columnBean >
			<#assign columnJavaBeanName = columnBean.getJavabeanFieldName() >
			<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
				<#if (columnBean_index == 0)>
            ${r"#{value."+columnJavaBeanName+"}"}<#rt>
				<#else>
            ${r"#{value."+columnJavaBeanName+"}"}<#t>
				</#if>	
			<#else>
				<#if (columnBean_index == 0)>
            ${r"#{value."+columnJavaBeanName+"}"},<#rt>
				<#else>
            ${r"#{value."+columnJavaBeanName+"}"},<#t>
				</#if>			
			</#if>
		</#list>
	
	</#list>
          FROM dual
        </if>
        <if test="where !=null" >
          WHERE NOT EXISTS
          (
            SELECT 1 FROM ${tableBean.getTableName()}
            <where>
	<#list tableBean.getListColumn() as columnBean  >
		<#assign columnName = columnBean.columnName >
		<#assign javabeanFieldName = columnBean.javabeanFieldName  >
		<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
			<#assign columnName = "`" + columnName + "`"  >
		</#if>
              <if test="${r'where.'+javabeanFieldName} != null">
                AND ${columnName} =  ${r"#{where."+javabeanFieldName+"}"}
              </if>
	</#list>
            </where>
           )
        </if>
      </foreach>
    </trim>
  </insert>
</#if>


<#if (idExtra?? && idExtra !="" )  >
  <!--insert batch into table -->
  <insert id="insertBatch" parameterType="java.util.List"  useGeneratedKeys="true" keyProperty="${idExtra}" >
    INSERT INTO ${tableBean.getTableName()}
      <![CDATA[
      (
	<#list tableBeanWithoutExtra.getListColumn() ? chunk(5) as columnlist  >
		<#list columnlist as  columnBean >
			<#assign columnName = columnBean.getColumnName() >
			<#if ( KeyWords.contains( columnName?string?upper_case?trim) ) >
				<#assign columnName ="`"+columnName+"`" >
			</#if>
			<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
				<#if (columnBean_index == 0)>
        ${columnName}<#rt>
				<#else>
        ${columnName}<#t>
				</#if>	
			<#else>
				<#if (columnBean_index == 0)>
        ${columnName},<#rt>
				<#else>
        ${columnName},<#t>
				</#if>			
			</#if>
		</#list>
	
	</#list>
      )
      ]]>
    VALUES
    <foreach collection="list" item="item" open="" separator="," close="">
      (
	<#list tableBeanWithoutExtra.getListColumn() ? chunk(5) as columnlist  >
		<#list columnlist as  columnBean >
			<#assign columnJavaBeanName = columnBean.getJavabeanFieldName() >
			<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
				<#if (columnBean_index == 0)>
        ${r"#{item."+columnJavaBeanName+"}"}<#rt>
				<#else>
        ${r"#{item."+columnJavaBeanName+"}"}<#t>
				</#if>	
			<#else>
				<#if (columnBean_index == 0)>
        ${r"#{item."+columnJavaBeanName+"}"},<#rt>
				<#else>
        ${r"#{item."+columnJavaBeanName+"}"},<#t>
				</#if>			
			</#if>
		</#list>
	
	</#list>    	
      )
    </foreach>
  </insert>
<#else>
  <!--insert batch into table -->
  <insert id="insertBatch" parameterType="java.util.List">
    INSERT INTO ${tableBean.getTableName()}
      <![CDATA[
      (
	<#list tableBean.getListColumn() ? chunk(5) as columnlist  >
		<#list columnlist as  columnBean >
			<#assign columnName = columnBean.getColumnName() >
			<#if ( KeyWords.contains( columnName?string?upper_case?trim) ) >
				<#assign columnName ="`"+columnName+"`" >
			</#if>
			<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
				<#if (columnBean_index == 0)>
        ${columnName}<#rt>
				<#else>
        ${columnName}<#t>
				</#if>	
			<#else>
				<#if (columnBean_index == 0)>
        ${columnName},<#rt>
				<#else>
        ${columnName},<#t>
				</#if>			
			</#if>
		</#list>
	
	</#list>
      )
      ]]>
    VALUES
    <foreach collection="list" item="item" open="" separator="," close="">
      (
	<#list tableBean.getListColumn() ? chunk(5) as columnlist  >
		<#list columnlist as  columnBean >
			<#assign columnJavaBeanName = columnBean.getJavabeanFieldName() >
			<#if !(columnlist_has_next)&&! (columnBean_has_next)  ><#--- 最后一个  -->
				<#if (columnBean_index == 0)>
          ${r"#{item."+columnJavaBeanName+"}"}<#rt>
				<#else>
          ${r"#{item."+columnJavaBeanName+"}"}<#t>
				</#if>	
			<#else>
				<#if (columnBean_index == 0)>
          ${r"#{item."+columnJavaBeanName+"}"},<#rt>
				<#else>
          ${r"#{item."+columnJavaBeanName+"}"},<#t>
				</#if>			
			</#if>
		</#list>
	
	</#list>    	
      )
    </foreach>
  </insert>
</#if>

  
<#if (tableBean.getPrimaryKeyNum() gt 0 )  >
  <!-- get the records by id -->
  <select id="getById" resultMap="${resultMapId}">
    SELECT
    <include refid="columns"/>
      <![CDATA[
        FROM ${tableBean.getTableName() }
      ]]>
    <where>
	<#list tableBean.getListColumn() as columnBean  >
		<#assign columnName = columnBean.columnName >
		<#assign javabeanFieldName = columnBean.javabeanFieldName  >
		<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
			<#assign columnName = "`" + columnName + "`"  >
		</#if>
		<#if (columnBean.isPrimaryKey) >
		and ${columnName} =${r"#{" + javabeanFieldName + "}"}
		</#if>
	</#list>	
    </where>
	<if test="isForUpdate !=null and isForUpdate == true ">
        FOR UPDATE
    </if>
  </select>
</#if>

<#if (tableBean.getPrimaryKeyNum() gt 0 )  >
	<#if (tableBean.getPrimaryKeyNum() == 1 )  >	
  <!-- get the records by ids -->
  <select id="getByIds" resultMap="${resultMapId}" parameterType="java.util.List">
	<trim>
      SELECT
      <include refid="columns"/>
      FROM ${tableBean.getTableName()}
	    WHERE ${ids} in
      <foreach collection="ids" item="item" open="(" separator="," close=")">
       ${r"#{item}"}
      </foreach>
      <if test="isForUpdate !=null and isForUpdate == true ">
          FOR UPDATE
      </if>
    </trim>
  </select>  
	<#else>
  <!-- get the records by ids -->
  <select id="getByIds" resultMap="${resultMapId}" parameterType="java.util.List">
    <trim>
      SELECT
      <include refid="columns"/>
      FROM ${tableBean.getTableName()}
	    WHERE (${ids}) in
      <foreach collection="ids" item="item" open="(" separator="," close=")">
        (${idJavaBeanNamesWithItem})
      </foreach>
      <if test="isForUpdate !=null and isForUpdate == true ">
          FOR UPDATE
      </if>
    </trim>
  </select>	
	</#if>
</#if>

  <!-- get the records by condition -->
  <select id="select" resultMap="${resultMapId}">
    <trim>
      SELECT
      	<include refid="columns"/>
	  	FROM ${tableBean.getTableName()}
      <where>
        <include refid="where_with_alias_sql"/>
      </where>
      <include refid="sort_sql"/>
      <choose>
        <when test="limit != null  and offset != null">
          limit ${r"#{limit}"} offset  ${r"#{offset}"}
        </when>
        <when test="limit != null  and offset == null">
          limit ${r"#{limit}"}
        </when>
      </choose>
      <if test="isForUpdate !=null and isForUpdate == true ">
          FOR UPDATE
      </if>
    </trim>
  </select>

  <!-- get the count by condition -->
  <select id="selectCount" resultType="int">
    <trim>
      SELECT count(1) FROM ${tableBean.getTableName()}
      <where>
        <include refid="where_with_alias_sql"/>
      </where>
    </trim>
  </select>
 
  <!-- delete the records by conditions -->
  <delete id="delete">
    <trim>
      DELETE FROM ${tableBean.getTableName()}
      <where>
        <include refid="where_sql"/>
      </where>
    </trim>
  </delete>

<#if (tableBean.getPrimaryKeyNum() gt 0 )  >
  <!-- deleted the records by id -->
  <delete id="deleteById">
    <trim>
      DELETE FROM ${tableBean.getTableName() }
      <where>
	  <#list tableBean.getListColumn() as columnBean  >
	  	<#assign columnName = columnBean.columnName >
	  	<#assign javabeanFieldName = columnBean.javabeanFieldName  >
	  	<#if (KeyWords.contains( columnName?string?upper_case?trim )) >
	  		<#assign columnName = "`" + columnName + "`"  >
	  	</#if>
	  	<#if (columnBean.isPrimaryKey) >
	  	and ${columnName} = ${r"#{" + javabeanFieldName + "}"}
	  	</#if>
	  </#list>
      </where>
    </trim>
  </delete>
</#if>

<#if (tableBean.getPrimaryKeyNum() gt 0 )  >
	<#if (tableBean.getPrimaryKeyNum() == 1 )  >	
  <!-- deleted the records by ids -->
  <delete id="deleteByIds" parameterType="java.util.List">
    <trim>
      delete from ${tableBean.getTableName()}
        WHERE ${ids} in
      <foreach collection="list" item="item" open="(" separator="," close=")">
	    ${r"#{item}"}
      </foreach>
    </trim>
  </delete> 
	<#else>
  <!-- deleted the records by ids -->
  <delete id="deleteByIds" parameterType="java.util.List">
    <trim>
      delete from ${tableBean.getTableName() }
	    WHERE (${ids}) in
      <foreach collection="list" item="item" open="(" separator="," close=")">
        (${idJavaBeanNamesWithItem})
      </foreach>
    </trim>
  </delete>
	</#if>
</#if>
</mapper>
