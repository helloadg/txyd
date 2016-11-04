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
<#---  主键组合  -->
<#assign idJavaBeanNamesWithItem = "" >
<#list tableBean.getListColumn() as columnBean  >
	<#if (columnBean.isPrimaryKey?? && columnBean.isPrimaryKey  ) >
		<#assign columnJavaBeanName = columnBean.javabeanFieldName >
		<#assign idJavaBeanNamesWithItem = idJavaBeanNamesWithItem + r"#{item." +  columnJavaBeanName + "},"  >
	</#if>
</#list>
<#assign idJavaBeanNamesWithItem = idJavaBeanNamesWithItem?string?substring(0, idJavaBeanNamesWithItem?string?last_index_of(",")) >
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
    <where>
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
    </where>
  </sql>
  
  <!--the 'where' sql with where alias of search  -->
  <sql id="where_with_alias_sql">
    <where>
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
    </where>
  </sql>
  
  <!-- the 'sort' sql for search -->
  <sql id="sort_sql">
    <trim>
      <if test="sort != null and sort.size() >0 ">
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
    </trim>
  </sql>
  
  <!-- the 'set' sql for search -->
  <sql id="update_sql">
    <set>
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
    </set>
  </sql>
  
  <!-- the 'set' sql with table alias for search -->
  <sql id="update_with_alias_sql">
    <set>
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
    </set>
  </sql>
  
<#if (tableBean.getPrimaryKeyNum() gt  0) >
  <!-- update the table by 'id' sql -->
  <update id="updateById" parameterType="java.util.Map">
    UPDATE ${tableBean.getTableName()}
    <include refid="update_with_alias_sql"/>
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
  </update>
</#if>
  <!-- update the records by ids sql -->
  <update id="update" parameterType="java.util.Map">
	UPDATE ${tableBean.getTableName()}
      <include refid="update_with_alias_sql"/>
      <include refid="where_with_alias_sql"/>
  </update>
<#if (tableBean.getPrimaryKeyNum() gt 0 )  >
	<#if (tableBean.getPrimaryKeyNum() == 1 )  >
  <!-- update the records by ids sql -->
  <update id="updateByIds" parameterType="java.util.Map">
    UPDATE ${tableBean.getTableName()}
	  <include refid="update_with_alias_sql"/>
	  WHERE ${ids} in
	  <foreach collection="ids" item="item" open="(" separator="," close=")">
	    ${r"#{item}"}
	  </foreach>
  </update>
	<#else>
  <!-- update the records by ids sql -->
  <update id="updateByIds" parameterType="java.util.Map">
    UPDATE ${tableBean.getTableName()}
      <include refid="update_with_alias_sql"/>
      WHERE (${ids}) in
	  <foreach collection="ids" item="item" open="(" separator="," close=")">
         (${idJavaBeanNamesWithItem})
      </foreach>
  </update>
	</#if>
</#if>

<#if (idExtra?? )  >
  <!-- insert value sql -->
  <insert id="insert" useGeneratedKeys="true" keyProperty="${idExtra}">
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
  </insert>
<#else>
  <!-- insert value sql -->
  <insert id="insert" >
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
  </insert>
</#if>

<#if (idExtra?? )  >
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
</#if>


<#if (idExtra?? )  >
  <!--insert batch into table -->
  <insert id="insertBatch" parameterType="java.util.List">
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
  </select>
</#if>

<#if (tableBean.getPrimaryKeyNum() gt 0 )  >
	<#if (tableBean.getPrimaryKeyNum() == 1 )  >	
  <!-- get the records by ids -->
  <select id="getByIds" resultMap="${resultMapId}" parameterType="java.util.List">
    SELECT
    <include refid="columns"/>
    FROM ${tableBean.getTableName()}
	  WHERE ${ids} in
    <foreach collection="list" item="item" open="(" separator="," close=")">
     ${r"#{item}"}      
    </foreach>
  </select>  
	<#else>
  <!-- get the records by ids -->
  <select id="getByIds" resultMap="${resultMapId}" parameterType="java.util.List">
    SELECT
    <include refid="columns"/>
    FROM ${tableBean.getTableName()}  
	  WHERE (${ids}) in
    <foreach collection="list" item="item" open="(" separator="," close=")">
      (${idJavaBeanNamesWithItem})
    </foreach>
  </select>	
	</#if>
</#if>

  <!-- get the records by condition -->
  <select id="select" resultMap="${resultMapId}">
    SELECT 
    	<include refid="columns"/>
		FROM ${tableBean.getTableName()}
    <include refid="where_with_alias_sql"/>
    <include refid="sort_sql"/>
    <choose>
      <when test="limit != null  and offset != null">
        limit ${r"#{limit}"} offset  ${r"#{offset}"}  
      </when>
      <when test="limit != null  and offset == null">
        limit ${r"#{limit}"} 
      </when>
    </choose>
  </select>

  <!-- get the count by condition -->
  <select id="selectCount" resultType="int">
    SELECT count(1) FROM ${tableBean.getTableName()}
    <include refid="where_with_alias_sql"/>
  </select>
 
  <!-- delete the records by conditions -->
  <delete id="delete">
    DELETE FROM ${tableBean.getTableName()}
    <include refid="where_sql"/>
  </delete>

<#if (tableBean.getPrimaryKeyNum() gt 0 )  >
  <!-- deleted the records by id -->
  <delete id="deleteById">
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
  </delete>
</#if>

<#if (tableBean.getPrimaryKeyNum() gt 0 )  >
	<#if (tableBean.getPrimaryKeyNum() == 1 )  >	
  <!-- deleted the records by ids -->
  <delete id="deleteByIds" parameterType="java.util.List">
    delete from ${tableBean.getTableName()} 
      WHERE ${ids} in
    <foreach collection="list" item="item" open="(" separator="," close=")">      
	  ${r"#{item}"}
    </foreach>
  </delete> 
	<#else>
  <!-- deleted the records by ids -->
  <delete id="deleteByIds" parameterType="java.util.List">
    delete from ${tableBean.getTableName() }
	  WHERE (${ids}) in
    <foreach collection="list" item="item" open="(" separator="," close=")">
      (${idJavaBeanNamesWithItem})
    </foreach>
  </delete>
	</#if>
</#if>
</mapper>
