package com.pc.rest.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Item {

	@JsonProperty("kind")
	private String kind;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("htmlTitle")
	private String htmlTitle;
	
	@JsonProperty("link")
	private String link;
	
	@JsonProperty("displayLink")
	private String displayLink;
	
	@JsonProperty("snippet")
	private String snippet;
	
	@JsonProperty("cacheId")
	private String cacheId;
	
	@JsonProperty("mime")
	private String mime;
	
	@JsonProperty("formattedUrl")
	private String formattedUrl;
	
	@JsonProperty("fileFormat")
	private String fileFormat;
	
	@JsonProperty("htmlFormattedUrl")
	private String htmlFormattedUrl;
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHtmlTitle() {
		return htmlTitle;
	}
	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDisplayLink() {
		return displayLink;
	}
	public void setDisplayLink(String displayLink) {
		this.displayLink = displayLink;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public String getCacheId() {
		return cacheId;
	}
	public void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}
	public String getMime() {
		return mime;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	public String getFormattedUrl() {
		return formattedUrl;
	}
	public void setFormattedUrl(String formattedUrl) {
		this.formattedUrl = formattedUrl;
	}
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public String getHtmlFormattedUrl() {
		return htmlFormattedUrl;
	}
	public void setHtmlFormattedUrl(String htmlFormattedUrl) {
		this.htmlFormattedUrl = htmlFormattedUrl;
	}
	@Override
	public String toString() {
		return "Item [kind=" + kind + ", title=" + title + ", htmlTitle="
				+ htmlTitle + ", link=" + link + ", displayLink=" + displayLink
				+ ", snippet=" + snippet + ", cacheId=" + cacheId + ", mime="
				+ mime + ", formattedUrl=" + formattedUrl + ", fileFormat="
				+ fileFormat + ", htmlFormattedUrl=" + htmlFormattedUrl + "]";
	}

}
