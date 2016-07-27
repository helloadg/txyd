package com.txyd.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


public class ImageUtils {

	/**
	 * 判断文件是否是图片
	 * 使用Image读取文件时，如果是非图像文件，则会返回null。 
	 * @param filePath
	 * @return
	 */
	public static boolean isImage(File file){
		{//方法1
			BufferedImage image=null;
			try {
				image=ImageIO.read(file);
				if(image==null){
					
					return false;			
				}else{
					System.out.println(image.getType());
					return true;
				}
			} catch (IOException e1) {
				return false;
			}
		}
		
//		{//方法2
//			ImageInputStream is = null;
//			try {
//				is = ImageIO.createImageInputStream(file);
//				Iterator<ImageReader> iter = ImageIO.getImageReaders(is);
//				return iter.hasNext();
//			} catch (Exception e) {
//				return false;
//			}finally{
//				if(is!=null){
//					 try {
//						is.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}			
//		}		
	}
	/**
	 * 判断文件类型
	 * @param filePath
	 * @param types："png|gif|jpg"
	 * @return
	 */
	public static boolean isFileTypes(File file,String types){
		ImageInputStream is = null;
		try {
			is = ImageIO.createImageInputStream(file);
			Iterator<ImageReader> iter = ImageIO.getImageReaders(is);
			if(iter.hasNext()){
				
				ImageReader reader = iter.next();
				String[] typeArray=types.split("\\|");
				List<String> list=Arrays.asList(typeArray);
				System.out.println(file.getCanonicalPath()+"： Format: " + reader.getFormatName());
				if(list!=null&&list.contains(reader.getFormatName())){
					return true;					
				}else{
					return false;
				}				
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}finally{
			if(is!=null){
				 try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	


	
	public static void main(String[] args) throws IOException{
		{
//			File files=new File("D:/test/");
//			for(File file:files.listFiles()){
//				boolean isImg=ImageUtils.isImage(file);
//				System.out.println( file.getCanonicalPath()+" isImg:"+isImg);
//			}
		}
		{
//			File files=new File("D:/test/");
//			for(File file:files.listFiles()){
//				boolean isImg=ImageUtils.isFileTypes(file,"png|gif|jpg");
//				System.out.println( file.getCanonicalPath()+" isImg:"+isImg);
//			}
		}
		{
			File files=new File("D:/test/");
			for(File file:files.listFiles()){
				System.out.println(new MimetypesFileTypeMap().getContentType(file));
			}
		}
		
	}


}
