package txyd.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.core.IURIResolver;
import org.apache.poi.xwpf.converter.core.XWPFConverterException;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class MicrosoftOfficeUtil {
	private static Logger log = LogManager.getLogger(MicrosoftOfficeUtil.class);
	/**
	 * doc格式的word文档
	 */
	public static final String WORD_DOC="doc";
	/**
	 * docx格式的word文档
	 */
	public static final String WORD_DOCX="docx";

	/**
	 * 将word文档转化为html并输出转化后的html字符串
	 * @param word：word文档
	 * @param html：html文档
	 * @param suffix：word文档后缀名（doc或docx）
	 * @return
	 * @throws IOException 
	 * @throws XWPFConverterException 
	 */
	public static String wordToHtml(File word,File html,String suffix)
	{
		if(suffix==null||suffix.trim().equals(""))
		{
			log.error("word文档后缀不能为空");
			System.out.println("word文档后缀不能为空");
			return null;
		}else if(word==null)
		{
			log.error("word文档不能为空");
			System.out.println("word文档不能为空");
			return null;
		}else if(html==null)
		{
			log.error("html文档不能为空");
			System.out.println("html文档不能为空");
			return null;
			
		}
		/*****************doc格式的文档*********************/
		if(suffix.equals(WORD_DOC))
		{
			try {
				return MicrosoftOfficeUtil.wordToHtml2003(word, html);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("转换出错");
				return null;
			}
			
		}else if(suffix.equals(WORD_DOCX))
		{
			try {
				return MicrosoftOfficeUtil.wordToHtml2007(word, html);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("转换出错");
				return null;
			}

		}else
		{
			log.error("word文档后缀必须是doc或docx");
			System.out.println("word文档后缀必须是doc或docx");
			return null;
		}
	}
	/**
	 * 将docx文档转化为html并输出转化后的html字符串
	 * @param word
	 * @param html
	 * @param imgPath
	 * @return
	 * @throws Exception
	 */
	public static String wordToHtml2003(File word, File html) throws Exception 
	{
		System.out.println("开始转换html:"+MicrosoftOfficeUtil.getFileName(html)+";doc:"+MicrosoftOfficeUtil.getFileName(word));
		log.info("开始转换html:"+MicrosoftOfficeUtil.getFileName(html)+";doc:"+MicrosoftOfficeUtil.getFileName(word));
		long startTime = System.currentTimeMillis();
		InputStream is = new FileInputStream(word);
		HWPFDocument wordDocument = new HWPFDocument(is);//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.newDocument());
		String htmlName=MicrosoftOfficeUtil.getFileName(html);
		final String imgPath=htmlName+".files/";
		wordToHtmlConverter.setPicturesManager( new PicturesManager()
        {
             public String savePicture( byte[] content,
                     PictureType pictureType, String suggestedName,
                     float widthInches, float heightInches )
             {
            	 String htmlImgSrc=imgPath+suggestedName;
            	 System.out.println("html图片标签src属性："+htmlImgSrc);
            	 log.info("html图片标签src属性："+htmlImgSrc);
            	 return htmlImgSrc;                 
             }
         } );
		wordToHtmlConverter.processDocument(wordDocument);
		//保存图片
		final String imgPath_temp=MicrosoftOfficeUtil.getFilePath(html)+htmlName+".files/";
		MicrosoftOfficeUtil.createPath(imgPath_temp);
		List<Picture> list_picture=wordDocument.getPicturesTable().getAllPictures();
		if(list_picture!=null){
			for(int i=0;i<list_picture.size();i++){
				Picture pircture = (Picture)list_picture.get(i);
				try {
					String pictureName=imgPath_temp+ pircture.suggestFullFileName();
					System.out.println("doc文档图片所在的硬盘路径："+pictureName);
	            	log.info("doc文档图片所在的硬盘路径："+pictureName);
					pircture.writeImageContent(new FileOutputStream(pictureName));
				} catch (Exception e) {
					e.printStackTrace();
				}  
			}
		}
        StringWriter writer = new StringWriter();
        
        Transformer serializer = TransformerFactory.newInstance().newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(
                new DOMSource(wordToHtmlConverter.getDocument()),
                new StreamResult(writer) );
        writer.close();
        
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		String htmlStr="";
		try {
			fos = new FileOutputStream(html);
			bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
			htmlStr=writer.toString();//原html文本
			bw.write(htmlStr);
		} catch (Exception e) {
			System.out.println(e.toString());
		}  finally {
			try {
				if (bw != null)
					bw.close();
				if (fos != null)
					fos.close();
			} catch (Exception ie) {
			}
		}
		System.out.println("转换结束");
		System.out.println("转换 " + html.getAbsolutePath() + " ；用时： " + (System.currentTimeMillis() - startTime) + " ms。");
		
		return htmlStr;
	
	}
	/**
	 * 将docx文档转化为html并输出转化后的html字符串
	 * @param word
	 * @param html
	 * @param imgPath
	 * @return
	 * @throws Exception
	 */
	public static String wordToHtml2007(File word,File html) throws Exception 
	{
		System.out.println("开始转换html:"+MicrosoftOfficeUtil.getFileName(html)+";doc:"+MicrosoftOfficeUtil.getFileName(word));
		log.info("开始转换html:"+MicrosoftOfficeUtil.getFileName(html)+";doc:"+MicrosoftOfficeUtil.getFileName(word));		
		long startTime = System.currentTimeMillis();
		InputStream is = new FileInputStream(word);
		XWPFDocument document = new XWPFDocument(is);
		XHTMLOptions options = XHTMLOptions.create().indent(4);
		/****图片部分的设置（start）***************/
		File imageFolder =null;//图片的存放路径
		File imageURI=null;//图片在html中的路径
		String htmlPath=MicrosoftOfficeUtil.getFilePath(html);
		String htmlName=MicrosoftOfficeUtil.getFileName(html);
		imageFolder=new File(htmlPath+htmlName+".files");
		imageURI=new File(htmlName+".files");//使用的是当前class所在的路径
		options.setExtractor(new FileImageExtractor(imageFolder));
		// URI resolver
		options.URIResolver(new FileURIResolver(imageURI));
		/***************图片部分的设置（end）*****************/

		if(html.getParentFile()!=null&&!html.getParentFile().exists())
		{
			html.getParentFile().mkdirs();				
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OutputStream out = new FileOutputStream(html);
		XHTMLConverter.getInstance().convert(document, baos, options);
		String htmlStrReal=baos.toString();
		String htmlStr="";
		if(SystemUtil.LINUX_SYSTEM==SystemUtil.systemType())//linux操作系统
		{
			String selfPathExp="";
			try
			{
				selfPathExp=new File("").getAbsolutePath()+"/";//使用的是当前class所在的路径(for linux)
				htmlStr=htmlStrReal.replaceAll(selfPathExp, "");//(for linux)				
			}catch(Exception e)
			{
				System.out.println("html图片标签src属性变更时失败："+selfPathExp);
				log.error("html图片标签src属性变更时失败："+selfPathExp);
			}
		}else
		{
			String selfPathExp="";
			try
			{
				selfPathExp=MicrosoftOfficeUtil.getReplaceOfStr(new File("").getAbsolutePath()+"\\");//使用的是当前class所在的路径(for windows)
				htmlStr=htmlStrReal.replaceAll(selfPathExp, "");//(for windows)			
			}catch(Exception e)
			{
				System.out.println("html图片标签src属性变更时失败："+selfPathExp);
				log.error("html图片标签src属性变更时失败："+selfPathExp);
			}			
		}
		out.write(htmlStr.getBytes());
		System.out.println("转换结束");
		System.out.println("转换 " + html.getAbsolutePath() + " ；用时： " + (System.currentTimeMillis() - startTime) + " ms。");
		return out.toString();
	}
	/**
	 * 将str中的"\"替换为"\\"，便于使用String.replaceAll("XX","YY")方法;
	 * @param str
	 * @return
	 */
	public static String getReplaceOfStr(String str)
	{
		String result=str.replaceAll("\\\\","\\\\\\\\");
		return result;
	}
	/**
	 * 如果不存在则创建目录（或者文件），并返回该目录（或者文件）
	 * @param path
	 * @return
	 */
	public static File createPath(String path)
	{
		File file =new File(pathOrFileFilter(path));
		if(!file.exists())
		{
			file.mkdirs();
		}
		return file;
	}
	/**
	 * 美化文件的路径
	 * @param pathOrFile
	 * @return
	 */
	public static String pathOrFileFilter(String pathOrFile)
	{
		if(pathOrFile.contains("//"))
		{
			return pathOrFile.replaceAll("//", "/");
		}
		return pathOrFile;
	}
	/**
	 * 获得文件所在路径
	 * @param file
	 * @return
	 */
	public static String getFilePath(File file)
	{
		
		String filePath=file==null?"":(file.isDirectory()?file.getParent():file.getPath());
		if(!filePath.equals(""))
		{
			if(filePath.contains("\\"))
			{
				filePath=filePath.replaceAll("\\\\", "/");		
				filePath=filePath.substring(0,filePath.lastIndexOf("/")+1);		
			}
		}
		return filePath;
	}
	/**
	 * 获得文件的名称
	 * 11.doc -》11
	 * @param file
	 * @return
	 */
	public static String getFileName(File file)
	{
		String fileName_All=file.getName();
		String fileName=fileName_All.substring(0, fileName_All.lastIndexOf("."));
		return fileName;
	}
	public static void main(String[] args)
	{
//		File file = new File("c:/test/bb.doc");
//		String filePath=file==null?"":(file.isDirectory()?file.getParent():file.getPath());
//		filePath=filePath.replaceAll("\\\\", "/");
//		System.out.println(filePath);
//		System.out.println(getFilePath(file));
//		File word2003 = new File("c:/html/11.doc");
//		File html=new File("c:/html/11.html");
//		MicrosoftOfficeUtil.wordToHtml(word2003, html, MicrosoftOfficeUtil.WORD_DOC);
		//System.out.println(MicrosoftOfficeUtil.getFileName(word2003));
		File word2007 = new File("c:/html/22.docx");
		File html=new File("c:/html/22.html");
		MicrosoftOfficeUtil.wordToHtml(word2007, html, MicrosoftOfficeUtil.WORD_DOCX);
//		System.out.println(new File("").getAbsolutePath());
//		System.out.println(getFilePath(html));
		
	}

}
