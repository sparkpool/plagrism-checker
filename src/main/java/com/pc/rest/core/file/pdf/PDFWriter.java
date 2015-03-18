package com.pc.rest.core.file.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
	
	private PdfPTable pdfTable;
	
	private File file;
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
	     	file = new File(filePath);
	     	if(!file.exists()){
	     		file.createNewFile();
	     	}
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
	}
	
	public File getFile(){
		return this.file;
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
	    addEmptyLine(paragraph, 1);
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
	public Anchor setAnchor(String anchorName){
		return getAnchor(anchorName, PDFStyles.catFont);
	}
	
	public Anchor getAnchor(String anchorName, Font font){
		Anchor anchor = new Anchor(anchorName, font);
	    anchor.setName(anchorName);
	    return anchor;
	}
	
	/**
	 * This will add data in a paragraph and it will add empty line
	 * before writing to a paragraph
	 * @param data
	 * @throws DocumentException 
	 */
	public void addDataInParagraph(String data) throws DocumentException{
	  document.add(new Paragraph(data));	
	}
	
	public void addDataInParagraphInsideChapter(String data, Chapter chapter) throws DocumentException{
		if(chapter == null){
			throw new InvalidParameterException("CHAPTER CAN NOT BE NULL");
		}
		chapter.add(new Paragraph(data));
		document.add(chapter);
	}
	
	public Chapter createChapter(String chapterTitle, Integer noOfChapter){
		if(chapterTitle == null && noOfChapter == null){
			throw new InvalidParameterException("CHAPTER TITLE AND NO OF CHAPTER CAN NOT BE NULL");
		}
		if(chapterTitle == null){
			return new Chapter(noOfChapter);
		}
		return new Chapter(new Paragraph(chapterTitle), noOfChapter);
	}
	
	public Chapter createChapterwithAnchor(Integer noOfChapter, Anchor anchor){
		if(noOfChapter == null && anchor == null){
			throw new InvalidParameterException("NO OF CHAPTER AND ANCHOR CAN NOT BE NULL");
		}
		if(anchor == null){
			return new Chapter(noOfChapter);
		}
		return new Chapter(new Paragraph(anchor), noOfChapter);
	}
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	  }
	
	/**
	 * This will create Table Structure in PDF File 
	 * List will contain header names size of list will be equal to 
	 * no of headers
	 * 
	 * This table will be added to document after creating header cells
	 * @param tableHeaders
	 * @throws DocumentException 
	 */
	public void createTable(List<String> tableHeaders) throws DocumentException{
		if(tableHeaders == null || tableHeaders.size() == 0){
			throw new InvalidParameterException("TABLE HEADERS MUST BE GREATER THAN ZERO");
		}
		float[] widths = getWidths(tableHeaders.size());
		pdfTable = new PdfPTable(widths);
		int i=0;
		for(String header : tableHeaders){
			if(header!=null && header.trim().length()>0){
				pdfTable.addCell(getTableCell(header));	
			}
		}
	}
	
	/**
	 * 
	 * @param noOfColumn
	 * @return
	 */
	public float[] getWidths(int noOfColumn){
		float[] widths = new float[noOfColumn];
		for(int i=0;i<noOfColumn;i++){
			widths[i] = (i == 0 ? 1f : 8f);
		}
		return widths;
	}
	
	/**
	 * This will add data in next cell
	 * @param table
	 * @param data
	 */
	public void addDataInPDFTable(String data, BaseColor baseColor){
		if(pdfTable == null){
			throw new InvalidParameterException("PDF Table CAN NOT BE NULL");
		}
		Font font = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, baseColor);
		pdfTable.addCell(new Paragraph(data, font));
	}
	
	public void addTableToDocument() throws DocumentException{
	  if(pdfTable == null){
		  throw new InvalidParameterException("YOU CAN NOT ADD EMPTY PDF TABLE TO DOCUMENT");
	  }
	  document.add(pdfTable);
	}
	
	private PdfPCell getTableCell(String headerName){
		Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
		PdfPCell pdfCell = new PdfPCell(new PdfPCell(new Phrase(headerName, font)));
		pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    return pdfCell;
	}
	
	
	/**
	 * This will return document and this will close the document
	 * as well.
	 * If you do any operation after calling this method an Exception will 
	 * Occurred	
	 * @return
	 */
	public Document getDocument(){
		close();
		return document;
	}

	private void close(){
		if(document == null){
			throw new InvalidParameterException("DOCUMENT CAN NOT BE NULL");
		}
		document.close();
	}
}
