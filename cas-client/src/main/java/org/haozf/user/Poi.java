package org.haozf.user;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakClear;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

public class Poi extends TestCase{
	public static void main(String[] args) {
		try {
			XWPFDocument doc = new XWPFDocument();
			doc.getDocument().getBody().addNewSectPr();
			
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

//			XWPFParagraph p2 = doc.createParagraph();
//			p2.setAlignment(ParagraphAlignment.RIGHT);
//
//			// BORDERS
//			p2.setBorderBottom(Borders.DOUBLE);
//			p2.setBorderTop(Borders.DOUBLE);
//			p2.setBorderRight(Borders.DOUBLE);
//			p2.setBorderLeft(Borders.DOUBLE);
//			p2.setBorderBetween(Borders.SINGLE);

//			XWPFRun r2 = p2.createRun();
//			r2.setText("jumped over the lazy dog");
//			r2.setStrike(true);
//			r2.setFontSize(20);

//			XWPFRun r3 = p2.createRun();
//			r3.setText("and went away");
//			r3.setStrike(true);
//			r3.setFontSize(20);
//			r3.setSubscript(VerticalAlign.SUPERSCRIPT);

//			XWPFParagraph p3 = doc.createParagraph();
//			p3.setWordWrap(true);
//			p3.setPageBreak(true);
//
//			// p3.setAlignment(ParagraphAlignment.DISTRIBUTE);
//			p3.setAlignment(ParagraphAlignment.BOTH);
//			p3.setSpacingLineRule(LineSpacingRule.EXACT);
//
//			p3.setIndentationFirstLine(600);

//			XWPFRun r4 = p3.createRun();
//			r4.setTextPosition(20);
//			r4.setText("To be, or not to be: that is the question: " + "Whether 'tis nobler in the mind to suffer " + "The slings and arrows of outrageous fortune, " + "Or to take arms against a sea of troubles, " + "And by opposing end them? To die: to sleep; ");
//			r4.addBreak(BreakType.PAGE);
//			r4.setText("No more; and by a sleep to say we end " + "The heart-ache and the thousand natural shocks " + "That flesh is heir to, 'tis a consummation " + "Devoutly to be wish'd. To die, to sleep; " + "To sleep: perchance to dream: ay, there's the rub; " + ".......");
//			r4.setItalic(true);
			// This would imply that this break shall be treated as a simple line
			// break, and break the line after that word:

//			XWPFRun r5 = p3.createRun();
//			r5.setTextPosition(-10);
//			r5.setText("For in that sleep of death what dreams may come");
//			r5.addCarriageReturn();
//			r5.setText("When we have shuffled off this mortal coil," + "Must give us pause: there's the respect" + "That makes calamity of so long life;");
//			r5.addBreak();
//			r5.setText("For who would bear the whips and scorns of time," + "The oppressor's wrong, the proud man's contumely,");
//
//			r5.addBreak(BreakClear.ALL);
//			r5.setText("The pangs of despised love, the law's delay," + "The insolence of office and the spurns" + ".......");

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

	        CTP ctP3 = CTP.Factory.newInstance();
	        CTR ctR3 = ctP3.addNewR();
	        CTText t3 = ctR3.addNewT();
	        t3.setStringValue("Second paragraph for the footer");

	        
	        XWPFParagraph[] pars = new XWPFParagraph[1];
//	        for(XWPFParagraph ps:document.getParagraphs()){
//	        	pars[0] = ps;
//	        	
//	        }
	        pars[0] = document.getParagraphs().get(0);
	        
//	        XWPFParagraph[] pars = new XWPFParagraph[1];
//	        pars[0] = p1;
//	        XWPFParagraph[] pars2 = new XWPFParagraph[2];
//	        pars2[0] = p2;
//	        pars2[1] = p3;

	        // Set headers
	        XWPFHeader headerD = policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, pars);
	        XWPFHeader headerF = policy.createHeader(XWPFHeaderFooterPolicy.FIRST);
	        
	        assertNotNull(policy.getDefaultHeader());
	        assertNotNull(policy.getFirstPageHeader());
	        // ....and that the footer object captured above contains two
	        // paragraphs of text.

	        // Check the header created with the paragraph got them, and the one
	        // created without got an empty one
	        assertEquals(1, headerD.getParagraphs().size());
	        assertEquals(1, headerF.getParagraphs().size());

	        assertEquals(tText, headerD.getParagraphs().get(0).getText());
	        assertEquals("", headerF.getParagraphs().get(0).getText());
	        // Set a default footer and capture the returned XWPFFooter object.
//	        XWPFFooter footer = policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, pars2);
			
			in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
