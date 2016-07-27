<#-- 注释  -->
<!-- 0： 变量相关区 -->
<#assign var=42>
${var}
${var2 !"默认值"}
${var3 !}
<#if var3 ?? >
${var3}
</#if>
${var3?if_exists}

<#assign var3="">
${var3 !"默认值3"}

<#--${var??}-->
${message}


<!-- 1： 数字验证区 -->
<#--   <#setting number_format="currency">   -->
<#assign answer=42>
<!-- ${r'${answer}'} -->
${answer}
<!-- ${r'${answer?string}'} -->
${answer?string}
<!-- ${r'${answer?string.number}'} -->
${answer?string.number}
<!-- ${r'${answer?string.currency}'} -->
${answer?string.currency}
<!-- ${r'${answer?string.percent}'} -->
${answer?string.percent}
<#--
数字格式化插值可采用#{expr;format}形式来格式化数字,其中format可以是: 
mX:小数部分最小X位 
MX:小数部分最大X位 
-->
<#assign x=2.582/> 
<#assign y=4/> 
#{x; M2} <#-- 输出2.58 --> 
#{y; M2} <#-- 输出4 --> 
#{x; m2} <#-- 输出2.58 --> 
#{y; m2} <#-- 输出4.00 --> 
#{x; m1M2} <#-- 输出2.58 --> 
#{y; m1M2} <#-- 输出4.0 --> 
${ -1.1?int } 
${ -1.999?int } 
${ -1.001?float } 
<!-- 2： 日期验证区 -->
${now?string("yyyy-MM-dd HH:mm:ss.SSS zzzz")} 
${now?string("yyyy-MM-dd hh:mm:ss.SSS zzzz")} 
${now?string("yyyy-MM-dd HH:mm:ss.SSS ")} 
${now?string("EEE, MMM d, ''yy")} 
${now?string("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'")} 

<!-- 3： bool验证区 -->
<#assign foo=true/> 
${foo?string("yes", "no")} 

<!-- 4： string验证区（内建函数区） -->
${"<b>name</b>"?html}
${"name"?cap_first}
${"Name"?lower_case}
${"name"?upper_case}
${"name "?trim}



<!-- 5： 逻辑验证区 -->
<#assign age=23>
<#if (age gt 60) >老年人 
<#elseif (age gt 40) >中年人 
<#elseif (age gt 20) >青年人 
<#else> 少年人 
</#if> 

<#switch age>
	<#case 1> 1 <#break>
	<#case 23> 23 <#break>
	<#default> 0 <#break>
</#switch>
<#switch age>
	<#case 1> 1 <#break>
	<#case 23> 23 <#break>
	<#default> 0 <#break>
</#switch>

<#assign name="tt">
<#switch name>
	<#case "t"> t <#break>
	<#case "tt"> tt <#break>
	<#default> hh <#break>
</#switch>

<!-- 6 xml\html 安全 -->
${"<script>firstName"} 
<#escape x as x?html>   
  First name: ${"<script>firstName"}   
  <#noescape>Last name: ${"<script>lastName"}</#noescape>   
  Maiden name: ${"<script>maidenName"}   
</#escape>  






<!-- 10： list验证区 -->
<#assign str= ["winter", "spring", "summer", "autumn"] />
<#list str as s>
	${s_index + 1}:${s}<#if s_has_next>,<#else>;</#if> 
</#list>

<--（用于修剪/忽略）忽略所有前导和尾随空白。去掉左右空白和回车换行 -->	
<#list str as s>
		${s_index + 1}:${s}<#if s_has_next>,<#else>;</#if><#t />
</#list>

<--（用于右修剪/忽略）忽略所有尾随空白。去掉右边空白和回车换行-->	
<#list str as s>
		${s_index + 1}:${s}<#if s_has_next>,<#else>;</#if><#rt />
</#list>

<--（用于左修剪/忽略）忽略所有前导空白。去掉左边空白->	
<#list str as s>
		${s_index + 1}:${s}<#if s_has_next>,<#else>;</#if>     <#lt />
</#list>

<#compress> <#--去除空格的效果-->
<#list str as s>
	${s_index + 1}:${s}<#if s_has_next>,<#else>;</#if> 
</#list>
</#compress>

<@compress single_line=true> <#--去除空格的效果+单行-->
<#list str as s>
	${s_index + 1}:${s}<#if s_has_next>,<#else>;</#if>
</#list>
</@compress>

<@compress single_line=true> <#--去除空格的效果+单行-->
<#list str as s>
<#compress> 
	${s_index + 1}:${s}<#if s_has_next>,<#else>;</#if>
</#compress>
</#list>
</@compress>

<#assign str="12345" />
${str[0..2]}

<#assign array=["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] />
${array[0]}

<#assign array2=array[1..2] />
${array2[0]}

<#assign map={"语文":78, "数学":80}  />
${map["语文"]}

<#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"]  as x> 
${x} 
</#list> 

<#list ["星期一", "星期二", "星期三"]+[ "星期四", "星期五", "星期六", "星期天"]  as x> 
${x} 
</#list> 

<#list [2 + 2, 2, "whatnot"]  as x> 
${x} 
</#list> 

<#list 2..5  as x> 
${x} 
</#list>

<#list 5..3  as x> 
${x} 
</#list>  

<#list list?if_exists as item >
  <#if item_index == 10>
	<#break>
  <#else>
	${(item_index + 1) ?string.number } : ${(item_index + 1) }
  </#if>
</#list>
<#list list?if_exists as item >
  <#if item_index == 10>
	<#break>
  <#else>
	${item.id ?string.number}
	${item.name}
  </#if>
</#list>


<!-- 20 noparse 不处理相关的内容 -->
<#noparse>
  First name: ${"<script>firstName"}  
<#escape x as x?html>   
  First name: ${"<script>firstName"}   
  <#noescape>Last name: ${"<script>lastName"}</#noescape>   
  Maiden name: ${"<script>maidenName"}   
</#escape>
</#noparse> 




<!--21 include :将整个页面导入进来-->
<#include "top.ftl">
${x}
<!--22 import :将页面的变量以及函数导入进来，并放入到map对象中-->
<#import "top.ftl" as service >
${service.x}

<!-- 21 macro  宏定义 -->
<#macro book booklist tt>
<#list booklist as book> 
   ${tt}:${book_index}_${book} 
   <#if book_index gt 2>
   	<#return> 
   </#if>
</#list> 
</#macro> 
<#assign booklist=["数学","英语","地理","化学"] />
<@book booklist  "朕" />
<@book booklist=["数学","英语","地理","化学"]   tt="朕" />

