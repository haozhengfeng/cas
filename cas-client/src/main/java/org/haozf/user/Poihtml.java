package org.haozf.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Poihtml {
	public static void main(String[] args) {
		try {
			XWPFDocument doc = new XWPFDocument();
			doc.getDocument().getBody().addNewSectPr();
			XWPFParagraph p1 = doc.createParagraph();
			XWPFRun r1 = p1.createRun();
			r1.setText("The quick brown fox");
			FileOutputStream out = new FileOutputStream("d://simple.docx");
			doc.write(out);
			out.close();
			
			
			FileInputStream inhtml = new FileInputStream("d://simple.docx");
			XWPFDocument documenthtml = new XWPFDocument(inhtml);
			
			String fileInName = "simple";
			String root = "d://poi";  
	        String fileOutName = root + "/" + fileInName + ".html";  
			
	        XHTMLOptions options = XHTMLOptions.create();// .indent( 4 );  
	        // Extract image  
	        File imageFolder = new File( root + "/images/" + fileInName );  
	        options.setExtractor( new FileImageExtractor( imageFolder ) );  
	        // URI resolver  
	        options.URIResolver( new FileURIResolver( imageFolder ) );  
	  
	        OutputStream outhtml = new FileOutputStream( new File( fileOutName ) );  
	        XHTMLConverter.getInstance().convert( documenthtml, outhtml, options );  
	        inhtml.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
