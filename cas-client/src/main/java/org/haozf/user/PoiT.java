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
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

public class PoiT {
	public static void main(String[] args) {
		try {
			XWPFDocument doc = new XWPFDocument();
			
			
			//!!!!!!!!!!!!!!!!!!!!!!!!!
			doc.getDocument().getBody().addNewSectPr();
			XWPFStyles styles = doc.createStyles();

	        String strStyleId = "headline1";
	        CTStyle ctStyle = CTStyle.Factory.newInstance();

	        ctStyle.setStyleId(strStyleId);
	        XWPFStyle s = new XWPFStyle(ctStyle);
	        styles.addStyle(s);
	        //!!!!!!!!!!!!!!!!!!!!!!!!!
			
	        
	        XWPFParagraph p1 = doc.createParagraph();
			p1.setAlignment(ParagraphAlignment.CENTER);
			p1.setBorderBottom(Borders.DOUBLE);
			p1.setBorderTop(Borders.DOUBLE);

			p1.setBorderRight(Borders.DOUBLE);
			p1.setBorderLeft(Borders.DOUBLE);
			p1.setBorderBetween(Borders.SINGLE);

			p1.setVerticalAlignment(TextAlignment.TOP);

			XWPFRun r1 = p1.createRun();
			r1.setBold(true);
			r1.setText("The quick brown fox");
			r1.setBold(true);
			r1.setFontFamily("Courier");
			r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
			r1.setTextPosition(100);
			
			
			XWPFParagraph p2 = doc.createParagraph();
			p2.setAlignment(ParagraphAlignment.RIGHT);

			// BORDERS
			p2.setBorderBottom(Borders.DOUBLE);
			p2.setBorderTop(Borders.DOUBLE);
			p2.setBorderRight(Borders.DOUBLE);
			p2.setBorderLeft(Borders.DOUBLE);
			p2.setBorderBetween(Borders.SINGLE);
			p2.setPageBreak(true);
			
			XWPFRun r2 = p2.createRun();
			r2.setText("jumped over the lazy dog");
			r2.setStrike(true);
			r2.setFontSize(20);
			
			FileOutputStream out = new FileOutputStream("d://simple.docx");
			doc.write(out);
			out.close();
			
			
			FileInputStream in = new FileInputStream("d://simple.docx");
			XWPFDocument document = new XWPFDocument(in);
			XWPFHeaderFooterPolicy policy = document.getHeaderFooterPolicy();
			
	        CTP ctP1 = CTP.Factory.newInstance();
	        CTR ctR1 = ctP1.addNewR();
	        CTText t = ctR1.addNewT();
	        String tText = "Paragraph in header";
	        t.setStringValue(tText);

	        CTP ctP2 = CTP.Factory.newInstance();
	        CTR ctR2 = ctP2.addNewR();
	        CTText t2 = ctR2.addNewT();
	        t2.setStringValue("First paragraph for the footer");
	        
	        XWPFParagraph[] pars = new XWPFParagraph[1];
	        pars[0] = new XWPFParagraph(ctP1, document);
	        pars[0].setAlignment(ParagraphAlignment.CENTER);
	        
	        XWPFParagraph[] pars1 = new XWPFParagraph[1];
	        pars1[0] = new XWPFParagraph(ctP2, document);
	        pars1[0].setAlignment(ParagraphAlignment.CENTER);
	        
	        XWPFHeader headerD = policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, pars);
	        XWPFFooter footer = policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, pars1);
	        
			FileOutputStream out1 = new FileOutputStream("d://simple.docx");
			document.write(out1);
			out1.close();
			
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
