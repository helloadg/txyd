package com.test.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/19.
 */
public class Test {
	public static void main(String[] args) {
		File file = new File("e:/zufang");
		for (File item : file.listFiles()) {
			try {
				System.out.println(item.getName());
				PDDocument doc = PDDocument.load(item);
				PDFRenderer renderer = new PDFRenderer(doc);
				int pageCount = doc.getNumberOfPages();
				for (int i = 0; i < pageCount; i++) {
					BufferedImage image = renderer.renderImageWithDPI(i, 296);
					ImageIO.write(image, "PNG", new File("e:/zufang/{name}.png".replace("{name}",item.getName())));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
