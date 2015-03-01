package com.pc.rest.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.pc.rest.controller.PcController;
import com.pc.rest.core.file.pdf.PDFWriter;
import com.pc.rest.pojo.Item;
import com.pc.rest.pojo.PlagrismSearchResponse;
import com.pc.rest.service.intrface.IPDFCreateService;
import com.pc.rest.service.intrface.ISearchContentService;

@Service("pdfCreateService")
public class PDFCreateServiceImpl implements IPDFCreateService{

	private static final Logger logger = Logger.getLogger(PDFCreateServiceImpl.class);
	
	@Autowired
	private ISearchContentService searchContentService;
	
	@Value("${filePrefix}")
	private String filePrefix;

	
	@Override
	public File createPDF(String query) {
		PlagrismSearchResponse plagrismResponse = getSearchContentService().getSearchResult(query);
		return populateFileFromPCResponse(plagrismResponse);
	}
	
	private File populateFileFromPCResponse(PlagrismSearchResponse plagrismResponse){
		if(plagrismResponse!=null){
			String fileName = getFileName();
			try {
				PDFWriter pdfWriter = new PDFWriter(fileName);
				List<Item> items = plagrismResponse.getItems();
				pdfWriter.createTable(getTableHeaders());
				for(int i = 0 ; i<items.size(); i++){
					pdfWriter.addDataInPDFTable(String.valueOf((i+1)), BaseColor.BLACK);
					Item item = items.get(i);
					pdfWriter.addDataInPDFTable(item.getSnippet(), BaseColor.BLUE);
					pdfWriter.addDataInPDFTable(item.getLink(), BaseColor.RED);
				}
				pdfWriter.addTableToDocument();
				pdfWriter.getDocument();
				return pdfWriter.getFile();
			} catch (DocumentException e) {
				logger.error("Exception occured: " + e.getMessage());
			} catch (IOException e) {
				logger.error("Exception occured: " + e.getMessage());
			}
		}
		return null;
	}
	
	private List<String> getTableHeaders(){
		List<String> headers = new ArrayList<String>();
		headers.add("S.No");headers.add("Snippet");headers.add("Source");
		return headers;
	}
	
	private String getFileName(){
		logger.info("File Prefix is " + filePrefix);
		return (filePrefix + "PCResponse" + System.currentTimeMillis() + ".pdf");
	}

	public ISearchContentService getSearchContentService() {
		return searchContentService;
	}
}
