package com.txyd.database.create;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.utils.StringUtil;

public abstract class VelocityCreateBase {
	public  static enum FileType{
		model,
		modelKey,
		baseModel,
		baseService,
		service,
		baseServiceImpl,
		serviceImpl,
		mybatisConfigXml,
		mybatisXml
		
	}
	public static boolean createFile(JavaConfigBean jcb,Map<String, String> fileMap,FileType type){

		//根目录
		String outRoot=new File(jcb.getOutRoot().trim()).isDirectory()?jcb.getOutRoot().trim():FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
		//项目名（文件夹）
		String projectName=jcb.getProjectName();
		//java类所在的路径
		String javaPathRoot=jcb.getJavaPathRoot().trim().equals("")?"src":jcb.getJavaPathRoot().trim();
		//java类所在包所对应的文件路径
		String outModel="";
		{
			if(type==FileType.model){
				outModel=jcb.getBasePackageModel().toLowerCase().replace(".", "/");
			}else if(type==FileType.modelKey){
				outModel=jcb.getBasePackageModel().toLowerCase().replace(".", "/");				
			}else if(type==FileType.baseModel){
				outModel=jcb.getBasePackageModel().toLowerCase().replace(".", "/");				
			}else if(type==FileType.baseService){
				outModel=jcb.getBasePackageService().toLowerCase().replace(".", "/");				
			}else if(type==FileType.service){
				outModel=jcb.getBasePackageService().toLowerCase().replace(".", "/");				
			}else if(type==FileType.baseServiceImpl){
				outModel=jcb.getBasePackageServiceImpl().toLowerCase().replace(".", "/");				
			}else if(type==FileType.serviceImpl){
				outModel=jcb.getBasePackageServiceImpl().toLowerCase().replace(".", "/");				
			}else if(type==FileType.mybatisConfigXml){
				outModel=jcb.getXmlConfigPath().toLowerCase().replace(".", "/");				
			}else if(type==FileType.mybatisXml){
				outModel=jcb.getXmlBasePath().toLowerCase().replace(".", "/");				
			}
		}
		String fileSuffix=".java";
		{
			if(type == FileType.mybatisConfigXml || type == FileType.mybatisXml){
				fileSuffix=".xml";
			}else{
				fileSuffix=".java";
			}
		}
		//绝对路径
		String outModelPath=StringUtil.removeDoubleSlash(outRoot+"/"+projectName+"/"+javaPathRoot+"/"+outModel); 
		//文件
		File modelPath=new File(outModelPath);
		
		System.out.println("生成文件路径："+outModelPath);		
		
		if(!modelPath.exists())modelPath.mkdirs();
		for(String key:fileMap.keySet())
		{
			File modelFile=new File(StringUtil.removeDoubleSlash(modelPath+"/"+key)+fileSuffix);
			try {
				BufferedWriter bw=new BufferedWriter(new FileWriter(modelFile));
				bw.write(fileMap.get(key));
				bw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	public static String removeSpaceLine(String string){
		if(string==null||string.trim().equals("")){
			return "";
		}
		StringBuilder strbu=new StringBuilder();
		String[] strArray=string.split("\n");
		
		for(String temp:strArray ){
			if(!temp.trim().equals("")){
				strbu.append(temp.replaceFirst("\\s+$", "")+"\n");
			}
		}
		return strbu.toString();		
		
	}
	
	public static void main(String[] args){

		{
			String str=" hht \n\t\t\t\n\n\nddddd    \t\t\t  ";
			System.out.println(str);
			System.out.println(removeSpaceLine(str));
		}
		

		{
//	        String str="  oschina      ";
//	        System.out.println(str.replaceFirst("\\s+$", ""));
		}
	}


	

}
