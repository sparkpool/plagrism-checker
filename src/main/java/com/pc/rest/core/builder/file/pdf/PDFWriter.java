package com.pc.rest.core.builder.file.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * This is used for creating PDF 
 * We only have one constructor which takes filename with full location
 * This is basically a layer above itext library to create pdf
 * @author shrey
 *
 */
public class PDFWriter {
	
	private Document document;
	
	/**
	 * Constructor takes filename with full path
	 * This will create new file if file doesn't exist in particular location
	 * This will also throw DocumentException if not able to create PDF document
	 * @param filePath
	 * @throws DocumentException
	 * @throws IOException 
	 */
	public PDFWriter(String filePath) throws DocumentException, IOException{
	     	document = new Document();
	     	File file= new File(filePath);
	     	if(!file.exists()){
	     		file.createNewFile();
	     	}
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
	}
	
	/**
	 * This will add Title as meta data in PDF document 
	 * which you can see in properties
	 * @param title
	 */
	public void addTitle(String title){
		document.addTitle(title);
	}
	
	
	/**
	 * This will add Subject as meta data in PDF document
	 * which you can see in properties
	 * @param subject
	 */
	public void addSubject(String subject){
		document.addSubject(subject);
	}
	
	/**
	 * This will add keywords as meta data in PDF document
	 * which you can see in properties
	 * You can pass multiple keywords separated by comma
	 * @param keywords
	 */
	public void addKeywords(String keywords){
		document.addKeywords(keywords);
	}
	
	/**
	 * This will add author info as meta data in PDF document
	 * which you can see in properties
	 * @param author
	 */
	public void addAuthor(String author){
		document.addAuthor(author);
	}
	
	/**
	 * This will add creator as as meta data in PDF document
	 * which you can see in properties
	 * @param creator
	 */
	public void addCreator(String creator){
		document.addCreator(creator);
	}
	
	/**
	 * This will add title of the page.
	 * This will add CatFont by default of this title.
	 * @param title
	 * @throws DocumentException 
	 */
	public void addTitleOfThePage(String title) throws DocumentException{
		addTitleOfThePage(title, PDFStyles.catFont);
	}
	
	/**
	 * This will add title of the page with passed font.
	 * @param title
	 * @param font
	 * @throws DocumentException 
	 */
	public void addTitleOfThePage(String title, Font font) throws DocumentException{
		Paragraph paragraph = new Paragraph();
	    addEmptyLine(paragraph, 1);
	    paragraph.add(new Paragraph(title, font));
	    document.add(paragraph);
	}
	
	/**
	 * This will start the new page.
	 * After calling this method whatever you will add will add in next line.
	 */
	public void changePage(){
		document.newPage();
	}
	
	/**
	 * This will add anchor like chapter or subpart.
	 * ByDefault this will add catFont by default
	 * @param anchorName
	 */
	public void setAnchor(String anchorName){
		setAnchor(anchorName, PDFStyles.catFont);
	}
	
	public void setAnchor(String anchorName, Font font){
		Anchor anchor = new Anchor(anchorName, font);
	    anchor.setName(anchorName);
	    
	    
	}
	
	/**
	 * This will add data in a paragraph and it will add empty line
	 * before writing to a paragraph
	 * @param data
	 */
	public void addDataInParagraph(String data){
		
	}
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	  }
	
	/**
	 * This will return document and this will close the document
	 * as well.
	 * If you do any operation after calling this method an Exception will 
	 * Occurred	
	 * @return
	 */
	public Document getDocument(){
		document.close();
		return document;
	}

}
