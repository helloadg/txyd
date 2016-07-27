package txyd.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class MicrosoftOfficeUtil22 {

	public static void main(String argv[]) throws Exception
	{		
//		String inputFile2007="c:/bb.docx";
//		getword2007(inputFile2007);
		String inputFile2003="c:/bb.doc";
		getword2003(inputFile2003);
	}
	public static void getword2003(String fileName) throws Exception 
	{
		getword2003(fileName,"c:/bb.html");
		
	}
	public static void getword2007(String fileName) throws Exception 
	{
		String fileInName = fileName;
		String fileOutName = "c:/bb.html";

		long startTime = System.currentTimeMillis();
		InputStream is = new FileInputStream(fileInName);
		XWPFDocument document = new XWPFDocument(is);
		XHTMLOptions options = XHTMLOptions.create().indent(4);
		// Extract image
		File imageFolder = new File("c:/test/images");
		options.setExtractor(new FileImageExtractor(imageFolder));
		// URI resolver
		options.URIResolver(new FileURIResolver(imageFolder));

		File outFile = new File(fileOutName);
		outFile.getParentFile().mkdirs();
		OutputStream out = new FileOutputStream(outFile);
		XHTMLConverter.getInstance().convert(document, out, options);

		System.out.println("Generate " + fileOutName + " with "
				+ (System.currentTimeMillis() - startTime) + " ms.");
	}
	public static void getword2003(String fileName, String outPutFile)
			throws Exception {
		InputStream is = new FileInputStream(fileName);
		HWPFDocument wordDocument = new HWPFDocument(is);//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.newDocument());
		 wordToHtmlConverter.setPicturesManager( new PicturesManager()
         {
             public String savePicture( byte[] content,
                     PictureType pictureType, String suggestedName,
                     float widthInches, float heightInches )
             {
                 return "test/"+suggestedName;
             }
         } );
		wordToHtmlConverter.processDocument(wordDocument);
		//save pictures
		List pics=wordDocument.getPicturesTable().getAllPictures();
		if(pics!=null){
			for(int i=0;i<pics.size();i++){
				Picture pic = (Picture)pics.get(i);
				System.out.println();
				try {
					pic.writeImageContent(new FileOutputStream("c:/test/"
							+ pic.suggestFullFileName()));
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
		try {
			File file = new File(outPutFile);
			fos = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
			bw.write(writer.toString());
		} catch (Exception fnfe) {
		}  finally {
			try {
				if (bw != null)
					bw.close();
				if (fos != null)
					fos.close();
			} catch (Exception ie) {
			}
		}
	}
}
