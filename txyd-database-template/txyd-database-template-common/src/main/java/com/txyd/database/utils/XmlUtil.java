package com.txyd.database.utils;

import com.txyd.database.bean.ColumnBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.bean.TableBean;
import com.txyd.database.inter.Primarykey;
import com.txyd.database.sql.KeyWords;
import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.BeanUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 生成mybatis所对应的xml文件
 * @author Administrator
 *
 */
public class XmlUtil  {
	public static class MybatisXmlUtil{
		private static String MYSQL_SEPARATE="`"; 
		/**
		 * 
		 * @Description 获得sum个空格
		 * @author     
		 * @param sum
		 * @return
		 */
		private static String getSpaceOfSum(int sum)
		{
			String result="";
			if(sum>0)
			{
				for(int i=0;i<sum;i++)
				{
					result+=" ";
				}
			}
			return result;
			
		}
		/**
		 * 
		 * @Description 获得字符串前端的空白字符
		 * @author     
		 * @param str
		 * @return
		 */
		private static String getStartSpaceOfStr(String str)
		{
			String result="";
			if(str!=null)
			{
				if(str.trim().equals(""))
				{
					result= str;
				}else
				{
					Pattern p=Pattern.compile("([\\S])");
					Matcher m=p.matcher(str);
					int end=0;
					if(m.find())
					{
						end=m.start();
//						System.out.println(m.start());
						
					}
					result=str.substring(0, end);
				}
			}
			return result;
		}
		private static String modifyXmlContext(String xml)
		{
			if(xml.trim().equals(""))
			{
				return "";
			}
			String[] xmls=xml.split("\n");
			StringBuffer xmlsb=new StringBuffer();
			for(int i=0;i<xmls.length;i++)
			{
				//String line="";
				String str=xmls[i];
				if(!str.trim().equals(""))
				{
//					String space=str.substring(0,str.indexOf("<"));
					String space=getStartSpaceOfStr(str);
					if(str.indexOf(">")!=str.length())
					{
						str=str.replace(">", ">\n");
						str=str.replace("<", "\n<");
					}
					String[] strs_temp=str.split("\n");
					List<String> list_str=new ArrayList<String>();
					for(int j=0;j<strs_temp.length;j++)
					{
						if(!strs_temp[j].trim().equals(""))
						{
							list_str.add(strs_temp[j]);
						}
					}
					if(list_str.size()>0)
					{
						for(int j=0;j<list_str.size();j++)
						{
							String content=list_str.get(j);
							if(j==0||j==list_str.size()-1)
							{
								if(content.contains("<"))
								{
									content=space+content+"\n";							
								}else
								{
									content=space+"  "+content+"\n";
								}																
							}else
							{
								content=space+"  "+content+"\n";
							}
							
							xmlsb.append(content);
						}
					}
					
				}
				
			}
			//处理<![CDATA[*]]>，以及多个","的情况
			String content=xmlsb.toString();
			xmlsb.setLength(0);
			String strs[]=content.split("\n");
			if(strs==null||strs.length==0)
			{
				return "";
			}
			for(String str:strs)
			{
				String line="";
				if(!str.trim().equals("")&&str.trim().startsWith("<![CDATA["))
				{
//					String space=str.substring(0,str.indexOf("<"));
					String space=getStartSpaceOfStr(str);
					str=str.replace("<![CDATA[", "<![CDATA[\n");
					str=str.replace("]]>", "\n]]>");
					String[] strs_temp=str.split("\n");
					if(strs_temp!=null&&strs_temp.length>0)
					{
						List<String> list=new ArrayList<String>();
						for(String ss:strs_temp)
						{
							if(!ss.trim().equals(""))
							{
								list.add(ss.trim());
							}
						}
						if(list.size()>0)
						{
							for(int i=0;i<list.size();i++)
							{
								if(i==0||i==list.size()-1)
								{
									line+=space+list.get(i)+"\n";
								}else
								{
									line+=space+"  "+list.get(i)+"\n";
								}
							}
						}
						
					}
					//////
					
				}else
				{
					line=str+"\n";
				}
				xmlsb.append(line);
				
			}
			//处理"("、")"的情况
			content=xmlsb.toString();
			xmlsb.setLength(0);
			strs=content.split("\n");
			if(strs==null||strs.length==0)
			{
				return "";
			}
			for(String str:strs)
			{
				String line="";
				if(!str.trim().equals("")&&!str.contains("<")&&!str.contains(">")&&(str.contains("(")||str.contains(")"))&&str.length()>80)
				{
					String space=getStartSpaceOfStr(str);
					str=str.trim().replace("(", "\n(").replace(")", ")\n");
					String[] strs_kuoHao=str.split("\n");
					if(strs_kuoHao.length>0)
					{
						for(String strKuoHao:strs_kuoHao)
						{
							strKuoHao=strKuoHao.trim();
							if(strKuoHao.contains("(")||strKuoHao.contains(")"))
							{
								line+=space+"  "+strKuoHao+"\n";								
							}else
							{
								if(!strKuoHao.equals(""))
								{
									line+=space+strKuoHao+"\n";	
									
								}								
							}
						}
					}
				}else
				{
					line=str+"\n";
				}
				xmlsb.append(line);
				
			}
			
			//处理多个","的情况
			content=xmlsb.toString();
			xmlsb.setLength(0);
			strs=content.split("\n");
			if(strs==null||strs.length==0)
			{
				return "";
			}
			for(String str:strs)
			{
				String line="";
				if(!str.trim().equals("")&&!str.contains("<")&&!str.contains(">")&&str.contains(",")&&str.length()>80)
				{
					String space=getStartSpaceOfStr(str);
					String[] strs_temp=str.trim().split(",");
					if(strs_temp!=null&&strs_temp.length>0)
					{
						List<String> list=new ArrayList<String>();
						String line_temp="";
						for(int i=0;i<strs_temp.length;i++)
						{
							String str_temp=strs_temp[i]+",";
							if(line_temp.length()>80)
							{
								line_temp+=str_temp;
								list.add(line_temp.trim());
								line_temp="";
							}else
							{
								line_temp+=str_temp;
							}							
						}
						if(!line_temp.trim().equals(""))
						{
							list.add(line_temp.trim());
						}
						if(list.size()>0)
						{
							String firstLine=space+list.get(0);
							int sum=0;
							if(firstLine.contains(","))
							{
								sum=firstLine.indexOf(",");
							}else
							{
								sum=firstLine.length();
							}
							line+=firstLine+"\n";
							for(int i=1;i<list.size();i++)
							{
								String str_temp=list.get(i).trim();
								str_temp=getSpaceOfSum(sum)+str_temp;
								line+=str_temp+"\n";
							}
						}
						line=line.substring(0,line.lastIndexOf(","))+"\n";
					}
				}else
				{
					line=str+"\n";
				}
				xmlsb.append(line);
			}
			return xmlsb.toString();
		}
		/**
		 * 取得数据库表的所对应的mybatis的xml文件
		 * @param tableBean
		 * @param jcb
		 * @return
		 */
		public static String getXmlByTable(TableBean tableBean,JavaConfigBean jcb)
		{
			try
			{
				if(tableBean==null||jcb==null||tableBean.getListColumn()==null||tableBean.getListColumn().size()==0)
				{
					return null;
				}
				TableBean tb=new TableBean();
				{//复制传入的table的相关信息
					BeanUtils.copyProperties(tableBean, tb);
					List<ColumnBean> list_columnBean=new ArrayList<ColumnBean>();
					for(int i=0;i<tb.getListColumn().size();i++)
					{
						ColumnBean columnBean=new ColumnBean();
						BeanUtils.copyProperties(tb.getListColumn().get(i), columnBean);
						list_columnBean.add(columnBean);
					}
					tb.setListColumn(list_columnBean);
				}
				

				String javabeanNameWithPackage=jcb.getBasePackageModel()+"."+tb.getJavabeanModelClassName();
				String namespace="";
				if(jcb.getGenerateMybatisType().trim().equalsIgnoreCase("dao")||jcb.getGenerateMybatisType().trim().equals(""))
				{
					namespace=javabeanNameWithPackage;
				}else if(jcb.getGenerateMybatisType().trim().equalsIgnoreCase("mapper"))
				{
					namespace=jcb.getBasePackageMapper()+"."+tb.getJavabeanMapperClassName();
				}
				String resultMapId="rm_"+tb.getJavabeanModelClassName();
				Document document = DocumentHelper.createDocument();
				{
					document.addDocType("mapper", "-//ibatis.apache.org//DTD Mapper 3.0//EN", "http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd");
				}
				//创建根结点mapper
//				document.addComment("使用命名空间（namespace）以方便sql搜索定位");
				document.addComment(" use namespace for search ");
				Element mapper = document.addElement("mapper");
				mapper.addAttribute("namespace", namespace);
				{//添加resultMap
//					mapper.addComment("表("+tb.getTableName()+")与java类("+javabeanNameWithPackage+")的映射关系");
					mapper.addComment("database table: ("+tb.getTableName()+") with javabean : ("+javabeanNameWithPackage+")");
					Element resultMap = mapper.addElement("resultMap");
					resultMap.addAttribute("id", resultMapId);
					resultMap.addAttribute("type", javabeanNameWithPackage);
					for(ColumnBean cb:tb.getListColumn())
					{
						Element result=resultMap.addElement("result");
						result.addAttribute("property", cb.getJavabeanFieldName());
						result.addAttribute("column", cb.getColumnName());
					}
				}
				{//如果和数据库关键字相同，则修改表的列名（添加引用符）
					for(int i=0;i<tb.getListColumn().size();i++)
					{
						String columnName=tb.getListColumn().get(i).getColumnName();
						if(KeyWords.MYSQL.contains(columnName.toUpperCase().trim()))
						{
							columnName=MybatisXmlUtil.MYSQL_SEPARATE+columnName+MybatisXmlUtil.MYSQL_SEPARATE;
							tb.getListColumn().get(i).setColumnName(columnName);
						}
						
					}
				}
				{//表的列名
					String columnNames="";
					for(ColumnBean cb:tb.getListColumn())
					{
						columnNames+=cb.getColumnName()+",";
					}
					columnNames=columnNames.substring(0,columnNames.lastIndexOf(","));
//					mapper.addComment("表的列名");
					mapper.addComment("columns of table");
					Element columns = mapper.addElement("sql");
					columns.addAttribute("id", "columns");
					columns.addCDATA(columnNames);
				}
				{//where
//					mapper.addComment("查询（where部分）");
					mapper.addComment("the 'where' sql of search  ");
					Element sql=mapper.addElement("sql").addAttribute("id", "where_sql");
					Element where =sql.addElement("where");
					for(ColumnBean cb:tb.getListColumn())
					{
						String test="{columnName} != null";
						String text="and {columnName} = {columnValue} ";
						test=test.replace("{columnName}", cb.getJavabeanFieldName());
						text=text.replace("{columnName}",cb.getColumnName() );
						text=text.replace("{columnValue}", "#{"+cb.getJavabeanFieldName()+"}");
						Element eif=where.addElement("if");
						eif.addAttribute("test", test);
						eif.addText(text);
					}
				}
				{//where带有对象别名的的语句
//					mapper.addComment("查询（where部分），带有表对象别名的语句");
					mapper.addComment("the 'where' sql with table alias of search  ");
					Element sql=mapper.addElement("sql").addAttribute("id", "where_with_alias_sql");
					Element where =sql.addElement("where");
					for(ColumnBean cb:tb.getListColumn())
					{
						String test="{columnName} != null";
						String text="and {columnName} = {columnValue} ";
						test=test.replace("{columnName}", jcb.getTableAlias().trim()+"."+cb.getJavabeanFieldName());
						text=text.replace("{columnName}",cb.getColumnName() );
						text=text.replace("{columnValue}", "#{"+jcb.getTableAlias().trim()+"."+cb.getJavabeanFieldName()+"}");
						Element eif=where.addElement("if");
						eif.addAttribute("test", test);
						eif.addText(text);
					}
				}
				{//sort，排序部分
//					mapper.addComment("排序部分");
					mapper.addComment(" the 'sort' sql for search ");
					Element sql = mapper.addElement("sql").addAttribute("id", "sort_sql");
					Element trim = sql.addElement("trim");
					Element ife = trim.addElement("if").addAttribute("test", "sort != null").addText("ORDER BY");
					Element foreach=ife.addElement("foreach");
					foreach.addAttribute("collection", "sort");
					foreach.addAttribute("index", "key");
					foreach.addAttribute("item", "value");
					foreach.addAttribute("open", "");
					foreach.addAttribute("separator", ",");
					foreach.addAttribute("close", "");
					Element columnName=foreach.addElement("choose");
					Element sortDerection=foreach.addElement("choose");
					for(ColumnBean cb:tb.getListColumn())
					{
						String test="key!=null and '{columnName}'.equalsIgnoreCase(key)";
						test=test.replace("{columnName}", cb.getJavabeanFieldName());
						columnName.addElement("when").addAttribute("test", test).addText(cb.getColumnName());
					}
					sortDerection.addElement("when").addAttribute("test", "'asc'.equalsIgnoreCase(value)").addText("asc");
					sortDerection.addElement("when").addAttribute("test", "'desc'.equalsIgnoreCase(value)").addText("desc");
					sortDerection.addElement("otherwise").addText("asc");
					
				}
				{//update
					{//update_sql
//						mapper.addComment("修改表中的值（set部分）");
						mapper.addComment(" the 'set' sql for search ");
						Element element = mapper.addElement("sql").addAttribute("id", "update_sql").addElement("set");
						for(ColumnBean cb:tb.getListColumn())
						{
							if(!cb.getIsPrimaryKey())
							{
								String test="{columnName} != null";
								String text="{columnName} = {columnValue} ,";
								test=test.replace("{columnName}", cb.getJavabeanFieldName());
								text=text.replace("{columnName}",cb.getColumnName() );
								text=text.replace("{columnValue}", "#{"+cb.getJavabeanFieldName()+"}");
								Element eif=element.addElement("if");
								eif.addAttribute("test", test);
								eif.addText(text);
							}						
							
						}
					}
					{//update_with_alias_sql,带有表别名的Update的set语句
//						mapper.addComment("修改表中的值（set部分）,带有表别名");
						mapper.addComment(" the 'set' sql with table alias for search ");
						Element element = mapper.addElement("sql").addAttribute("id", "update_with_alias_sql").addElement("set");
						for(ColumnBean cb:tb.getListColumn())
						{
							if(!cb.getIsPrimaryKey())
							{
								String test="{columnName} != null";
								String text="{columnName} = {columnValue} ,";
								test=test.replace("{columnName}", jcb.getTableAlias().trim()+"."+cb.getJavabeanFieldName());
								text=text.replace("{columnName}",cb.getColumnName() );
								text=text.replace("{columnValue}", "#{"+jcb.getTableAlias().trim()+"."+cb.getJavabeanFieldName()+"}");//set_text=set_text.replace("{fieldName}", "#{"+jcb.getTableAlias().trim()+"."+cb.getJavabeanFieldName()+"}");
								Element eif=element.addElement("if");
								eif.addAttribute("test", test);
								eif.addText(text);
								
							}
							
						}
					}
					{//update
//						String text=" UPDATE {tableName} ";
//						text=text.replace("{tableName}", tb.getTableName());
//
//						mapper.addComment("修改表中的值");
//						Element element = mapper.addElement("update");
//						element.addAttribute("id", "update");
//						//element.addAttribute("parameterType", javabeanNameWithPackage);
//						element.addText(text);
//						Element include = element.addElement("include");
//						include.addAttribute("refid", "update_sql");
//						element.addElement("include").addAttribute("refid", "where_sql");
					}
					{//updateById
						if(tb.getPrimaryKeyNum()>0)
						{
							String text=" UPDATE {tableName}  ";
							text=text.replace("{tableName}", tb.getTableName());
							String ids=" where ";
							for(ColumnBean cb:tb.getListColumn())
							{
								String id="";
								if(cb.getIsPrimaryKey())
								{
									id="{columnName} = {columnValue},";
									id=id.replace("{columnName}", cb.getColumnName());	
									id=id.replace("{columnValue}", "#{"+cb.getJavabeanFieldName()+"}");
									ids+=id;
								}
							}
							ids=ids.substring(0,ids.lastIndexOf(","));
							ids=ids.replace(",", " AND ");

//							mapper.addComment("通过id修改表中的值");
							mapper.addComment(" update the table by 'id' sql ");
							Element element = mapper.addElement("update");
							element.addAttribute("id", "updateById");
							element.addAttribute("parameterType", "java.util.Map");
							//element.addAttribute("parameterType", javabeanNameWithPackage);
							element.addText(text);
							{//set 部分
								Element include = element.addElement("include");
								include.addAttribute("refid", "update_with_alias_sql");
							}
							
							element.addText(ids);
						}						
					}
				}
				{//insert
					String id="";
					for(ColumnBean cb:tb.getListColumn())
					{
						if(Primarykey.valueOf(cb.getIsPrimaryKey().toString())==Primarykey.yes&&cb.getExtra()!=null&&!cb.getExtra().trim().equals(""))
						{
							id=cb.getColumnName();
						}
					}
					String insert="INSERT INTO {tableName} ({columnNames}) VALUES ({columnValues})";
					String columnNames="";
					String columnValues="";
					for(ColumnBean cb:tb.getListColumn())
					{
						columnNames+=cb.getColumnName()+",";
						columnValues+="#{"+cb.getJavabeanFieldName()+"},";
					}
					columnValues=columnValues.substring(0,columnValues.lastIndexOf(",")>0?columnValues.lastIndexOf(","):columnValues.length());
					columnNames=columnNames.substring(0,columnNames.lastIndexOf(",")>0?columnNames.lastIndexOf(","):columnNames.length());
					insert=insert.replace("{tableName}", tb.getTableName());
					insert=insert.replace("{columnNames}", columnNames);
					insert=insert.replace("{columnValues}", columnValues);
//					mapper.addComment("向表中插入值");
					mapper.addComment(" insert value sql ");
					Element element = mapper.addElement("insert");
					element.addAttribute("id", "insert");
					if(!id.equals(""))						
					{
						element.addAttribute("useGeneratedKeys", "true");
						element.addAttribute("keyProperty", id);
					}
					//element.addAttribute("parameterType", javabeanNameWithPackage);
					element.addCDATA(insert);
				}
				{//insertNotNull
					String id="";
					for(ColumnBean cb:tb.getListColumn())
					{
						if(Primarykey.valueOf(cb.getIsPrimaryKey().toString())==Primarykey.yes&&cb.getExtra()!=null&&!cb.getExtra().trim().equals(""))
						{
							id=cb.getColumnName();
						}
					}
					String text="INSERT INTO {tableName} ";
					text=text.replace("{tableName}", tb.getTableName());

//					mapper.addComment("向表中插入不为NULL的值");
					mapper.addComment(" insert into table withnot null ");
					Element element = mapper.addElement("insert");
					element.addAttribute("id", "insertNotNull");
					if(!id.equals(""))						
					{
						element.addAttribute("useGeneratedKeys", "true");
						element.addAttribute("keyProperty", id);
					}
					//element.addAttribute("parameterType", javabeanNameWithPackage);
					element.addText(text);
					Element column=element.addElement("trim");
					column.addAttribute("prefix", "(");
					column.addAttribute("suffix", ")");
					column.addAttribute("suffixOverrides", ",");
					Element value=element.addElement("trim");
					value.addAttribute("prefix", "  VALUES(");
					value.addAttribute("suffix", ")");
					value.addAttribute("suffixOverrides", ",");
					for(ColumnBean cb:tb.getListColumn())
					{
						String columnTest="{fieldName} != null";
						columnTest=columnTest.replace("{fieldName}", cb.getJavabeanFieldName());
						Element columnIf=column.addElement("if");
						columnIf.addAttribute("test",columnTest);
						columnIf.addText(cb.getColumnName()+",");
						
						Element valueIf=value.addElement("if");
						valueIf.addAttribute("test",columnTest);
						valueIf.addText("#{"+cb.getJavabeanFieldName()+"},");
					}
				}
				{//insertBatch
					String id="";
					for(ColumnBean cb:tb.getListColumn())
					{
						if(Primarykey.valueOf(cb.getIsPrimaryKey().toString())==Primarykey.yes&&cb.getExtra()!=null&&!cb.getExtra().trim().equals(""))
						{
							id=cb.getColumnName();
						}
					}
					String insert="INSERT INTO {tableName} ({columnNames}) VALUES ";
					String columnNames="";
					String columnValues="";
					for(ColumnBean cb:tb.getListColumn())
					{
						if(!cb.getColumnName().equals(id))
						{
							columnNames+=cb.getColumnName()+",";
							columnValues+="#{item."+cb.getJavabeanFieldName()+"},";							
						}
					}
					columnValues=columnValues.substring(0,columnValues.lastIndexOf(","));
					columnValues="("+columnValues+")";
					columnNames=columnNames.substring(0,columnNames.lastIndexOf(","));
					insert=insert.replace("{tableName}", tb.getTableName());
					insert=insert.replace("{columnNames}", columnNames);
					
//					mapper.addComment("向表中批量插入值");
					mapper.addComment("insert batch into table ");
					Element element = mapper.addElement("insert");
					element.addAttribute("id", "insertBatch");
//					if(!id.equals(""))						
//					{
//						element.addAttribute("useGeneratedKeys", "true");
//						element.addAttribute("keyProperty", id);
//					}
					element.addAttribute("parameterType", "java.util.List");
					element.addText(insert);
					
					Element foreach=element.addElement("foreach");
					foreach.addAttribute("collection", "list");
					foreach.addAttribute("item", "item");
					foreach.addAttribute("open", "");
					foreach.addAttribute("separator", ",");
					foreach.addAttribute("close", "");
					foreach.addText(columnValues);

				}
				{//getById
					if(tb.getPrimaryKeyNum()>0)
					{
						String text="SELECT";
						String where=" FROM {tableName}  WHERE {ids}";
						String ids="";
						for(ColumnBean cb:tb.getListColumn())
						{
							String id="";
							if(cb.getIsPrimaryKey())
							{
								id="{columnName} = {columnValue},";
								id=id.replace("{columnName}", cb.getColumnName());	
								id=id.replace("{columnValue}", "#{"+cb.getJavabeanFieldName()+"}");
								ids+=id;
							}
						}
						ids=ids.substring(0,ids.lastIndexOf(","));
						ids=ids.replace(",", " AND ");
						where=where.replace("{tableName}", tb.getTableName());
						where=where.replace("{ids}", ids);
						
//						mapper.addComment("通过主键获得表中的记录");
						mapper.addComment(" get the records by id ");
						Element element = mapper.addElement("select");
						element.addAttribute("id", "getById");
						//element.addAttribute("parameterType", javabeanNameWithPackage);
						element.addAttribute("resultMap", resultMapId);
						element.addText(text);
						Element include = element.addElement("include");
						include.addAttribute("refid", "columns");
						element.addCDATA(where);	
					}								
				}
				{//getByIds
					if(tb.getPrimaryKeyNum()>0)
					{
						String text="SELECT";
						String where=" FROM {tableName}  WHERE {primaryColumnNames} in ";
						String primaryColumnNames="";
						String primaryColumnValues="";
						if(tb.getPrimaryKeyNum()==1)//单主键
						{
							for(ColumnBean cb:tb.getListColumn())
							{
								if(cb.getIsPrimaryKey())
								{
									primaryColumnNames=cb.getColumnName();
									primaryColumnValues="#{item}";
									break;
								}
							}
						}else//联合主键
						{
							primaryColumnNames=" ({primaryColumnName}) ";
							primaryColumnValues=" ({primaryColumnValue}) ";
							String primaryColumnName="";
							String primaryColumnValue="";
							for(ColumnBean cb:tb.getListColumn())
							{
								if(cb.getIsPrimaryKey())
								{
									primaryColumnName += cb.getColumnName()+",";
									primaryColumnValue += "#{item."+cb.getJavabeanFieldName()+"},";
								}
							}
							primaryColumnName=primaryColumnName.substring(0,primaryColumnName.lastIndexOf(","));
							primaryColumnValue=primaryColumnValue.substring(0,primaryColumnValue.lastIndexOf(","));
							
							primaryColumnNames=primaryColumnNames.replace("{primaryColumnName}", primaryColumnName);
							primaryColumnValues=primaryColumnValues.replace("{primaryColumnValue}", primaryColumnValue);
						}
						where=where.replace("{tableName}", tb.getTableName());
						where=where.replace("{primaryColumnNames}", primaryColumnNames);
//						mapper.addComment("通过主键批量获得表中的记录");
						mapper.addComment(" get the records by ids ");
						Element element = mapper.addElement("select");
						element.addAttribute("id", "getByIds");
						element.addAttribute("resultMap", resultMapId);
						element.addAttribute("parameterType", "java.util.List");
						element.addText(text);
						
						Element include = element.addElement("include");
						include.addAttribute("refid", "columns");
						element.addCDATA(where);
						
						Element foreach = element.addElement("foreach");
						foreach.addAttribute("collection", "list");
						foreach.addAttribute("item", "item");
						foreach.addAttribute("open", "(");
						foreach.addAttribute("separator", ",");
						foreach.addAttribute("close", ")");
						foreach.addText(primaryColumnValues);
					}						
				}
				
				{//select
//					mapper.addComment("查询符合条件表中的记录");
					mapper.addComment(" get the records by condition ");
					String text="SELECT * FROM {tableName}";
					String limit_and_offset_test="limit != null  and offset != null";
					String limit_no_offset_test="limit != null  and offset == null";
					String limit_and_offset_text="limit #{limit} offset #{offset}";
					String limit_no_offset_text="limit #{limit}";
					text=text.replace("{tableName}", tb.getTableName());
//					//排序
//					String sort="ORDER BY";
//					String sortRule="${key}  ${value}";
					
					Element element = mapper.addElement("select");
					element.addAttribute("id", "select");
					//element.addAttribute("parameterType", javabeanNameWithPackage);
					element.addAttribute("resultMap", resultMapId);
					element.addText(text);
					element.addElement("include").addAttribute("refid", "where_with_alias_sql");
					element.addElement("include").addAttribute("refid", "sort_sql");//排序
					Element choose = element.addElement("choose");
					choose.addElement("when").addAttribute("test", limit_and_offset_test).addText(limit_and_offset_text);
					choose.addElement("when").addAttribute("test", limit_no_offset_test).addText(limit_no_offset_text);
					
//					Element ife=element.addElement("if").addAttribute("test", "sort != null");
//					ife.addText(sort);
//					Element foreach=ife.addElement("foreach");
//					foreach.addAttribute("collection", "sort");
//					foreach.addAttribute("index", "key");
//					foreach.addAttribute("item", "value");
//					foreach.addAttribute("open", "");
//					foreach.addAttribute("separator", ",");
//					foreach.addAttribute("close", "");
//					foreach.addText(sortRule);
				}
				{//selectCount
//					mapper.addComment("查询符合条件表中的记录数");
					mapper.addComment(" get the count by condition ");
					String text="SELECT count(1) FROM {tableName}";
					text=text.replace("{tableName}", tb.getTableName());
					Element element = mapper.addElement("select");
					element.addAttribute("id", "selectCount");
					//element.addAttribute("parameterType", javabeanNameWithPackage);
					element.addAttribute("resultType", "int");//基本数据类型
					element.addText(text);
					element.addElement("include").addAttribute("refid", "where_with_alias_sql");
				}
				{//delete
//					mapper.addComment("删除符合条件的表中的记录");
					mapper.addComment(" delete the records by conditions ");
					String text="DELETE FROM {tableName}";
					text=text.replace("{tableName}", tb.getTableName());
					Element element = mapper.addElement("delete");
					element.addAttribute("id", "delete");
					//element.addAttribute("parameterType", javabeanNameWithPackage);
					element.addText(text);
					element.addElement("include").addAttribute("refid", "where_sql");
				}
				{//deleteById
					if(tb.getPrimaryKeyNum()>1)//联合主键
					{
						String text="DELETE FROM {tableName} WHERE {idNames} = {idValues}";
						String idNames="";
						String idValues="";
						for(ColumnBean cb:tb.getListColumn())
						{
							String idName="";
							String idValue="";
							if(cb.getIsPrimaryKey())
							{
								idName="{idName},";
								idName=idName.replace("{idName}", cb.getColumnName());
								idNames+=idName;
								idValue="#{{idValue}},";
								idValue=idValue.replace("{idValue}", cb.getJavabeanFieldName());
								idValues+=idValue;
							}
						}
						idNames=idNames.substring(0,idNames.lastIndexOf(","));
						idNames="("+idNames+")";
						idValues=idValues.substring(0,idValues.lastIndexOf(","));
						idValues="("+idValues+")";
						text=text.replace("{tableName}", tb.getTableName());
						text=text.replace("{idNames}", ""+idNames+"");
						text=text.replace("{idValues}", ""+idValues+"");
						

//						mapper.addComment("通过id删除表中的记录");
						mapper.addComment(" deleted the records by id ");
						Element element = mapper.addElement("delete");
						element.addAttribute("id", "deleteById");
						//element.addAttribute("parameterType", javabeanNameWithPackage);
						element.addText(text);
						
					}else if(tb.getPrimaryKeyNum()==1)
					{
						String text="DELETE FROM {tableName} WHERE {idName} = {idValue} ";
						String idName="";
						String idValue="";
						for(ColumnBean cb:tb.getListColumn())
						{
							if(cb.getIsPrimaryKey())
							{
								idName="{idName}";
								idName=idName.replace("{idName}", cb.getColumnName());
								idValue="#{{idValue}}";
								idValue=idValue.replace("{idValue}", cb.getJavabeanFieldName());
								break;
							}
						}
						text=text.replace("{tableName}", tb.getTableName());
						text=text.replace("{idName}", idName);
						text=text.replace("{idValue}", idValue);		

//						mapper.addComment("通过id删除表中的记录");
						mapper.addComment(" deleted the records by id ");
						
						Element element = mapper.addElement("delete");
						element.addAttribute("id", "deleteById");
						//element.addAttribute("parameterType", javabeanNameWithPackage);
						element.addText(text);
					}
					
				}
				{//deleteByIds
					if(tb.getPrimaryKeyNum()>0)
					{
						String text="delete from {tableName} WHERE {idNames} in ";
						String idNames="";
						String idValues="";
						if(tb.getPrimaryKeyNum()==1)//单主键
						{
							for(ColumnBean cb:tb.getListColumn())
							{
								if(cb.getIsPrimaryKey())
								{
									idNames=cb.getColumnName();
									idValues="#{item}";
									break;									
								}
							}
						}else//联合主键
						{
							idNames=" ({primaryColumnName}) ";
							idValues=" ({primaryColumnValue}) ";
							String idName="";
							String idValue="";
							
							for(ColumnBean cb:tb.getListColumn())
							{
								if(cb.getIsPrimaryKey())
								{
									idName += cb.getColumnName()+",";
									idValue +=  "#{item."+cb.getJavabeanFieldName()+"},";									
								}
							}
							idName=idName.substring(0,idName.lastIndexOf(","));
							idValue=idValue.substring(0,idValue.lastIndexOf(","));
							
							idNames=idNames.replace("{primaryColumnName}", idName);
							idValues=idValues.replace("{primaryColumnValue}", idValue);
						}
						
						text=text.replace("{tableName}", tb.getTableName());
						text=text.replace("{idNames}", ""+idNames+"");

//						mapper.addComment("通过id批量删除表中的记录");
						mapper.addComment(" deleted the records by ids ");
						Element element = mapper.addElement("delete");
						element.addAttribute("id", "deleteByIds");
						element.addAttribute("parameterType", "java.util.List");
						element.addText(text);
						Element foreach=element.addElement("foreach");
						foreach.addAttribute("collection", "list");
						foreach.addAttribute("item", "item");
						foreach.addAttribute("open", "(");
						foreach.addAttribute("separator", ",");
						foreach.addAttribute("close", ")");
						foreach.addText(idValues);
						
					}

				}
				// 美化格式  
	            OutputFormat format = OutputFormat.createPrettyPrint();  
	            // 指定XML编码,不指定的话，默认为UTF-8  
	            format.setEncoding("UTF-8"); 
	            OutputStream os=new ByteArrayOutputStream();
	            XMLWriter output;
	            String xml="";
				try {
					output = new XMLWriter(os, format);
		            output.write(document); 
		            output.flush();
		            output.close();
		            xml=os.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}  
				return modifyXmlContext(xml);
//				return xml;
			}catch(Exception e)
			{
				e.printStackTrace();
				return "";
			}
			
            
//			return modifyXmlContext(xml);
//			return xml;
		}

		/**
		 * 取得数据库表的所对应的mybatis的xml文件
		 * @param tableBean
		 * @param jcb
		 * @return
		 */
		public static String getXmlWithOnlyNamespaceByTable(TableBean tableBean,JavaConfigBean jcb)
		{
			try
			{
				if(tableBean==null||jcb==null||tableBean.getListColumn()==null||tableBean.getListColumn().size()==0)
				{
					return null;
				}
				TableBean tb=new TableBean();
				{//复制传入的table的相关信息
					BeanUtils.copyProperties(tableBean, tb);
					List<ColumnBean> list_columnBean=new ArrayList<ColumnBean>();
					for(int i=0;i<tb.getListColumn().size();i++)
					{
						ColumnBean columnBean=new ColumnBean();
						BeanUtils.copyProperties(tb.getListColumn().get(i), columnBean);
						list_columnBean.add(columnBean);
					}
					tb.setListColumn(list_columnBean);
				}
				

				String javabeanNameWithPackage=jcb.getBasePackageModel()+"."+tb.getJavabeanModelClassName();
				String namespace="";
				if(jcb.getGenerateMybatisType().trim().equalsIgnoreCase("dao")||jcb.getGenerateMybatisType().trim().equals(""))
				{
					namespace=javabeanNameWithPackage;
				}else if(jcb.getGenerateMybatisType().trim().equalsIgnoreCase("mapper"))
				{
					namespace=jcb.getBasePackageMapper()+"."+tb.getJavabeanMapperClassName();
				}
				Document document = DocumentHelper.createDocument();
				{
					document.addDocType("mapper", "-//ibatis.apache.org//DTD Mapper 3.0//EN", "http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd");
				}
				//创建根结点mapper
//				document.addComment("使用命名空间（namespace）以方便sql搜索定位");
				document.addComment(" use namespace for search ");
				Element mapper = document.addElement("mapper");
				mapper.addAttribute("namespace", namespace);
				mapper.addComment("add definded by youself");
				// 美化格式  
	            OutputFormat format = OutputFormat.createPrettyPrint();  
	            // 指定XML编码,不指定的话，默认为UTF-8  
	            format.setEncoding("UTF-8"); 
	            OutputStream os=new ByteArrayOutputStream();
	            XMLWriter output;
	            String xml="";
				try {
					output = new XMLWriter(os, format);
		            output.write(document); 
		            output.flush();
		            output.close();
		            xml=os.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}  
				return modifyXmlContext(xml);
			}catch(Exception e)
			{
				e.printStackTrace();
				return "";
			}
		}
		public static String getXmlConfigByTables(List<TableBean> list_tb,JavaConfigBean jcb)
		{
			if(list_tb==null||jcb==null||list_tb.size()==0)
			{
				return null;
			}
			Document document = DocumentHelper.createDocument();
			{
				document.addDocType("configuration", "-//mybatis.org//DTD Config 3.0//EN", "http://mybatis.org/dtd/mybatis-3-config.dtd");
			}
			//创建根结点mapper
			document.addComment("mybatis配置文件");
			Element configuration = document.addElement("configuration");
			{//settings
				Element element=configuration.addElement("settings");
				element.addElement("setting").addAttribute("name", "cacheEnabled").addAttribute("value", "true");
				element.addElement("setting").addAttribute("name", "lazyLoadingEnabled").addAttribute("value", "true");
				element.addElement("setting").addAttribute("name", "multipleResultSetsEnabled").addAttribute("value", "true");
				element.addElement("setting").addAttribute("name", "useColumnLabel").addAttribute("value", "true");
				element.addElement("setting").addAttribute("name", "defaultExecutorType").addAttribute("value", "REUSE");
				element.addElement("setting").addAttribute("name", "defaultStatementTimeout").addAttribute("value", "25000");
			}
			{//typeAliases
				Element element=configuration.addElement("typeAliases");
				for(TableBean tb:list_tb)
				{
					String alias=StringUtil.toLowerCaseOfFirstChar(tb.getJavabeanModelClassName());
					String type=jcb.getBasePackageModel()+"."+tb.getJavabeanModelClassName();
					element.addElement("typeAlias").addAttribute("alias", alias).addAttribute("type", type);					
				}
			}
			{//mappers
				Element element=configuration.addElement("mappers");
				//baseMapper
				element.addComment("the base xml of sql with  add and delete and update and select    ");
				for(TableBean tb:list_tb)
				{
//					String baseMapper=(jcb.getXmlBaseMapper()==null||jcb.getXmlBaseMapper().trim().equals(""))?"BaseMapper":StringUtil.getJavabeanClassName(jcb.getXmlBaseMapper().trim());
					String baseMapper=(jcb.getXmlBaseMapper()==null||jcb.getXmlBaseMapper().trim().equals(""))?"BaseMapper":jcb.getXmlBaseMapper().trim();
					String outModel=jcb.getXmlBasePath().toLowerCase().replace(".", "/");
					String outModelPath=StringUtil.removeDoubleSlash(outModel+"/"+tb.getJavabeanName()+baseMapper); 						
					
					String resource=outModelPath+".xml";
					element.addElement("mapper").addAttribute("resource", resource);					
				}
				//mapper
				element.addComment("the extends xml of sql with something  defined by youself  ");
				for(TableBean tb:list_tb)
				{					
					String mapper=(jcb.getXmlMapper()==null||jcb.getXmlMapper().trim().equals(""))?"Mapper":StringUtil.getJavabeanClassName(jcb.getXmlMapper().trim());
					String outModel=jcb.getXmlPath().toLowerCase().replace(".", "/");
					String outModelPath=StringUtil.removeDoubleSlash(outModel+"/"+tb.getJavabeanName()+mapper); 						
					
					String resource=outModelPath+".xml";
					element.addElement("mapper").addAttribute("resource", resource);	
				}
				
			}
			// 美化格式  
            OutputFormat format = OutputFormat.createPrettyPrint();  
            // 指定XML编码,不指定的话，默认为UTF-8  
            format.setEncoding("UTF-8"); 
            OutputStream os=new ByteArrayOutputStream();
            XMLWriter output;
            String xml="";
			try {
				output = new XMLWriter(os, format);
	            output.write(document); 
	            output.flush();
	            output.close();
	            xml=os.toString();
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}  
			return xml;
		}
	}
	/**
	 * 
	 * @Description 将root下的所有的xml的Element转化为map对象；
	 * @author     
	 * @param root
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public static void  parseXml2Map(Element root,Map<String, Object> map)
	{
		List<Element> list=root.elements();
		for(Element element:list)
		{
			if(element.elements().size()==0)
			{
				map.put(element.attributeValue("key").trim(), element.getTextTrim());				
			}else
			{
				Map<String, Object> map2=new HashMap<String, Object>();
				map.put(element.attributeValue("key").trim(),map2);
				parseXml2Map(element,map2);
			}
		}
	}
	/**
	 * 
	 * @Description 将path所指的xml转化为javabean
	 * @author     
	 * @param path xml文件在java包中的位置
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T>  T parseXml2JavaBean(String path,Class<T> cls) {
		try
		{
			//读取xml文件
			InputStream is = XmlUtil.class.getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String message="";
			String str = "";
			while ((str = br.readLine()) != null){message+=str;};
			//将读取的xml文件转化为dom对象；
			Document document = DocumentHelper.parseText(message.trim());
			Element root=document.getRootElement();
			Map<String, Object>map=new HashMap<String, Object>();
			//读取dom对象将之转为map对象
			parseXml2Map(root,map);
			//将map对象转为javabean对象；
			T t=cls.newInstance();
			Field[] fields=t.getClass().getDeclaredFields();
			Field.setAccessible(fields, true);
			for(int i=0;i<fields.length;i++)
			{
				Field field=fields[i];
				if(!Modifier.isFinal(field.getModifiers()))
				{
					field.set(t, map.get(field.getName()));
					
				}
			}
			return t;
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	public static void main(String[] args) throws Exception {
		JavaConfigBean jcb=XmlUtil.parseXml2JavaBean("/txyd/database/resources/java-config.xml",JavaConfigBean.class);
	}

}
